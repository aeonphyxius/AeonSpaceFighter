package com.aeonphyxius.gamecomponents.drawable;

import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.EngineGL;
import com.aeonphyxius.engine.TextureRegion;

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
	private int texture;  // Background texture


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
	public BackGround(float se,float scaleX, float scaleY, float translateX,int textureName) {
		scroll = 0.f;
		scrollElapse = se;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.translateX = translateX;
		backgroundTexture = new TextureRegion (new float [] {0.0f,0.0f,1.0f,0.0f,1.0f,1.0f,0.0f,1.0f,});
		texture=textureName;
	}

	/**
	 * To draw current background layer
	 * @param gl Open GL handler
	 */
	public void draw(GL10 gl) {

		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);

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
}