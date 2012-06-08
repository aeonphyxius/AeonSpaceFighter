package com.aeonphyxius.activity;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.MusicManager;
import com.aeonphyxius.engine.Vibration;

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

public class OptionsActivity extends Activity implements OnClickListener{

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
		ImageButton vib = (ImageButton)findViewById(R.id.btnVibration);

		// Apply alpha transparency and Haptic Feedback
		back.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
		back.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);

		sound.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
		sound.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);

		diff.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
		diff.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);

		vib.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
		vib.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);
		
		
		// Set onclick listeners 
		sound.setOnClickListener(this);
		back.setOnClickListener(this);
		diff.setOnClickListener(this);
		vib.setOnClickListener(this);


	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
		case R.id.btnBack:
			Vibration.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			MusicManager.getInstance().playSound(Engine.SOUND_CLICK_BACK);
			OptionsActivity.this.finish();
			break;
		case R.id.btnDiff:
			Vibration.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			MusicManager.getInstance().playSound(Engine.SOUND_CLICK);
			// load Difficulty level options screen
			Intent diff = new Intent(getApplicationContext(),DiffOptionsActivity.class);
			OptionsActivity.this.startActivity(diff);
			break;
		case R.id.btnSound:
			Vibration.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			MusicManager.getInstance().playSound(Engine.SOUND_CLICK);
			// load sounds options screen
			Intent sound = new Intent(getApplicationContext(),SoundOptionsActivity.class);
			OptionsActivity.this.startActivity(sound);
			break;
		case R.id.btnVibration:
			Vibration.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			MusicManager.getInstance().playSound(Engine.SOUND_CLICK);
			// load sounds options screen
			Intent vib = new Intent(getApplicationContext(),VibrationOptionsActivity.class);
			OptionsActivity.this.startActivity(vib);
			break;
		}
	}
}