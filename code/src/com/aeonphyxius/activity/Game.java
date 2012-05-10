package com.aeonphyxius.activity;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.GameView;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public class Game extends Activity {
	final Engine gameEngine = new Engine();
	private GameView gameView;
    
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
        int height = Engine.display.getHeight() / 4;
        int playableArea = Engine.display.getHeight() - height;
        if (y > playableArea){
        	switch (event.getAction()){
        	case MotionEvent.ACTION_DOWN:
        		if(x < Engine.display.getWidth() / 2){
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
