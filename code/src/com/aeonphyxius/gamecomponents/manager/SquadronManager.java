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


public class SquadronManager {
	
	private static SquadronManager instance = null;	
	private ArrayList<Squadron> squadronList;
	
	protected SquadronManager() {
		squadronList = new ArrayList<Squadron>();
	}

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

	public void loadSquadronsLevel(int level) throws Exception{
		
		AssetManager assetManager = Engine.context.getAssets();
		String fileName = "level" + level +".xml";
		InputStream is;
		try {
			is = assetManager.open(fileName);
	        SAXParserFactory spf = SAXParserFactory.newInstance();
	        SAXParser sp = spf.newSAXParser();
	        XMLReader xmlReader = sp.getXMLReader();
	        xmlReader.setContentHandler(new LevelContentHandler());
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
		
		for (int sqNum = 0; sqNum< squadronNum ; sqNum++) {	
			tempSquadron = SquadronManager.getInstance().getSquadronList().get(sqNum);
			if (!tempSquadron.isDestroyed()) {
				Random randomPos = new Random();
				int enemyNum = tempSquadron.getEnemyList().size();		
				
				switch (tempSquadron.getSquadronEnemyType()) { 
				
				case Engine.TYPE_INTERCEPTOR: // Interceptor
					
					for (int sqPos = 0; sqPos < enemyNum; sqPos++){						
						Enemy tempEnemy = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqPos);
						if (!tempEnemy.isDestroyed){						
						
							//if (tempSquadron.getSquadronPosY() < Engine.SQUADRON_MIN_Y) {
							if (tempEnemy.posY < Engine.SQUADRON_MIN_Y) {
							/*	tempEnemy.posY = ((randomPos.nextFloat() * 4) + 4);
								tempEnemy.posX = randomPos.nextFloat() * 3;
								tempEnemy.isLockedOn = false;
								tempEnemy.lockOnPosX = 0;*/
								tempEnemy.isDestroyed = true;
							}
							gl.glMatrixMode(GL10.GL_MODELVIEW);
							gl.glLoadIdentity();
							gl.glPushMatrix();
							gl.glScalef(.15f, .15f, 1f);
							
							if (tempEnemy.posY >= Engine.SQUADRON_START_Y) {
								tempEnemy.posY -= Engine.INTERCEPTOR_SPEED;
							} else {
								if (!tempEnemy.isShooting){
									if( (Math.random())<0.05){
										WeaponManager.getInstance().addEnemyShot(tempEnemy.posX, tempEnemy.posY);
										tempEnemy.isShooting = true;
									}
								}
								
								if (!tempEnemy.isLockedOn) {
									tempEnemy.lockOnPosX = Engine.playerBankPosX;
									tempEnemy.isLockedOn = true;
									tempEnemy.incrementXToTarget = (float) (
											(tempEnemy.lockOnPosX - 
											tempEnemy.posX) / 
											(tempEnemy.posY  / (Engine.INTERCEPTOR_SPEED * 4)));
								}
								//tempSquadron.setSquadronPosY(tempSquadron.getSquadronPosY()-(Engine.INTERCEPTOR_SPEED * 4));
								tempEnemy.posX += tempEnemy.incrementXToTarget;
								tempEnemy.posY -= (Engine.INTERCEPTOR_SPEED * 4);
		
							}
							gl.glTranslatef(tempEnemy.posX, tempEnemy.posY, 0f);
							gl.glMatrixMode(GL10.GL_TEXTURE);
							gl.glLoadIdentity();
							//gl.glTranslatef(0.235f, .25f, 0.0f);							
							tempEnemy.draw(gl, spriteSheet);
							gl.glPopMatrix();
							gl.glLoadIdentity();
						}
					}

					break;
				case Engine.TYPE_SCOUT:
					for (int sqPos = 0; sqPos < enemyNum; sqPos++){
						Enemy tempEnemy = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqPos);
						if (!tempEnemy.isDestroyed){							
						
							if (tempEnemy.posY < Engine.SQUADRON_MIN_Y) {
								/*tempEnemy.posY = ((randomPos.nextFloat() * 4) + 4);							
								tempEnemy.isLockedOn = false;
								tempEnemy.posT = Engine.SCOUT_SPEED;
								tempEnemy.lockOnPosX = tempEnemy.getNextScoutX();
								tempEnemy.lockOnPosY = tempEnemy.getNextScoutY();
								
								if (tempEnemy.attackDirection == Engine.ATTACK_LEFT) {
									tempEnemy.posX = 0;
								} else {
									tempEnemy.posX = 3f;
								}*/
								tempEnemy.isDestroyed = true;
							}
							gl.glMatrixMode(GL10.GL_MODELVIEW);
							gl.glLoadIdentity();
							gl.glPushMatrix();
							gl.glScalef(.15f, .15f, 1f);
		
							if (tempEnemy.posY >= Engine.SQUADRON_START_Y) {
								tempEnemy.posY -= Engine.SCOUT_SPEED;
		
							} else {
								if (!tempEnemy.isShooting){
									if( (Math.random())<0.05){
										WeaponManager.getInstance().addEnemyShot(tempEnemy.posX, tempEnemy.posY);
										tempEnemy.isShooting = true;
									}
								}
								tempEnemy.posX = tempEnemy.getNextScoutX();
								tempEnemy.posY = tempEnemy.getNextScoutY();
								tempEnemy.posT += Engine.SCOUT_SPEED;
							}
							gl.glTranslatef(tempEnemy.posX, tempEnemy.posY, 0f);
							gl.glMatrixMode(GL10.GL_TEXTURE);
							gl.glLoadIdentity();
							//gl.glTranslatef(0.50f, .25f, 0.0f);
							tempEnemy.draw(gl, spriteSheet);
							gl.glPopMatrix();
							gl.glLoadIdentity();
						}
					}
					break;
					
					
				case Engine.TYPE_WARSHIP:
					for (int sqPos = 0; sqPos < enemyNum; sqPos++){
						Enemy tempEnemy = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqPos);

						if (!tempEnemy.isDestroyed){							
						
							if (tempEnemy.posY < Engine.SQUADRON_MIN_Y) {/*
								tempEnemy.posY = ((randomPos.nextFloat() * 4) + 4);
								tempEnemy.posX = randomPos.nextFloat() * 3;
								tempEnemy.isLockedOn = false;
								tempEnemy.lockOnPosX = 0;*/
								tempEnemy.isDestroyed = true;
							}
							gl.glMatrixMode(GL10.GL_MODELVIEW);
							gl.glLoadIdentity();
							gl.glPushMatrix();
							gl.glScalef(.15f, .15f, 1f);
		
							if (tempEnemy.posY >= Engine.SQUADRON_START_Y) { 
								tempEnemy.posY -= Engine.WARSHIP_SPEED;								
							} else {
								if (!tempEnemy.isShooting){
									if( (Math.random())<0.05){
										WeaponManager.getInstance().addEnemyShot(tempEnemy.posX, tempEnemy.posY);
										tempEnemy.isShooting = true;
									}
								}		
								if (!tempEnemy.isLockedOn) {
									tempEnemy.lockOnPosX = randomPos.nextFloat() * 3;
									tempEnemy.isLockedOn = true;
									tempEnemy.incrementXToTarget = 
											(float) ((tempEnemy.lockOnPosX - tempEnemy.posX) / (tempEnemy.posY/ (Engine.WARSHIP_SPEED * 4)));
								}
								tempEnemy.posY -= Engine.WARSHIP_SPEED * 2;
								tempEnemy.posX += tempEnemy.incrementXToTarget;
							}
							gl.glTranslatef(tempEnemy.posX, tempEnemy.posY, 0f);
							gl.glMatrixMode(GL10.GL_TEXTURE);
							gl.glLoadIdentity();
							tempEnemy.draw(gl, spriteSheet);
							gl.glPopMatrix();
							gl.glLoadIdentity();
						}
					}
					break;
				}
			}
		}
	}
	
		/**
		* Sax parser handler to parse the level files.
		* See also assets/level.dtd
		*/
	    private class LevelContentHandler extends DefaultHandler {

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
