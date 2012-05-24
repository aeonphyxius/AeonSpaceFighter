package com.aeonphyxius.activity;


import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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

public class ExitActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Engine.display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        super.onCreate(savedInstanceState);
        // display the splash screen image
        setContentView(R.layout.exitscreen);
        // start up the splash screen and main menu in a time delayed thread
        Engine.context = this;
        new Handler().postDelayed(new Thread() {
        		@Override
        		public void run() {                   
                   ExitActivity.this.finish();
                   overridePendingTransition(R.layout.fadein,R.layout.fadeout);
                   // Exits the game
   					boolean clean = false;
   					//		clean = Engine.onExit(v);	
   					//	if (clean)
   					//{
   					int pid= android.os.Process.myPid();
   					android.os.Process.killProcess(pid);
   					//}
        		}
        	}, Engine.GAME_THREAD_DELAY);
        
    }
}