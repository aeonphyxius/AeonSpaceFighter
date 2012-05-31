package com.aeonphyxius.gamecomponents.drawable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.aeonphyxius.data.PlayerData;
import com.aeonphyxius.engine.DrawableComponent;
import com.aeonphyxius.engine.Engine;

public class Player implements DrawableComponent {

	private static Player instance = null;
	private PlayerData data;
	private FloatBuffer vertexBuffer;
	private FloatBuffer textureBuffer;
	private ByteBuffer indexBuffer;
	private final int SPRITE_INDEX = 2;

	private float vertices[] = { 
			0.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 
			1.0f, 1.0f, 0.0f,
			0.0f, 1.0f, 0.0f, };

	private float texture[] = { 
			0.0f, 0.0f, 
			0.33f, 0.0f, 
			0.33f, 0.30f, 
			0.0f,0.33f, };


	private byte indices[] = { 
			0, 1, 2, 
			0, 2, 3, 
			};


	
	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}
	
	public void applyDamage() {
		data.increaseDamage();
		if (data.getDamage() == Engine.PLAYER_SHIELDS) {
			data.setDestroyed(true);
		}

	}
	
	
	public PlayerData getData() {
		return data;
	}

	public void setData(PlayerData data) {
		this.data = data;
	}

	public boolean isDestroyed(){
		return data.isDestroyed();
	}
	
	/**
	 * 
	 */
	private Player() {
		data = new PlayerData();
		
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
	}

	/**
	 * 
	 * @param gl
	 * @param spriteSheet
	 */
	public void draw(GL10 gl, int[] spriteSheet) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, spriteSheet[SPRITE_INDEX]);

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
