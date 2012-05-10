package com.aeonphyxius.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.Music;


public class MainMenu extends Activity {
    /** Called when the activity is first created. */
	final Engine engine = new Engine();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Engine.context = getApplicationContext();
       
        
        /** Fire up background music */
       Engine.musicThread = new Thread(){
        	public void run(){
        		Intent bgmusic = new Intent(getApplicationContext(), Music.class);
        		startService(bgmusic);
  
        	}
        };
        Engine.musicThread.start();
        

        
        /** Set menu button options */
        ImageButton start = (ImageButton)findViewById(R.id.btnStart);
        ImageButton exit = (ImageButton)findViewById(R.id.btnExit);
        
        start.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        start.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
       
        exit.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA); 
        exit.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				/** Start Game!!!! */
				Intent game = new Intent(getApplicationContext(),Game.class);
				MainMenu.this.startActivity(game);

			}
        	
        });
        
        exit.setOnClickListener(new OnClickListener(){ 
			@Override
			public void onClick(View v) {
				boolean clean = false;
				clean = engine.onExit(v);	
				if (clean)
				{
					int pid= android.os.Process.myPid();
					android.os.Process.killProcess(pid);
				}
			}
        	});
    }

}
