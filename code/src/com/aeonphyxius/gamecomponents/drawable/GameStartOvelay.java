package com.aeonphyxius.gamecomponents.drawable;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.DrawableComponent;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;

public class GameStartOvelay extends EngineGL implements DrawableComponent {

	private static GameStartOvelay instance = null;	
	private Vector<TextureRegion> playerTexturesList;	// Texture region containing the icon to show
	private double timeStamp;
	private double elapsed;
	private int frame;
	private int animation;
	private final int ANIM_FRAMES = 10;
	private final int MAX_FRAMES = 3;


	
	public static GameStartOvelay getInstance() {
		if (instance == null) {
			instance = new GameStartOvelay();
		}
		return instance;
	}
	
	/**
	 * 
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
	
	public void resetOverlay(){
		timeStamp = System.currentTimeMillis();
		frame = 0;
		animation = 0;		
	}

	/**
	 * 
	 * @param gl
	 * @param spriteSheet
	 */
	public void draw(GL10 gl, int[] spriteSheet) {
		
		elapsed += System.currentTimeMillis() - timeStamp;
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef(.05f + (0.01f * animation), .05f+ (0.01f * animation), 1f);
		gl.glTranslatef(5.f, 5.f, 0f);
		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
		
		super.draw(gl, spriteSheet, Engine.TEXTURES, playerTexturesList.get(frame));
		gl.glPopMatrix();
		gl.glLoadIdentity();
		
		if ( elapsed > Engine.ANIMATION_SLEEP){
			elapsed = 0;
			timeStamp = System.currentTimeMillis();
			if (animation < ANIM_FRAMES){
				animation ++ ;
			}else{
				animation = 0;
				frame++;
				if (frame == MAX_FRAMES){					
					Engine.GAMESTATUS = Engine.GameSatus.PLAYING;			
				}
			}			
		}
	}

}
