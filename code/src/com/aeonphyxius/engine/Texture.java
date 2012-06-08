package com.aeonphyxius.engine;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

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

public class Texture {

	private int[] textures = new int[3];			// textures array

	/**
	 * Generates textures
	 * @param gl
	 */
	public Texture(GL10 gl){
		gl.glGenTextures(2, textures, 0);

	}
	
	/**
	 * To load the texture given by parameter. 
	 * @param gl
	 * @param texture
	 * @param context
	 * @param textureNumber
	 * @return array containing the loaded textures ids
	 */
	public int[] loadTexture(GL10 gl,int texture, Context context,int textureNumber) {
		InputStream imagestream = context.getResources().openRawResource(texture);
		Bitmap bitmap = null;
		try {

			bitmap = BitmapFactory.decodeStream(imagestream);

		}catch(Exception e){

		}finally {
			//Always clear and close
			try {
				imagestream.close();
				imagestream = null;
			} catch (IOException e) {
			}
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
