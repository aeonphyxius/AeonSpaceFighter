package com.aeonphyxius.gamecomponents.drawable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import com.aeonphyxius.engine.DrawableComponent;
import com.aeonphyxius.engine.Engine;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;


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

public class BackGround implements DrawableComponent{

	private FloatBuffer vertexBuffer;
	private FloatBuffer textureBuffer;
	private ByteBuffer indexBuffer;
	private float scroll;
	private float scrollElapse;
	private float scaleX;
	private float translateX;

	private int[] textures = new int[1];

	private float vertices[] = {
			0.0f, 0.0f, 0.0f, 
			1.0f, 0.0f, 0.0f,  
			1.0f, 1.0f, 0.0f,  
			0.0f, 1.0f, 0.0f,
	};

	private float texture[] = {          
			0.0f, 0.0f,
			1.0f, 0f,
			1f, 1.0f,
			0f, 1f, 
	};

	private byte indices[] = {
			0,1,2, 
			0,2,3, 
	};
	
	

	
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
	 * Class constructor, with scroll elapse, scaleX, translateX values. To be used 
	 * @param se
	 * @param scaleX
	 * @param translateX
	 */
	public BackGround(float se,float scaleX, float translateX) {
		scroll = 0.f;
		scrollElapse = se;
		this.scaleX = scaleX;
		this.translateX = translateX;
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
	 * To draw current background layer
	 * @param gl Open GL handler
	 */
	public void draw(GL10 gl) {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

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


	/**
	 * Loading an image as a texture of this background layer
	 * @param gl Open GL handler
	 * @param texture texture number
	 * @param context application context with all resources
	 */
	public void loadTexture(GL10 gl,int texture, Context context) {
		InputStream imagestream = context.getResources().openRawResource(texture);
		Bitmap bitmap = null;
		try {
			// load the image
			bitmap = BitmapFactory.decodeStream(imagestream);

		}catch(Exception e){

		}finally {
			//Always clear and close
			try {
				imagestream.close();
				imagestream = null;
			} catch (IOException e) {
			}
		}

		// Bind texture
		gl.glGenTextures(1, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		// clear
		bitmap.recycle();
	}
}
