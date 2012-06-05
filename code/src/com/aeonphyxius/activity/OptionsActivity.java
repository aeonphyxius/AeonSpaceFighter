package com.aeonphyxius.activity;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
* OptionsActivity Object.
* 
* <P>Options menu activity
*  
* <P>Handles all the events, layout, images etc for the main options screen. 
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class OptionsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)  {
    	super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.options);
        Engine.context = getApplicationContext();
          
        ImageButton back = (ImageButton)findViewById(R.id.btnBack);
        ImageButton sound = (ImageButton)findViewById(R.id.btnSound);
        ImageButton diff = (ImageButton)findViewById(R.id.btnDiff);
        
        // Apply alpha transparency and Haptic Feedback
        back.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        back.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        sound.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        sound.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        diff.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        diff.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        
        // Sound options button click event. Loads Sound options activity
        sound.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// load sounds options screen
				Intent sound = new Intent(getApplicationContext(),SoundOptionsActivity.class);
				OptionsActivity.this.startActivity(sound);

			}        	
        });

        
        // Difficult options button click event. Loads difficult options activity
        diff.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// load Difficulty level options screen
				Intent diff = new Intent(getApplicationContext(),DiffOptionsActivity.class);
				OptionsActivity.this.startActivity(diff);

			}        	
        });

         // Back button click event. Goes back to previous Activity (Options in this case).
        back.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// Close this activity screen
                OptionsActivity.this.finish();
			}        	
        });        
    }
}