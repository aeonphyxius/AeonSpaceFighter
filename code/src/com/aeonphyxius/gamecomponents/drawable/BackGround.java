package com.aeonphyxius.gamecomponents.drawable;

import java.io.InputStream;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

/**
 * BackGround Object.
 *
 * <P> Class to manage a background layer. Contains all logic to display an image as a background,
 *
 * <P> and load the texture.
 *
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class BackGround extends EngineGL{


	private float scroll; // Background layer scroll speed
	private float scrollElapse; // Scroll elapse time
	private float scaleX; // Background scroll image X scale
	private float scaleY; // Background scroll image Y scale
	private float translateX; // Background scroll image X translation
	private TextureRegion backgroundTexture; // Background texture region
	private int[] textures = new int[1]; // Background texture


	public float getScaleY() {
		return scaleY;
	}


	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}


	public float getScrollElapse() {
		return scrollElapse;
	}


	public void setScrollElapse(float scrollElapse) {
		this.scrollElapse = scrollElapse;
	}


	public float getScroll() {
		return scroll;
	}


	public void setScroll(float scroll) {
		this.scroll = scroll;
	}


	public float getScaleX() {
		return scaleX;
	}


	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}


	public float getTranslateX() {
		return translateX;
	}


	public void setTranslateX(float translateX) {
		this.translateX = translateX;
	}


	/**
	 * Default class constructor. Will load default values for scroll elapse, scaleX and translateX
	 */
	public BackGround() {
		scroll = 0.f;
		scrollElapse = Engine.SCROLL_BACKGROUND_1;
		backgroundTexture = new TextureRegion (new float [] {0.0f,0.0f,1.0f,0.0f,1.0f,1.0f,0.0f,1.0f,});
	}

	/**
	 * Class constructor, with scroll elapse, scaleX, translateX values. To be used instead of the default one
	 * @param se
	 * @param scaleX
	 * @param translateX
	 */
	public BackGround(float se,float scaleX, float scaleY, float translateX) {
		scroll = 0.f;
		scrollElapse = se;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.translateX = translateX;
		backgroundTexture = new TextureRegion (new float [] {0.0f,0.0f,1.0f,0.0f,1.0f,1.0f,0.0f,1.0f,});
	}

	/**
	 * To draw current background layer
	 * @param gl Open GL handler
	 */
	public void draw(GL10 gl) {

		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, backgroundTexture.getVertexBuffer());
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, backgroundTexture.getTextureBuffer());

		gl.glDrawElements(GL10.GL_TRIANGLES, backgroundTexture.getIndices().length, GL10.GL_UNSIGNED_BYTE, backgroundTexture.getIndexBuffer());

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}


	/**
	 * Loading an image as a texture of this background layer
	 * @param gl Open GL handler
	 * @param texture texture number
	 * @param context application context with all resources
	 */
	public void loadTexture(GL10 gl,String textureFile, Context context) {
		
		InputStream imagestream; // = context.getResources().openRawResource(texture);
		Bitmap bitmap = null;
		try {
			imagestream = context.getAssets().open (textureFile);
			// load the image
			bitmap = BitmapFactory.decodeStream(imagestream);
			imagestream.close();
			imagestream = null;
		}catch(Exception e){

		}
		// Bind texture
		gl.glGenTextures(1, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		// clear
		bitmap.recycle();
	}
}