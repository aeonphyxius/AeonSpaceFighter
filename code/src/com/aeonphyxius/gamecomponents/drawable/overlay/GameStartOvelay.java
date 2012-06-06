package com.aeonphyxius.gamecomponents.drawable.overlay;

import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.Overlay;
import com.aeonphyxius.engine.TextureRegion;

/**
 * GameStartOvelay Object.
 * 
 * <P>
 * Start animation overlay
 * 
 * <P>
 * This class contains logic to display animation before starting the game. Showing a count down from 3 to 1. 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */


public class GameStartOvelay extends EngineGL implements Overlay {

	private static GameStartOvelay instance = null;			// Singleton pattern implementation	
	private Vector<TextureRegion> playerTexturesList;		// Texture region containing the icon to show
	private double timeStamp;								// times tamp at the start of each iteration
	private double elapsed;									// elapsed time since last iteration	
	private int frame;										// Current frame number inside the iteration	
	private int animation;									// Current animation number inside the iteration	
	private final int ANIM_FRAMES = 15;						// Number of animations per number (small effect per number shown)
	private final int MAX_FRAMES = 3;						// Number of items to display (1,2,3)


	/**
	 * Singleton implementation of the unique instance of this class
	 * @return unique instance of GameStartOvelay
	 */
	public static GameStartOvelay getInstance() {
		if (instance == null) {
			instance = new GameStartOvelay();
		}
		return instance;
	}


	/**
	 * Creates the textures and initializes the animation
	 */
	private GameStartOvelay() {		
		playerTexturesList = new Vector<TextureRegion>();

		TextureRegion tempTextureRegion = new TextureRegion( new float[] { 0.910f, 0.390f, 0.959f, 0.390f, 0.959f, 0.470f, 0.910f, 0.470f, });
		playerTexturesList.add(tempTextureRegion); // Texture for number 3 texture

		tempTextureRegion =  new TextureRegion( new float[] { 0.841f, 0.390f, 0.895f, 0.390f, 0.895f, 0.470f, 0.841f, 0.470f, });
		playerTexturesList.add(tempTextureRegion); // Texture for number 2 texture

		tempTextureRegion =  new TextureRegion( new float[] { 0.777f, 0.390f, 0.820f, 0.390f, 0.820f, 0.470f, 0.77f, 0.470f, });
		playerTexturesList.add(tempTextureRegion); // Texture for number 1 texture

		timeStamp = System.currentTimeMillis();
		frame = 0;
		animation = 0;
	}

	@Override
	public void resetOverlay(){
		timeStamp = System.currentTimeMillis();
		frame = 0;
		animation = 0;		
	}

	@Override	
	public void draw(GL10 gl, int[] spriteSheet) {		
		elapsed += System.currentTimeMillis() - timeStamp;

		// update the sprite position
		update(gl, (0.05f + (0.01f * animation)), (0.05f+ (0.01f * animation)) , 1.0f, 3.0f ,3.0f , 0.0f );

		// draw the sprite
		draw(gl, spriteSheet, Engine.TEXTURES, playerTexturesList.get(frame));

		// restore Matrix transformations to its original configuration
		restoreMatrix(gl);


		// Continue the animation
		if ( elapsed > Engine.ANIMATION_SLEEP){
			elapsed = 0;
			timeStamp = System.currentTimeMillis();
			if (animation < ANIM_FRAMES){
				animation ++ ;
			}else{
				animation = 0;
				frame++;
				if (frame == MAX_FRAMES){					
					Engine.GameSatus = Engine.GAMESTATUS.PLAYING;			
				}
			}			
		}
	}
}
