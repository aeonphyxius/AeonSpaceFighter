package com.aeonphyxius.activity;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
* DiffOptionsActivity Object.
* 
* <P>Difficulty Options menu activity
*  
* <P>Handles all the events, layout, images etc for the different difficulty levels . 
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class DiffOptionsActivity extends Activity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState)  {
    	super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.diffoptions);
        Engine.context = getApplicationContext();
          
        // Set menu button options
        ImageButton back = (ImageButton)findViewById(R.id.btnBack);
        ImageButton easy = (ImageButton)findViewById(R.id.btnEasy);
        ImageButton normal = (ImageButton)findViewById(R.id.btnNornmal);
        ImageButton hard = (ImageButton)findViewById(R.id.btnHard);
        
        // Apply alpha transparency and Haptic Feedback
        back.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        back.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        easy.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        easy.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
        
        normal.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        normal.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);        
        
        hard.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
        hard.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);

        //  Set activity buttons onclick event
        easy.setOnClickListener(this);
        normal.setOnClickListener(this);
        hard.setOnClickListener(this);  
        back.setOnClickListener(this);  
        
    }

	@Override
	public void onClick(View view) {
		switch (view.getId()){
		case R.id.btnBack: // Click on back
			// Close this activity screen
            DiffOptionsActivity.this.finish();
		break;
		case R.id.btnHard: // Click on difficulty level HARD
			// Set difficulty level to hard (3)
			Engine.difficulty = 3; 				
            // Close this activity screen
			DiffOptionsActivity.this.finish();
			break;
		case R.id.btnNornmal: // Click on difficulty level NORMAL
			// Set difficulty level to normal (2)
			Engine.difficulty = 2; 
			// Close this activity screen
            DiffOptionsActivity.this.finish();
			break;
		case R.id.btnEasy: // Click on difficulty level EASY
			// Set difficulty level to easy (1)
			Engine.difficulty = 1; 
			// Close this activity screen
            DiffOptionsActivity.this.finish();
			break;
		}
	}
}