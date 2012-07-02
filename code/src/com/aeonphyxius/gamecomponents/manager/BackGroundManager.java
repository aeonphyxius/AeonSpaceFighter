package com.aeonphyxius.gamecomponents.manager;

import java.util.ArrayList;
import java.util.Iterator;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.gamecomponents.drawable.BackGround;

/**
 * BackGroundManager Object.
 *
 * <P>Manager for all game background layers.
 *
 * <P>This class contains logic manage the game's background layers.
 *
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class BackGroundManager {

	private static BackGroundManager instance = null;
	private ArrayList<BackGround> background_list;

	/**
	 * Class constructor. Only to be called from Singleton implementation
	 */
	protected BackGroundManager() {
		background_list = new ArrayList<BackGround>();

		// Add 2 layers for our background
		background_list.add(new BackGround(Engine.SCROLL_BACKGROUND_1, 1f, 1.f, 0f,Engine.TEXTURE_BG1));
		background_list.add(new BackGround(Engine.SCROLL_BACKGROUND_2, 1.6f, 1.0f, -0.3f,Engine.TEXTURE_BG2));
	}

	/**
	 * Singleton implementation
	 * @return the unique instance of this class
	 */
	public static BackGroundManager getInstance() {
		if (instance == null) {
			instance = new BackGroundManager();
		}
		return instance;
	}


	/**
	 * Moves background layers to simulate scrolling
	 * @param gl Open GL handler
	 */
	public void scrollBackground(GL10 gl) {
		BackGround tempBG;

		// loop throw all background layers.
		for (Iterator<BackGround> i = background_list.iterator(); i.hasNext();) {
			tempBG = i.next();

			if (tempBG.getScroll() == Float.MAX_VALUE) {
				tempBG.setScroll(0f);
			}

			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef(tempBG.getScaleX(),tempBG.getScaleY(), 1f);
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