package com.aeonphyxius.engine;


import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * GameView Object.
 * 
 * <P>
 * GameView class, containing all logic to implement GLSurfaceView
 * 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class GameView extends GLSurfaceView {
	private GameLogic renderer;
	
	public GameView(Context context) {
		super(context);		
		renderer = new GameLogic();		
		this.setRenderer(renderer);
	}
	

}
