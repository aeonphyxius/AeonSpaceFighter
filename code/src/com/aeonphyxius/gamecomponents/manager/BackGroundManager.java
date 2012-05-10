package com.aeonphyxius.gamecomponents.manager;

import java.util.ArrayList;
import java.util.Iterator;
import javax.microedition.khronos.opengles.GL10;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.gamecomponents.drawable.BackGround;


public class BackGroundManager {

	private static BackGroundManager instance = null;

	protected BackGroundManager() {
		background_list = new ArrayList<BackGround>();

		// Add 3 layers for our background
		background_list.add(new BackGround(Engine.SCROLL_BACKGROUND_1, 1f, 0f));
		background_list.add(new BackGround(Engine.SCROLL_BACKGROUND_2, .5f,	1.5f));
		// TODO add the 3rd layer and the correct background images.
		// background_list.add(new BackGround());
	}

	public static BackGroundManager getInstance() {
		if (instance == null) {
			instance = new BackGroundManager();
		}
		return instance;
	}

	private ArrayList<BackGround> background_list;

	/**
	 * 
	 * @param gl
	 */
	public void loadTextures(GL10 gl) {
		background_list.get(0).loadTexture(gl, Engine.BACKGROUND_LAYER_ONE,
				Engine.context);
		background_list.get(1).loadTexture(gl, Engine.BACKGROUND_LAYER_TWO,
				Engine.context);
	}

	/**
	 * 
	 * @param gl
	 */
	public void scrollBackground(GL10 gl) {
		BackGround tempBG;

		for (Iterator<BackGround> i = background_list.iterator(); i.hasNext();) {
			tempBG = i.next();

			if (tempBG.getScroll() == Float.MAX_VALUE) {
				tempBG.setScroll(0f);
			}

			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(tempBG.getScaleX(), 1f, 1f);
			gl.glTranslatef(tempBG.getTranslateX(), 0f, 0f);

			gl.glMatrixMode(GL10.GL_TEXTURE);
			gl.glLoadIdentity();
			gl.glTranslatef(0.0f, tempBG.getScroll(), 0.0f);

			tempBG.draw(gl);
			gl.glPopMatrix();
			tempBG.setScroll(tempBG.getScroll() + tempBG.getScrollElapse());
			gl.glLoadIdentity();
		}
	}
}
