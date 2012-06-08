package com.aeonphyxius.activity;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.GameView;
import com.aeonphyxius.engine.MusicManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

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

public class GameActivity extends Activity {

	private GameView gameView;								// Game view handler
	private DisplayMetrics gameDisplayMetrics;				// Display metrics

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Engine.GameSatus = Engine.GAMESTATUS.START;		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Engine.gameActivity = this;
		gameView = new GameView(this);
		setContentView(gameView);
		this.gameDisplayMetrics = new DisplayMetrics();

	}
	@Override
	protected void onResume() {
		super.onResume();
		gameView.onResume();
		MusicManager.getInstance().resumeMusic();
	}

	@Override
	protected void onPause() {
		super.onPause();
		gameView.onPause();
		MusicManager.getInstance().pauseMusic();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.dumpEvent(event);
		return true;
	}

	/**
	 * Handle all input events (device touch screen)
	 * @param event
	 */
	public void dumpEvent(MotionEvent event) {
		//
		float x = event.getX();
		float y = event.getY();
		Engine.display.getMetrics(gameDisplayMetrics);
		int height =  gameDisplayMetrics.heightPixels / 7;
		int playableArea = (gameDisplayMetrics.heightPixels - height);


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

			case MotionEvent.ACTION_MOVE:
				if(x < gameDisplayMetrics.widthPixels / 2){
					Engine.playerFlightAction = Engine.PLAYER_BANK_LEFT_1;
				}else{
					Engine.playerFlightAction = Engine.PLAYER_BANK_RIGHT_1;
				}        		        		
				break;
			}

		}

	}

}
