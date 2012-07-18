package com.aeonphyxius.engine;

import java.util.Iterator;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.gamecomponents.drawable.Enemy;
import com.aeonphyxius.gamecomponents.drawable.Player;
import com.aeonphyxius.gamecomponents.drawable.Weapon;
import com.aeonphyxius.gamecomponents.drawable.overlay.GameOverOvelay;
import com.aeonphyxius.gamecomponents.drawable.overlay.GameStartOvelay;
import com.aeonphyxius.gamecomponents.drawable.overlay.LevelCompleteOverOvelay;
import com.aeonphyxius.gamecomponents.drawable.overlay.PlayerDestructionOverlay;
import com.aeonphyxius.gamecomponents.manager.BackGroundManager;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;
import com.aeonphyxius.gamecomponents.manager.HUDManager;
import com.aeonphyxius.gamecomponents.manager.LevelManager;
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

public class GameLogic implements Renderer {

	private long loopStart = 0;						// game loop start time
	private long loopEnd = 0;						// game loop loop end time
	private long loopRunTime = 0;					// game loop running time

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

		// States logic
		switch (Engine.GameSatus){

		case START:
			BackGroundManager.getInstance().scrollBackground(gl);
			GameStartOvelay.getInstance().draw(gl);
			break;

		case PLAYING:
			Engine.yScroll += Engine.MOVING_OUT_SCOPE;
			BackGroundManager.getInstance().scrollBackground(gl);		
			Player.getInstance().draw(gl);
			WeaponManager.getInstance().drawWeapon(gl);
			SquadronManager.getInstance().draw(gl);
			HUDManager.getInstance().draw(gl);
			detectCollisions();		
			ExplosionManager.getInstance().draw(gl);
			break;

		case DESTROYED:
			BackGroundManager.getInstance().scrollBackground(gl);
			PlayerDestructionOverlay.getInstance().draw(gl);			
			break;

		case GAMEOVER:
			BackGroundManager.getInstance().scrollBackground(gl);
			GameOverOvelay.getInstance().draw(gl);
			break;
			
		case END:			
			Engine.gameActivity.finish();
			break;
		case LEVEL_COMPLETE:

			BackGroundManager.getInstance().scrollBackground(gl);
			LevelCompleteOverOvelay.getInstance().draw(gl);			

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

		Squadron iterSquadron;
		Weapon iterWeapon;		
		Enemy iterEnemy;

		for (Iterator<Squadron> iteratorS = SquadronManager.getInstance().getSquadronList().iterator(); iteratorS.hasNext();) {		
			iterSquadron = iteratorS.next();

			if (!iterSquadron.isDestroyed() && iterSquadron.isVisible() ){
				for (Iterator<Enemy> iteratorE = iterSquadron.getEnemyList().iterator(); iteratorE.hasNext();){		
					iterEnemy = iteratorE.next();
					if (!iterEnemy.isDestroyed){						
					/*	if (BoundingBox.getInstance().overlapsPlayer(iterEnemy)){
							Player.getInstance().applyDamage();
							Player.getInstance().increasePoints(); // TODO : add enemy type
							iterEnemy.applyDamage();
							if (iterEnemy.isDestroyed){ // Add this destroyed enemy to the counter at Squadron level
								iterSquadron.increaseEnemiesDestroyed();
							}	
						}*/
	
						for (Iterator<Weapon> iteratorW = WeaponManager.getInstance().getPlayeFireList().iterator(); iteratorW.hasNext();) {				
							iterWeapon = iteratorW.next();
							if (iterWeapon.isFired && BoundingBox.getInstance().overlapsEnemy(iterEnemy,iterWeapon)){
								Player.getInstance().increasePoints(); // TODO : add enemy type
								iterEnemy.applyDamage();
								if (iterEnemy.isDestroyed){ // Add this destroyed enemy to the counter at Squadron level
									iterSquadron.increaseEnemiesDestroyed();
								}	
								iterWeapon.isFired = false;
							}
						}
					}
				}
			}			
		}
		
		for (Iterator<Weapon> i = WeaponManager.getInstance().getEnemyFireList().iterator(); i.hasNext();) {				
			iterWeapon = i.next();								
			if (iterWeapon.isFired && BoundingBox.getInstance().overlapsPlayer(iterWeapon)){
				Player.getInstance().applyDamage();
				iterWeapon.isFired = false;
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
		try {
			TextureManager.getInstance().loadTextures(gl);
			LevelManager.getInstance().loadLevelsData();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		
		try{
			LevelManager.getInstance().loadCurrentLevelData(gl);
			SquadronManager.getInstance().loadSquadronsLevel(LevelManager.getInstance().getCurrentLevel());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
