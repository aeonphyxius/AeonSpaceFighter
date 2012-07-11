package com.aeonphyxius.gamecomponents.drawable.overlay;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.Overlay;
import com.aeonphyxius.engine.TextureRegion;
import com.aeonphyxius.engine.Engine.GAMESTATUS;
import com.aeonphyxius.gamecomponents.manager.ExplosionManager;
import com.aeonphyxius.gamecomponents.manager.LevelManager;
import com.aeonphyxius.gamecomponents.manager.SquadronManager;
import com.aeonphyxius.gamecomponents.manager.WeaponManager;

/**
 * GameOverOvelay Object.
 * 
 * <P>
 * Game Over animaton overlay
 * 
 * <P>
 * This class contains logic to display animation when the games ends. Showing a Game Over text 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class LevelCompleteOverOvelay extends EngineGL implements Overlay {

	private static LevelCompleteOverOvelay instance = null;	// Singleton pattern implementation
	private TextureRegion gameOverTexture;					// Texture region containing the icon to show
	private double timeStamp;								// times tamp at the start of each iteration
	private double elapsed;									// elapsed time since last iteration	



	/**
	 * Singleton implementation of the unique instance of this class
	 * @return unique instance of GameOverOvelay
	 */
	public static LevelCompleteOverOvelay getInstance() {
		if (instance == null) {
			instance = new LevelCompleteOverOvelay();
		}
		return instance;
	}

	/**
	 * Creates the textures and initializes the animation
	 */
	private LevelCompleteOverOvelay() {

		//gameOverTexture = new TextureRegion( new float[] { 0.0f, 0.363f, 0.449f, 0.363f, 0.449f, 0.523f, 0.0f, 0.523f, });
		gameOverTexture = new TextureRegion(new float[]   { 0f, 1f, 1f, 1f, 1f, 0f, 0f, 0f, });

		timeStamp = System.currentTimeMillis();		
	}

	@Override
	public void resetOverlay(){
		timeStamp = System.currentTimeMillis();		
	}


	@Override
	public void draw(GL10 gl) {

		elapsed += System.currentTimeMillis() - timeStamp;

		// update the sprite position
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(1f,1f, 0f);
		gl.glTranslatef(0f, 0f, 0f);

		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();

		// draw the sprite
		draw(gl, Engine.TEXTURE_END_LEVEL, gameOverTexture);

		// restore Matrix transformations to its original configuration
		restoreMatrix(gl);

		// Continue the animation
		if ( elapsed > Engine.GAME_OVER_SLEEP){
			if (Engine.event != null){
				switch (Engine.event.getAction()){
				case MotionEvent.ACTION_DOWN:
					LevelManager.getInstance().increaseLevel();
					GameStartOvelay.getInstance().resetOverlay();
					WeaponManager.getInstance().resetWeapons();
					ExplosionManager.getInstance().resetExplosions();
					LevelManager.getInstance().loadCurrentLevelData(gl);
					try{
						SquadronManager.getInstance().loadSquadronsLevel(LevelManager.getInstance().getCurrentLevel());
					}catch (Exception e){
						e.printStackTrace();
					}
					this.resetOverlay();
					Engine.yScroll = 0;
					Engine.GameSatus = GAMESTATUS.START;


					break;
				}
			}
		}
	}

}
