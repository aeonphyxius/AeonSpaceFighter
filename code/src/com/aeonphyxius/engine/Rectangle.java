package com.aeonphyxius.engine;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Rectangle extends EngineGL{

	TextureRegion drawRegion = new TextureRegion(new float[] {});
    
    
    public void draw(GL10 gl){
    	super.draw(gl,drawRegion);
    	/*vertices= new float[] {
    			Engine.playerBankPosX,Engine.PLAYER_POS_Y +0.6f,-1.f,
    			Engine.playerBankPosX, Engine.PLAYER_POS_Y,-1.f,
    			Engine.playerBankPosX+ 0.6f,Engine.PLAYER_POS_Y,-1.f,
    			Engine.playerBankPosX+0.6f,Engine.PLAYER_POS_Y + 0.6f,-1.f,
    	};
    	/*
    	calculate();
    	gl.glClearColor(1.f, 1.f, 1.f, 0.f);
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW); // OpenGL docs
		// Enable face culling.
		//gl.glEnable(GL10.GL_CULL_FACE); // OpenGL docs
		// What faces to remove with the face culling.
		//gl.glCullFace(GL10.GL_BACK); // OpenGL docs

		// Enabled the vertices buffer for writing and to be used during
		// rendering.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);// OpenGL docs.
		//gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glColor4f(0.5f, 1.f, 0.3f, 0.7f);

		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,// OpenGL docs
				  GL10.GL_UNSIGNED_SHORT, indexBuffer);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		//gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);*/
    }

}
