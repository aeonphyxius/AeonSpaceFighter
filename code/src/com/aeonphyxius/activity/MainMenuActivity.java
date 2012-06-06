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
import com.aeonphyxius.engine.VibrationManager;

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

public class MainMenuActivity extends Activity implements OnClickListener{
	/** Called when the activity is first created. */



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.mainmenu);
		Engine.context = getApplicationContext();

		try{
			MusicManager.getInstance().loadMusic();
			MusicManager.getInstance().loadSounds();       	

		}catch (Exception e){
			e.printStackTrace();
		}            

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

		// Add onclick listeners
		start.setOnClickListener(this);
		exit.setOnClickListener(this);
		options.setOnClickListener(this);
		about.setOnClickListener(this);
		help.setOnClickListener(this);        

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
		case R.id.btnExit: // Click on exit
			VibrationManager.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			// Start exit activity !!!!
			Intent exit = new Intent(getApplicationContext(),ExitActivity.class);
			MainMenuActivity.this.finish();
			MainMenuActivity.this.startActivity(exit);
			break;
		case R.id.btnStart: // Click on start
			VibrationManager.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			// Start Game!!!!
			Intent game = new Intent(getApplicationContext(),GameActivity.class);
			MainMenuActivity.this.startActivity(game);
			break;
		case R.id.btnOptions: // Click on options
			VibrationManager.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			// load options screen
			Intent options = new Intent(getApplicationContext(),OptionsActivity.class);
			MainMenuActivity.this.startActivity(options);
			break;
		case R.id.btnAbout: // Click on about
			VibrationManager.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			// load about screen
			Intent about = new Intent(getApplicationContext(),AboutActivity.class);
			MainMenuActivity.this.startActivity(about);
			break;
		case R.id.btnHelp: // Click on help
			VibrationManager.getInstance().setVibration(Engine.MENU_CLICK_VIB);
			// load the help screen on reverse mode (so when they close themselves, we see the next one).			
			Intent help3 = new Intent(getApplicationContext(),Help3Activity.class);
			MainMenuActivity.this.startActivity(help3);

			Intent help2 = new Intent(getApplicationContext(),Help2Activity.class);
			MainMenuActivity.this.startActivity(help2);

			Intent help = new Intent(getApplicationContext(),Help1Activity.class);
			MainMenuActivity.this.startActivity(help);
			break;
		}
	}

}
