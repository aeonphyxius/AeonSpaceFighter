package com.aeonphyxius.engine;

import javax.microedition.khronos.opengles.GL10;



public interface Overlay {	
	
	/**
	 * To reset this overlay values to its default values, in order to use it again
	 */
	public void resetOverlay();
	
	/**
	 * Tto draw the Overlay texture
	 * @param gl
	 * @param spriteSheet
	 */
	public void draw(GL10 gl, int[] spriteSheet);
	

}
