package com.aeonphyxius.engine;

import java.io.InputStream;
import javax.microedition.khronos.opengles.GL10;

import com.aeonphyxius.gamecomponents.drawable.Explosion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

/**
 * Texture Object.
 * 
 * <P>
 * Texture class, containing all the information needed to manage textures
 * 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class TextureManager {

	private int[] textures = new int[10];					// textures array
	private static TextureManager instance = null;			// Singleton implementation
	
	public static final String TEXTURE_PLAYER_FILE = "img_ship.png";
	public static final String TEXTURE_PLAYER_LEFT_FILE = "img_ship_left.png";
	public static final String TEXTURE_PLAYER_RIGHT_FILE = "img_ship_right.png";


	/**
	 * Singleton implementation of the unique instance of this class
	 * @return
	 */
	public static TextureManager getInstance() {
		if (instance == null) {
			instance = new TextureManager();
		}
		return instance;
	}

	private TextureManager(){
		
	}
	
	
	public int[] getTextures() {
		return textures;
	}

	public void setTextures(int[] textures) {
		this.textures = textures;
	}

	/**
	 * Generates textures
	 * @param gl
	 */
	public TextureManager(GL10 gl){
		gl.glGenTextures(3, textures, 0);

	}

	/**
	 * 
	 * @param gl
	 * @param context
	 */
	public void loadPlayerTexture(GL10 gl, Context context){
		loadPlayerTexture(gl,TEXTURE_PLAYER_FILE,context, Engine.TEXTURE_PLAYER);
		loadPlayerTexture(gl,TEXTURE_PLAYER_RIGHT_FILE,context, Engine.TEXTURE_PLAYER_RIGHT);
		loadPlayerTexture(gl,TEXTURE_PLAYER_LEFT_FILE,context, Engine.TEXTURE_PLAYER_LEFT);
	}
	
	/**
	 * 
	 * @param gl
	 * @param fileName
	 * @param context
	 * @param textureNumber
	 */
	private void loadPlayerTexture(GL10 gl,String fileName, Context context,int textureNumber) {

		InputStream imagestream;
		Bitmap bitmap = null;
		try {
			imagestream = context.getAssets().open(fileName);			
			bitmap = BitmapFactory.decodeStream(imagestream);
			imagestream.close();
			imagestream = null;
		}catch(Exception e){

		}		

		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[textureNumber]);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		bitmap.recycle();
	}
	
	
	/**
	 * To load the texture given by parameter. 
	 * @param gl
	 * @param texture
	 * @param context
	 * @param textureNumber
	 * @return array containing the loaded textures ids
	 */
	//public int[] loadTexture(GL10 gl,int texture, Context context,int textureNumber) {
	public int[] loadTexture(GL10 gl,String fileName, Context context,int textureNumber) {
		//InputStream imagestream = context.getResources().openRawResource(texture);
		InputStream imagestream;
		Bitmap bitmap = null;
		try {
			imagestream = context.getAssets().open(fileName);			
			bitmap = BitmapFactory.decodeStream(imagestream);
			imagestream.close();
			imagestream = null;
		}catch(Exception e){

		}		

		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[textureNumber - 1]);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		bitmap.recycle();

		return textures;
	}
}
