package com.aeonphyxius.activity;


import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * WelcomeActivity Object.
 * 
 * <P>Welcome screen activity
 *  
 * <P>Shows the welcome screen during a certain amount of time before transition to main menu screen 
 *  
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class WelcomeActivity extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Engine.display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// display the splash screen image
		setContentView(R.layout.welcomescreen);
		// start up the splash screen and main menu in a time delayed thread
		Engine.context = this;
		new Handler().postDelayed(new Thread() {
			@Override
			public void run() {
				Intent mainMenu = new Intent(WelcomeActivity.this, MainMenuActivity.class);
				WelcomeActivity.this.startActivity(mainMenu);
				WelcomeActivity.this.finish();
				overridePendingTransition(R.layout.fadein,R.layout.fadeout);
			}
		}, Engine.GAME_THREAD_DELAY);
	}
	
	/*@Override
    protected void onDestroy() {
        super.onDestroy();

        unbindDrawables(findViewById(R.drawable.welcome));
        System.gc();
    }
	
	private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }*/


}