package com.aeonphyxius.engine;

import java.util.Random;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.gamecomponents.drawable.Enemy;
import com.aeonphyxius.gamecomponents.drawable.Player;
import com.aeonphyxius.gamecomponents.drawable.Weapon;
import com.aeonphyxius.gamecomponents.manager.BackGroundManager;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;
import com.aeonphyxius.gamecomponents.manager.HUDManager;
import com.aeonphyxius.gamecomponents.manager.Squadron;
import com.aeonphyxius.gamecomponents.manager.SquadronManager;
import com.aeonphyxius.gamecomponents.manager.WeaponManager;
import android.opengl.GLSurfaceView.Renderer;

/**
* GameRenderer Object.
* 
* <P>All related rendering operations.
*  
* <P>This class contains logic to display enemies, player and firepower. 
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class GameRenderer implements Renderer {


	//private Player player1 = new Player();
	private Texture textureLoader;
	private int[] spriteSheets = new int[4];
	private long loopStart = 0;
	private long loopEnd = 0;
	private long loopRunTime = 0;
	

	
	@Override
	public void onDrawFrame(GL10 gl) {
		loopStart = System.currentTimeMillis();
		try {
			if (loopRunTime < Engine.GAME_THREAD_FPS_SLEEP) {
				Thread.sleep(Engine.GAME_THREAD_FPS_SLEEP - loopRunTime);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		BackGroundManager.getInstance().scrollBackground(gl);

		Player.getInstance().draw(gl, spriteSheets);
		WeaponManager.getInstance().drawWeapon(gl, spriteSheets);
		moveEnemy(gl);
		HUDManager.getInstance().draw(gl, spriteSheets);
		detectCollisions();		
		ExplosionManager.getInstance().draw(gl, spriteSheets);

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		loopEnd = System.currentTimeMillis();
		loopRunTime = ((loopEnd - loopStart));
	}



	/**
	 * Moves all the enemies on screen. Also moves down the enemies above the visible area
	 * @param gl
	 */
	private void moveEnemy(GL10 gl) {
		int squadronNum = SquadronManager.getInstance().getSquadronList().size();		
		Squadron tempSquadron;
		/*Enemy temp = new Enemy(1,1,1,1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(.15f, .15f, 1f);
		gl.glTranslatef(Engine.playerBankPosX, Engine.PLAYER_POS_Y+0.8f, 0f);
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glTranslatef(0.75f, .25f, 0.0f);						
		temp.draw(gl, spriteSheets);
		gl.glPopMatrix();
		gl.glLoadIdentity();*/
		
		
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
						
							/*//if (tempSquadron.getSquadronPosY() < Engine.SQUADRON_MIN_Y) {
							if (tempEnemy.posY < Engine.SQUADRON_MIN_Y) {
								tempEnemy.posY = ((randomPos.nextFloat() * 4) + 4);
								tempEnemy.posX = randomPos.nextFloat() * 3;
								tempEnemy.isLockedOn = false;
								tempEnemy.lockOnPosX = 0;
							}*/
							gl.glMatrixMode(GL10.GL_MODELVIEW);
							gl.glLoadIdentity();
							gl.glPushMatrix();
							gl.glScalef(.15f, .15f, 1f);
							
							/*if (tempEnemy.posY >= 3) {
								tempEnemy.posY -= Engine.INTERCEPTOR_SPEED;
							} else {
								if (!tempEnemy.isLockedOn) {
									tempEnemy.lockOnPosX = Engine.playerBankPosX;
									tempEnemy.isLockedOn = true;
									tempEnemy.incrementXToTarget = (float) (
											(tempEnemy.lockOnPosX - 
											tempEnemy.posX) / 
											(tempEnemy.posY  / (Engine.INTERCEPTOR_SPEED * 4)));
								}*/
								//tempSquadron.setSquadronPosY(tempSquadron.getSquadronPosY()-(Engine.INTERCEPTOR_SPEED * 4));
								//tempEnemy.posX += tempEnemy.incrementXToTarget;
								tempEnemy.posY -= (Engine.INTERCEPTOR_SPEED * 4);
		
							//}
							gl.glTranslatef(tempEnemy.posX, tempEnemy.posY, 0f);
							gl.glMatrixMode(GL10.GL_TEXTURE);
							gl.glLoadIdentity();
							gl.glTranslatef(0.235f, .25f, 0.0f);							
							tempEnemy.draw(gl, spriteSheets);
							gl.glPopMatrix();
							gl.glLoadIdentity();
						}
					}

					break;
				case Engine.TYPE_SCOUT:
					for (int i = 0; i < enemyNum; i++){
						Enemy tempEnemy = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(i);
						if (!tempEnemy.isDestroyed){							
						
							if (tempEnemy.posY < Engine.SQUADRON_MIN_Y) {
								tempEnemy.posY = ((randomPos.nextFloat() * 4) + 4);							
								tempEnemy.isLockedOn = false;
								tempEnemy.posT = Engine.SCOUT_SPEED;
								tempEnemy.lockOnPosX = tempEnemy.getNextScoutX();
								tempEnemy.lockOnPosY = tempEnemy.getNextScoutY();
								
								if (tempEnemy.attackDirection == Engine.ATTACK_LEFT) {
									tempEnemy.posX = 0;
								} else {
									tempEnemy.posX = 3f;
								}
							}
							gl.glMatrixMode(GL10.GL_MODELVIEW);
							gl.glLoadIdentity();
							gl.glPushMatrix();
							gl.glScalef(.15f, .15f, 1f);
		
							if (tempEnemy.posY >= 2.75f) {
								tempEnemy.posY -= Engine.SCOUT_SPEED;
		
							} else {
								tempEnemy.posX = tempEnemy.getNextScoutX();
								tempEnemy.posY = tempEnemy.getNextScoutY();
								tempEnemy.posT += Engine.SCOUT_SPEED;
							}
							gl.glTranslatef(tempEnemy.posX, tempEnemy.posY, 0f);
							gl.glMatrixMode(GL10.GL_TEXTURE);
							gl.glLoadIdentity();
							gl.glTranslatef(0.50f, .25f, 0.0f);
							tempEnemy.draw(gl, spriteSheets);
							gl.glPopMatrix();
							gl.glLoadIdentity();
						}
					}
					break;
					
					
				case Engine.TYPE_WARSHIP:
					for (int i = 0; i < enemyNum; i++){
						Enemy tempEnemy = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(i);
						if (!tempEnemy.isDestroyed){							
						
							if (tempEnemy.posY < Engine.SQUADRON_MIN_Y) {
								tempEnemy.posY = ((randomPos.nextFloat() * 4) + 4);
								tempEnemy.posX = randomPos.nextFloat() * 3;
								tempEnemy.isLockedOn = false;
								tempEnemy.lockOnPosX = 0;
							}
							gl.glMatrixMode(GL10.GL_MODELVIEW);
							gl.glLoadIdentity();
							gl.glPushMatrix();
							gl.glScalef(.15f, .15f, 1f);
		
							if (tempEnemy.posY >= 3) {
								tempEnemy.posY -= Engine.WARSHIP_SPEED;
		
							} else {
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
							gl.glTranslatef(0.75f, .25f, 0.0f);
							tempEnemy.draw(gl, spriteSheets);
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
	 * Detects collision between all the weapons on screen and the enemies. 
	 */
	private void detectCollisions() {
		Enemy tempEnemy;
		int weaponsSize = WeaponManager.getInstance().getPlayeFireList().size();
		int squadronNum = SquadronManager.getInstance().getSquadronList().size();
		int enemyNum;
		Weapon tempWeapon;		
		
		for (int sqNum = 0; sqNum < squadronNum; sqNum++) { // loop all the squadrons
			if (!SquadronManager.getInstance().getSquadronList().get(sqNum).isDestroyed() ){
				enemyNum = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().size();						
				for (int sqMember = 0; sqMember < enemyNum; sqMember++){	// loop all the enemies inside the squadron
					tempEnemy  = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember);								
					if (!tempEnemy.isDestroyed){
						
						if (overlaps(Engine.playerBankPosX,Engine.PLAYER_POS_Y,Engine.playerBankPosX+0.6f,Engine.PLAYER_POS_Y+0.6f,tempEnemy.posX,tempEnemy.posY,tempEnemy.posX+0.6f,tempEnemy.posY+0.6f )){							
							Player.getInstance().applyDamage();
							Player.getInstance().increasePoints(); // TODO : add enemy type
							SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember).applyDamage();
							if (SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember).isDestroyed){ // Add this destroyed enemy to the counter at Squadron level
								SquadronManager.getInstance().getSquadronList().get(sqNum).increaseEnemiesDestroyed();
							}	
						}
						if (!tempEnemy.isDestroyed ){
							for (int shootNum =0; shootNum  < weaponsSize; shootNum++) {
								tempWeapon = WeaponManager.getInstance().getPlayeFireList().get(shootNum);							
								if (overlaps(tempEnemy.posX,tempEnemy.posY,tempEnemy.posX+0.6f,tempEnemy.posY+0.6f,tempWeapon.posX,tempWeapon.posY,tempWeapon.posX+0.30f,tempWeapon.posY+0.3f )){
									
									Player.getInstance().increasePoints(); // TODO : add enemy type
									SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember).applyDamage();
									if (SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember).isDestroyed){ // Add this destroyed enemy to the counter at Squadron level
										SquadronManager.getInstance().getSquadronList().get(sqNum).increaseEnemiesDestroyed();
									}	
									WeaponManager.getInstance().getPlayeFireList().get(shootNum).shootFired = false;
								}
							}
						}
						
	
					}
				}
			}
			
		}
		
		
		/*for (int shootNum =0; shootNum  < weaponsSize; shootNum++) {
				if (WeaponManager.getInstance().getPlayeFireList().get(shootNum).shootFired){
					for (int sqNum = 0; sqNum < squadronNum; sqNum++) { // loop all the squadrons
						if (!SquadronManager.getInstance().getSquadronList().get(sqNum).isDestroyed() ){
								//&& SquadronManager.getInstance().getSquadronList().get(sqNum).getSquadronPosY() < 5.25) { // TODO : Fix with a constant
							
							enemyNum = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().size();						
							for (int sqMember = 0; sqMember < enemyNum; sqMember++){	// loop all the enemies inside the squadron
								tempEnemy  = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember);								
								if (!tempEnemy.isDestroyed){
									tempWeapon = WeaponManager.getInstance().getPlayeFireList().get(shootNum);
									if ((tempWeapon.posY >= tempEnemy.posY - 1 && tempWeapon.posY <= tempEnemy.posY ) &&										
										(tempWeapon.posX <= tempEnemy.posX + 1 && tempWeapon.posX >= tempEnemy.posX )) {
										
										//WeaponManager.getInstance().getPlayeFireList().remove(shootNum).shootFired = false;
										
										SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember).applyDamage();
										if (SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember).isDestroyed){ // Add this destroyed enemy to the counter at Squadron level
											SquadronManager.getInstance().getSquadronList().get(sqNum).increaseEnemiesDestroyed();
										}									
																				
									}
								}
							}
						}
					}
			}		
		}*/
	}

	   
    public boolean overlaps(float minX1, float minY1, float maxX1, float maxY1,float minX2, float minY2, float maxX2, float maxY2) {            
            if(maxX1 <= minX2 || minX1 >= maxX2)
                    return false;

            if(maxY1 <= minY2 || minY1 >= maxY2)
                    return false;

            return true;
    }

	

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
		MusicManager.getInstance().playMusic();		
		textureLoader = new Texture(gl);
		spriteSheets = textureLoader.loadTexture(gl, Engine.CHARACTER_SHEET,Engine.context, 1);
		spriteSheets = textureLoader.loadTexture(gl, Engine.WEAPONS_SHEET,Engine.context, 2);
		spriteSheets = textureLoader.loadTexture(gl, Engine.PLAYER_SHEET,Engine.context, 3);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		BackGroundManager.getInstance().loadTextures(gl);
		try{
			SquadronManager.getInstance().loadSquadronsLevel(1);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
