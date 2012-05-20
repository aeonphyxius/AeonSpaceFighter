package com.aeonphyxius.activity;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
* SoundOptionsActivity Object.
* 
* <P>Sound Options menu activity
*  
* <P>Handles all the events, layout, images etc for the main options screen. Will consist
* in 2 options, "yes" "no" for sound 
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class SoundOptionsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)  {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.soundoptions);
        Engine.context = getApplicationContext();
          
        // Set menu button options
        ImageButton back = (ImageButton)findViewById(R.id.btnBack);
        ImageButton yes = (ImageButton)findViewById(R.id.btnYes);
        ImageButton no = (ImageButton)findViewById(R.id.btnNo);
        
        // Set alpha and haptic feedback for all the button's image 
        back.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        back.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        yes.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        yes.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        no.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        no.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
                    

        // Yes button click event. Puts sound option to true
        yes.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Engine.sound= true; // Set sound to true
				// Closes this activity
				SoundOptionsActivity.this.finish();
			}        	
        });
        
        // No button click event. Puts sound option to flase
        no.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Engine.sound = false;
				// Closes this activity
				SoundOptionsActivity.this.finish();
			}        	
        });
        
        // Back button onclick listener. Goes back to options menu
        back.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// Close this activity screen
                SoundOptionsActivity.this.finish();
			}        	
        });        
    }
}