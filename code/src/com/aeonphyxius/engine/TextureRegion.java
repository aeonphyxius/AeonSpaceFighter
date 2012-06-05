package com.aeonphyxius.engine;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class TextureRegion {
	
	private FloatBuffer vertexBuffer;
	private FloatBuffer textureBuffer;
	private ByteBuffer indexBuffer;	
	
	private float vertices[] = { 
			0.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f, 
			1.0f, 1.0f, 0.0f,
			0.0f, 1.0f, 0.0f, };

	private float texture[];
	
	private byte indices[] = {0, 1, 2, 0, 2, 3, };
	
	
	public TextureRegion (float texture[]){
		this.texture = texture;
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
	
	public TextureRegion (FloatBuffer vertexBuffer,FloatBuffer textureBuffer,ByteBuffer indexBuffer,float vertices[],float texture[],byte indices[]){
		this.vertexBuffer=vertexBuffer;
		this.textureBuffer=textureBuffer;
		this.indexBuffer = indexBuffer;
		this.vertices=vertices;
		this.texture=texture;
		this.indices = indices;
	}
	

	public FloatBuffer getVertexBuffer() {
		return vertexBuffer;
	}

	public void setVertexBuffer(FloatBuffer vertexBuffer) {
		this.vertexBuffer = vertexBuffer;
	}

	public FloatBuffer getTextureBuffer() {
		return textureBuffer;
	}

	public void setTextureBuffer(FloatBuffer textureBuffer) {
		this.textureBuffer = textureBuffer;
	}

	public ByteBuffer getIndexBuffer() {
		return indexBuffer;
	}

	public void setIndexBuffer(ByteBuffer indexBuffer) {
		this.indexBuffer = indexBuffer;
	}

	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}

	public float[] getTexture() {
		return texture;
	}

	public void setTexture(float[] texture) {
		this.texture = texture;
	}

	public byte[] getIndices() {
		return indices;
	}

	public void setIndices(byte[] indices) {
		this.indices = indices;
	}
	
	

}
