package com.aeonphyxius.engine;

import javax.microedition.khronos.opengles.GL10;
/**
 * EngineGL Object.
 * 
 * <P>All OpenGL related rendering operations .
 *  
 * <P>This class contains logic to display enemies, player and firepower. 
 *  
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class EngineGL {


	/**
	 * Update the current position with the given coordinates
	 * @param gl
	 * @param scaleX
	 * @param scaleY
	 * @param scaleZ
	 * @param xpos
	 * @param ypos
	 * @param zpos
	 */
	public void update ( GL10 gl,float scaleX, float scaleY, float scaleZ, float xpos, float ypos, float zpos){
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glPushMatrix();
		//gl.glScalef(.05f + (0.01f * animation), .05f+ (0.01f * animation), 1f);
		gl.glScalef(scaleX,scaleY, scaleZ);
		gl.glTranslatef(xpos, ypos, zpos);
	}

	public void restoreMatrix(GL10 gl){
		gl.glPopMatrix();													// Recover previous Matrix
		gl.glLoadIdentity();
	}

	/**
	 * Draw the given texture at the position previously updated
	 * @param gl
	 * @param spriteSheet
	 * @param spriteIndex
	 * @param textureRegion
	 */
	public void draw(GL10 gl, int[] spriteSheet, int spriteIndex, TextureRegion textureRegion) {

		gl.glMatrixMode(GL10.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glBindTexture(GL10.GL_TEXTURE_2D, spriteSheet[spriteIndex]);

		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, textureRegion.getVertexBuffer());
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0,
				textureRegion.getTextureBuffer());

		gl.glDrawElements(GL10.GL_TRIANGLES, textureRegion.getIndices().length,
				GL10.GL_UNSIGNED_BYTE, textureRegion.getIndexBuffer());

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}


	/**
	 * Draw the given texture at the position previously updated
	 * @param gl
	 * @param spriteSheet
	 * @param spriteIndex
	 * @param textureRegion
	 */
	public void draw(GL10 gl , TextureRegion textureRegion) {

		//gl.glBindTexture(GL10.GL_TEXTURE_2D, spriteSheet[spriteIndex]);

		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glColor4f(0.5f, 1.f, 0.3f, 0.7f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, textureRegion.getVertexBuffer());
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0,
				textureRegion.getTextureBuffer());

		gl.glDrawElements(GL10.GL_TRIANGLES, textureRegion.getIndices().length,
				GL10.GL_UNSIGNED_BYTE, textureRegion.getIndexBuffer());

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}


}
