package com.aeonphyxius.engine;

import javax.microedition.khronos.opengles.GL10;

public class EngineGL {
	
	/**
	 * 
	 * @param gl
	 * @param spriteSheet
	 * @param spriteIndex
	 * @param textureRegion
	 */
	public void draw(GL10 gl, int[] spriteSheet, int spriteIndex, TextureRegion textureRegion) {

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

}
