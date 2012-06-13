package com.aeonphyxius.gamecomponents.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.content.res.AssetManager;
import javax.microedition.khronos.opengles.GL10;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.gamecomponents.drawable.Enemy;

/**
 * SquadronManager Object.
 * 
 * <P>
 * Squadrons Manager component to control all weapons (player and enemies)
 * 
 * <P>
 * This class contains logic to display and fire the weapons  
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class SquadronManager {

	private static SquadronManager instance = null;			// Singleton implementation
	private ArrayList<Squadron> squadronList;				// List of squadrons

	/**
	 * 
	 */
	protected SquadronManager() {
		squadronList = new ArrayList<Squadron>();
	}

	/**
	 * Singleton implementation to manage the unique instance of this class
	 * @return unique instance of this class
	 */
	public static SquadronManager getInstance() {
		if (instance == null) {
			instance = new SquadronManager();
		}
		return instance;
	}


	public ArrayList<Squadron> getSquadronList() {
		return squadronList;
	}

	public void setSquadronList(ArrayList<Squadron> squadronList) {
		this.squadronList = squadronList;
	}

	/**
	 * reset the squadrons, loading the given level
	 * @param level
	 */
	public void resetSquadrons(int level){
		squadronList = new ArrayList<Squadron>();
		try{
			this.loadSquadronsLevel(level);
		}catch (Exception e){
			e.printStackTrace();
		}		
	}

	/**
	 * Loads all the squadrons from the level XML file
	 * @param level
	 * @throws Exception
	 */
	public void loadSquadronsLevel(int level) throws Exception{
		squadronList = new ArrayList<Squadron>();
		AssetManager assetManager = Engine.context.getAssets();
		String fileName = "level" + level +".xml";
		InputStream is;
		try {
			is = assetManager.open(fileName);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xmlReader = sp.getXMLReader();
			xmlReader.setContentHandler(new SquadronContentHandler());
			xmlReader.parse(new InputSource(is));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Moves all the enemies on screen. Also moves down the enemies above the visible area
	 * @param gl
	 */
	public void draw(GL10 gl, int[] spriteSheet) {
		int squadronNum = SquadronManager.getInstance().getSquadronList().size();		
		Squadron tempSquadron;

		if (squadronNum<=0){							
			Engine.GameSatus = Engine.GAMESTATUS.LEVEL_COMPLETE; // As a trick, when all the squadrons have been pass or destroyed, the level will be completed					
		}else{			
			// Step 1: Loop all squadrons
			for (int sqNum = 0; sqNum< squadronNum ; sqNum++) {	
				tempSquadron = SquadronManager.getInstance().getSquadronList().get(sqNum);
				if (!tempSquadron.isDestroyed()) {	// If the squadron is destroyed, whe don't draw it anymore
					Random randomPos = new Random();
					int enemyNum = tempSquadron.getEnemyList().size();		

					// Step 2: Loop all enemies, inside every squadron
					for (int sqPos = 0; sqPos < enemyNum; sqPos++){
						Enemy tempEnemy = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqPos);
						if (!tempEnemy.isDestroyed){// If the enemy is destroyed, whe don't draw it anymore

							// Prepare the transformations
							gl.glMatrixMode(GL10.GL_MODELVIEW);
							gl.glLoadIdentity();
							gl.glPushMatrix();
							gl.glScalef(.15f, .15f, 1f); 

							// Now depending of each enemy type, the behaiviour will be different
							switch (tempSquadron.getSquadronEnemyType()) {

							case Engine.TYPE_INTERCEPTOR: // Interceptor
								if (tempEnemy.posY >= Engine.SQUADRON_START_Y) {
									tempEnemy.posY -= Engine.INTERCEPTOR_SPEED;
								} else {
									if (tempEnemy.isShooting){
											WeaponManager.getInstance().addEnemyShot(tempEnemy.posX, tempEnemy.posY);
											tempEnemy.isShooting = false;
									}
									if (!tempEnemy.isLockedOn) {
										tempEnemy.lockOnPosX = Engine.playerBankPosX;
										tempEnemy.isLockedOn = true;
										tempEnemy.incrementXToTarget = (float) (
												(tempEnemy.lockOnPosX - 
														tempEnemy.posX) / 
														(tempEnemy.posY  / (Engine.INTERCEPTOR_SPEED * 4)));
									}								
									tempEnemy.posX += tempEnemy.incrementXToTarget;
									tempEnemy.posY -= (Engine.INTERCEPTOR_SPEED * 4);
									if (tempEnemy.posY < Engine.SQUADRON_MIN_Y) {
										tempEnemy.isDestroyed = true;
										tempSquadron.increaseEnemiesDestroyed();
									}
								}
								break;							

							case Engine.TYPE_SCOUT: // SCOUT
								if (tempEnemy.posY >= Engine.SQUADRON_START_Y) {
									tempEnemy.posY -= Engine.SCOUT_SPEED;
								}else {
									if (tempEnemy.isShooting){										
											WeaponManager.getInstance().addEnemyShot(tempEnemy.posX, tempEnemy.posY);
											tempEnemy.isShooting = false;										
									}
									tempEnemy.posX = tempEnemy.getNextScoutX();
									tempEnemy.posY = tempEnemy.getNextScoutY();
									tempEnemy.posT += Engine.SCOUT_SPEED;
									if (tempEnemy.posY < Engine.SQUADRON_MIN_Y) {
										tempEnemy.isDestroyed = true;
										tempSquadron.increaseEnemiesDestroyed();
									}
								}
								break;

							case Engine.TYPE_WARSHIP: // WARSHIP
								if (tempEnemy.posY >= Engine.SQUADRON_START_Y) { 
									tempEnemy.posY -= Engine.WARSHIP_SPEED;								
								} else {
									if (tempEnemy.isShooting){
											WeaponManager.getInstance().addEnemyShot(tempEnemy.posX, tempEnemy.posY);
											tempEnemy.isShooting = false;										
									}		
									if (!tempEnemy.isLockedOn) {
										tempEnemy.lockOnPosX = randomPos.nextFloat() * 3;
										tempEnemy.isLockedOn = true;
										tempEnemy.incrementXToTarget = 
												(float) ((tempEnemy.lockOnPosX - tempEnemy.posX) / (tempEnemy.posY/ (Engine.WARSHIP_SPEED * 4)));
									}
									tempEnemy.posY -= Engine.WARSHIP_SPEED * 2;
									tempEnemy.posX += tempEnemy.incrementXToTarget;
									if (tempEnemy.posY < Engine.SQUADRON_MIN_Y) {								
										tempEnemy.isDestroyed = true;
										tempSquadron.increaseEnemiesDestroyed();
									}
								}
								break;
							}

							// Draw the enemy, rollback matrix, etc.
							gl.glTranslatef(tempEnemy.posX, tempEnemy.posY, 0f);
							gl.glMatrixMode(GL10.GL_TEXTURE);
							gl.glLoadIdentity();														
							tempEnemy.draw(gl, spriteSheet);
							gl.glPopMatrix();
							gl.glLoadIdentity();
						}
					}
				}
			}
			// To free resources, if the squadron have been destroyed, we will delete it from the list
			if (SquadronManager.getInstance().getSquadronList().get(0).isDestroyed()){
				SquadronManager.getInstance().getSquadronList().remove(0);
			}

		}
	}

	/**
	 * Sax parser handler to parse the level files.
	 * See also assets/level.dtd
	 */
	private class SquadronContentHandler extends DefaultHandler {

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

			ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
			int enemy;
			int xpos;
			int dir;
			int numEnemies = 0;

			if (localName.equals("squadron")) {

				int ypos = Integer.parseInt(attributes.getValue("ypos"));	            	
				enemy = Integer.parseInt(attributes.getValue("enemy"));
				dir = Integer.parseInt(attributes.getValue("dir"));


				// Enemy1	                
				xpos = Integer.parseInt(attributes.getValue("xpos1"));
				enemyList.add(new Enemy (enemy,dir,xpos,ypos));
				numEnemies ++;

				// Enemy2	                
				xpos = Integer.parseInt(attributes.getValue("xpos2"));
				enemyList.add(new Enemy (enemy,dir,xpos,ypos));
				numEnemies ++;

				// Enemy3
				if (attributes.getValue("xpos3")!=null){	                	
					xpos = Integer.parseInt(attributes.getValue("xpos3"));
					enemyList.add(new Enemy (enemy,dir,xpos,ypos));
					numEnemies ++;
				}

				// Enemy4
				if (attributes.getValue("xpos4")!=null){	                	
					xpos = Integer.parseInt(attributes.getValue("xpos4"));
					enemyList.add(new Enemy (enemy,dir,xpos,ypos));
					numEnemies ++;
				}

				// Enemy5
				if (attributes.getValue("xpos5")!=null){	                	
					xpos = Integer.parseInt(attributes.getValue("xpos5"));
					enemyList.add(new Enemy (enemy,dir,xpos,ypos));
					numEnemies ++;
				}

				System.out.print("Squadron ypos:"+ypos);
				Squadron sq = new Squadron(enemyList,enemy,numEnemies,0);
				squadronList.add(sq);
			}
		}
	}	    
}
