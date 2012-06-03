package com.aeonphyxius.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.MusicManager;

/**
* MainMenuActivity Object.
* 
* <P>Main menu activity
*  
* <P>Handles all the events, layout, images etc for the main meny screen. 
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class MainMenuActivity extends Activity {
    /** Called when the activity is first created. */
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mainmenu);
        Engine.context = getApplicationContext();
       
        // TODO : fix music
       /* if (!Engine.isMuted){
	        // Fire up background music
	       Engine.musicThread = new Thread(){
	        	public void run(){
	        		Intent bgmusic = new Intent(getApplicationContext(), OldMusic.class);
	        		startService(bgmusic);  
	        	}
	        };	        
	        Engine.musicThread.start();
        }*/
        try{
        	MusicManager.getInstance().loadMusic();
        	MusicManager.getInstance().loadSounds();       	
        	
        }catch (Exception e){
        	e.printStackTrace();
        }
            
        //
        // Set menu button options
        ImageButton start = (ImageButton)findViewById(R.id.btnStart);
        ImageButton exit = (ImageButton)findViewById(R.id.btnExit);
        ImageButton options = (ImageButton)findViewById(R.id.btnOptions);
        ImageButton about = (ImageButton)findViewById(R.id.btnAbout);
        ImageButton help = (ImageButton)findViewById(R.id.btnHelp);
        
        
        
        // Apply alpha transparency and Haptic Feedback
        start.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        start.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
       
        exit.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA); 
        exit.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        options.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        options.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        about.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        about.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        help.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        help.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
                
        // About button click event. Loads About activity
        about.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// load about screen
				Intent about = new Intent(getApplicationContext(),AboutActivity.class);
				MainMenuActivity.this.startActivity(about);

			}        	
        });
        
        // Options button click event. Loads About activity
        options.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// load about screen
				Intent options = new Intent(getApplicationContext(),OptionsActivity.class);
				MainMenuActivity.this.startActivity(options);

			}        	
        });
        
        // Start button click event. Loads Game activity and starts the game
        start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// Start Game!!!!
				Intent game = new Intent(getApplicationContext(),GameActivity.class);
				MainMenuActivity.this.startActivity(game);

			}
        	
        });
        
        // Exit button click event. Cleans the application and exits
        exit.setOnClickListener(new OnClickListener(){ 
			
        	@Override
			public void onClick(View v) {
				// Start exit activity !!!!
				Intent exit = new Intent(getApplicationContext(),ExitActivity.class);
				MainMenuActivity.this.finish();
				MainMenuActivity.this.startActivity(exit);
        		
			/*	// Exits the game
				boolean clean = false;
				//clean = Engine.onExit(v);	
				//if (clean)
				//{
					int pid= android.os.Process.myPid();
					android.os.Process.killProcess(pid);*/
				//}
			}
        	
        });
    }

}
