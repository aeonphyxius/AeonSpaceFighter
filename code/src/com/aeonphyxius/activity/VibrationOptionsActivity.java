package com.aeonphyxius.activity;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.MusicManager;
import com.aeonphyxius.engine.Vibration;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * VibrationOptionsActivity Object.
 * 
 * <P>Vibration Options menu activity
 *  
 * <P>Handles all the events, layout, images etc for the main options screen. Will consist
 * in 2 options, "yes" "no" for sound 
 *  
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class VibrationOptionsActivity extends Activity implements OnClickListener{

	@Override
	public void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.vibrationoptions);
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


		// Add onClick Listeners to this menu buttons
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		back.setOnClickListener(this);        
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
		case R.id.btnBack: // Click on back
			Vibration.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			MusicManager.getInstance().playSound(Engine.SOUND_CLICK_BACK);
			VibrationOptionsActivity.this.finish();
			break;
		case R.id.btnNo: // Click on No sound
			Engine.isVibrated = false;
			Vibration.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			MusicManager.getInstance().playSound(Engine.SOUND_CLICK);
			
			// Closes this activity
			VibrationOptionsActivity.this.finish();
			break;			
		case R.id.btnYes: // Click on YES sound
			Engine.isVibrated= true; // Set sound to true
			Vibration.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			MusicManager.getInstance().playSound(Engine.SOUND_CLICK);			
			// Closes this activity
			VibrationOptionsActivity.this.finish();
			break;
		}
	}
}