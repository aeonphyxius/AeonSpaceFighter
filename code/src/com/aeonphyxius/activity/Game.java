package com.aeonphyxius.activity;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.GameView;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

/**
* Game Activity Object.
* 
* <P>Game Activity .
*  
* <P>. 
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class Game extends Activity {
	final Engine gameEngine = new Engine();
	private GameView gameView;
	private DisplayMetrics gameDisplayMetrics;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }
    @Override
    protected void onResume() {
       super.onResume();
       gameView.onResume();
    }

    @Override
    protected void onPause() {
       super.onPause();
       gameView.onPause();
    }

   	@Override
   	public boolean onTouchEvent(MotionEvent event) {
   		//
   		float x = event.getX();
        float y = event.getY();
        Engine.display.getMetrics(gameDisplayMetrics);
        int height =  gameDisplayMetrics.heightPixels / 4;
        int playableArea = gameDisplayMetrics.heightPixels - height;
        if (y > playableArea){
        	switch (event.getAction()){
        	case MotionEvent.ACTION_DOWN:
        		if(x < gameDisplayMetrics.widthPixels / 2){
        			Engine.playerFlightAction = Engine.PLAYER_BANK_LEFT_1;
        		}else{
        			Engine.playerFlightAction = Engine.PLAYER_BANK_RIGHT_1;
        		}
        		break;
        	case MotionEvent.ACTION_UP:
        		Engine.playerFlightAction = Engine.PLAYER_RELEASE;
        		break;
        	}
        	
        }

		return false;
    }
	
}
