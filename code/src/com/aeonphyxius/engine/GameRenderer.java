package com.aeonphyxius.engine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.aeonphyxius.data.LevelData;
import com.aeonphyxius.gamecomponents.drawable.Enemy;
import com.aeonphyxius.gamecomponents.drawable.GameStartOvelay;
import com.aeonphyxius.gamecomponents.drawable.Player;
import com.aeonphyxius.gamecomponents.drawable.PlayerDestructionOverlay;
import com.aeonphyxius.gamecomponents.drawable.Weapon;
import com.aeonphyxius.gamecomponents.manager.BackGroundManager;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;
import com.aeonphyxius.gamecomponents.manager.HUDManager;
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
		
		switch (Engine.GAMESTATUS){
		
		case START:
			BackGroundManager.getInstance().scrollBackground(gl);
			GameStartOvelay.getInstance().draw(gl, spriteSheets);
			
			break;
		
		case PLAYING:
			BackGroundManager.getInstance().scrollBackground(gl);		
			Player.getInstance().draw(gl, spriteSheets);
			WeaponManager.getInstance().drawWeapon(gl, spriteSheets);
			SquadronManager.getInstance().draw(gl, spriteSheets);
			HUDManager.getInstance().draw(gl, spriteSheets);
			detectCollisions();		
			ExplosionManager.getInstance().draw(gl, spriteSheets);
			break;
		
		case DESTROYED:
			BackGroundManager.getInstance().scrollBackground(gl);			
			ExplosionManager.getInstance().draw(gl, spriteSheets);
			PlayerDestructionOverlay.getInstance().draw(gl, spriteSheets);			
			break;
			
		case GAMEOVER:
			break;

		}
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		loopEnd = System.currentTimeMillis();
		loopRunTime = ((loopEnd - loopStart));
	}



	/**
	 * Detects collision between all the weapons on screen and the enemies. 
	 */
	private void detectCollisions() {
		Enemy tempEnemy;
		int weaponsSize = WeaponManager.getInstance().getPlayeFireList().size();
		int weaponsEnemySize = WeaponManager.getInstance().getEnemyFireList().size();
		int squadronNum = SquadronManager.getInstance().getSquadronList().size();
		int enemyNum;
		Weapon tempWeapon;		
		
		for (int sqNum = 0; sqNum < squadronNum; sqNum++) { // loop all the squadrons
			if (!SquadronManager.getInstance().getSquadronList().get(sqNum).isDestroyed() ){
				enemyNum = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().size();						
				for (int sqMember = 0; sqMember < enemyNum; sqMember++){	// loop all the enemies inside the squadron
					tempEnemy  = SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember);								
					if (!tempEnemy.isDestroyed){						
						if (BoundingBox.getInstance().overlaps(Engine.playerBankPosX,Engine.PLAYER_POS_Y,Engine.playerBankPosX+0.6f,Engine.PLAYER_POS_Y+0.6f,tempEnemy.posX,tempEnemy.posY,tempEnemy.posX+0.6f,tempEnemy.posY+0.6f )){							
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
								if (tempWeapon.isFired && BoundingBox.getInstance().overlaps(tempEnemy.posX,tempEnemy.posY,tempEnemy.posX+0.6f,tempEnemy.posY+0.6f,tempWeapon.posX,tempWeapon.posY,tempWeapon.posX+0.30f,tempWeapon.posY+0.3f )){
									
									Player.getInstance().increasePoints(); // TODO : add enemy type
									SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember).applyDamage();
									if (SquadronManager.getInstance().getSquadronList().get(sqNum).getEnemyList().get(sqMember).isDestroyed){ // Add this destroyed enemy to the counter at Squadron level
										SquadronManager.getInstance().getSquadronList().get(sqNum).increaseEnemiesDestroyed();
									}	
									WeaponManager.getInstance().getPlayeFireList().get(shootNum).isFired = false;
								}
							}
							for (int shootNum =0; shootNum  < weaponsEnemySize; shootNum++) {
								tempWeapon = WeaponManager.getInstance().getEnemyFireList().get(shootNum);
								
								if (tempWeapon.isFired && BoundingBox.getInstance().overlaps(
										Engine.playerBankPosX,Engine.PLAYER_POS_Y,Engine.playerBankPosX+0.6f,Engine.playerBankPosX+0.6f,
										tempWeapon.posX,tempWeapon.posY,tempWeapon.posX+0.3f,tempWeapon.posY+0.3f )){
									Player.getInstance().applyDamage();
									WeaponManager.getInstance().getEnemyFireList().get(shootNum).isFired = false;
								}
							}							
						}
					}
				}
			}
			
		}

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
		spriteSheets = textureLoader.loadTexture(gl, Engine.TEXTURES_FILE,Engine.context, 1);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);

		BackGroundManager.getInstance().loadTextures(gl);
		try{
			SquadronManager.getInstance().loadSquadronsLevel(LevelData.getInstance().getCurrentLevel());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
