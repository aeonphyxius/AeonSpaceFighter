package com.aeonphyxius.gamecomponents.drawable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;


public class HUDShield {

	private Vector <float[]> textureList;
	private static HUDShield instance = null;
	private FloatBuffer vertexBuffer;
	private FloatBuffer textureBuffer;
	private ByteBuffer indexBuffer;	
	private final int SPRITES_INDEX = 1;
	private int oldShieldTexture;


	private float vertices[] = { 
			0.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 
			1.0f, 1.0f, 0.0f,
			0.0f, 1.0f, 0.0f, };


	/*private float texture[] = { 
			0.356f, 0.875f, 
			0.441f, 0.875f, 
			0.441f, 0.976f, 
			0.356f,0.976f,};*/

	private float texture_100[] = { 
			0.338f, 0.82f, 
			0.453f, 0.824f, 
			0.453f, 0.939f, 
			0.338f,0.939f,};

	private float texture_75[] = { 
			0.471f, 0.828f, 
			0.586f, 0.828f, 
			0.586f, 0.945f, 
			0.471f,0.945f,};

	private float texture_50[] = { 
			0.338f, 0.824f, 
			0.453f, 0.824f, 
			0.453f, 0.939f, 
			0.338f,0.939f,};

	private float texture_25[] = { 
			0.338f, 0.824f, 
			0.453f, 0.824f, 
			0.453f, 0.939f, 
			0.338f,0.939f,};

	private float texture_0[] = { 
			0.338f, 0.824f, 
			0.453f, 0.824f, 
			0.453f, 0.939f, 
			0.338f,0.939f,};


	private byte indices[] = { 	0, 1, 2, 0, 2, 3, };


	public static HUDShield getInstance() {
		if (instance == null) {
			instance = new HUDShield();
		}
		return instance;
	}

	private HUDShield() {
		this.textureList = new Vector <float[]>();
		textureList.add(texture_0);
		textureList.add(texture_25);
		textureList.add(texture_50);
		textureList.add(texture_75);
		textureList.add(texture_100);
		this.oldShieldTexture=0;
		
	}

	
	public void setShieldTexture(int shieldTexture){
		
		if (oldShieldTexture!=shieldTexture){
			oldShieldTexture=shieldTexture;
		
			ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
			byteBuf.order(ByteOrder.nativeOrder());
			vertexBuffer = byteBuf.asFloatBuffer();
			vertexBuffer.put(vertices);
			vertexBuffer.position(0);
			byteBuf = ByteBuffer.allocateDirect(textureList.get(shieldTexture).length * 4);
			byteBuf.order(ByteOrder.nativeOrder());
			textureBuffer = byteBuf.asFloatBuffer();
			textureBuffer.put(textureList.get(shieldTexture));
			textureBuffer.position(0);
	
			indexBuffer = ByteBuffer.allocateDirect(indices.length);
			indexBuffer.put(indices);
			indexBuffer.position(0);
		}
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
