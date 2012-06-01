package com.aeonphyxius.gamecomponents.drawable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


public class HUDControl {

	private static HUDControl instance = null;
	private FloatBuffer vertexBuffer;
	private FloatBuffer textureBuffer;
	private ByteBuffer indexBuffer;	


	private FloatBuffer vertexBuffer_bkg;
	private FloatBuffer textureBuffer_bkg;
	private ByteBuffer indexBuffer_bkg;	


	private final int SPRITES_INDEX = 1;

	private float vertices[] = { 
			0.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 
			1.0f, 1.0f, 0.0f,
			0.0f, 1.0f, 0.0f, };

	private float texture[] = { 
			0.375f, 0.0f, 
			1.0f, 0.0f, 
			1.0f, 0.25f, 
			0.375f,0.25f, };


	private float texture_bkg[] = { 
			0.25f, 0.0f, 
			0.32f, 0.0f, 
			0.32f, 0.236f, 
			0.25f,0.236f, };



	private byte indices[] = { 
			0, 1, 2, 
			0, 2, 3, 
	};


	public static HUDControl getInstance() {
		if (instance == null) {
			instance = new HUDControl();
		}
		return instance;
	}

	private HUDControl() {

		// arrows
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);

		indexBuffer = ByteBuffer.allocateDirect(indices.length);
		indexBuffer.put(indices);
		indexBuffer.position(0);

		//background
		byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer_bkg = byteBuf.asFloatBuffer();
		vertexBuffer_bkg.put(vertices);
		vertexBuffer_bkg.position(0);

		byteBuf = ByteBuffer.allocateDirect(texture_bkg.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer_bkg = byteBuf.asFloatBuffer();
		textureBuffer_bkg.put(texture_bkg);
		textureBuffer_bkg.position(0);

		indexBuffer_bkg = ByteBuffer.allocateDirect(indices.length);
		indexBuffer_bkg.put(indices);
		indexBuffer_bkg.position(0);



	}

	public void drawBkg(GL10 gl, int[] spriteSheet) {

		gl.glBindTexture(GL10.GL_TEXTURE_2D, spriteSheet[SPRITES_INDEX]);

		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer_bkg);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer_bkg);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer_bkg);      

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
	
	public void draw(GL10 gl, int[] spriteSheet) {

		gl.glBindTexture(GL10.GL_TEXTURE_2D, spriteSheet[SPRITES_INDEX]);

		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, indexBuffer);      

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}

}
