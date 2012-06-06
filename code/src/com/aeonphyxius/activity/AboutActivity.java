package com.aeonphyxius.activity;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.VibrationManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * AboutActivity Object.
 * 
 * <P>About screen activity
 *  
 * <P>Shows some information from this game creator 
 *  
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class AboutActivity extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.about);
		Engine.context = getApplicationContext();

		// Set menu button options
		ImageButton back = (ImageButton)findViewById(R.id.btnBack);

		// Apply alpha transparency and Haptic Feedback
		back.getBackground().setAlpha(Engine.MENU_BUTTON_ALPHA);
		back.setHapticFeedbackEnabled(Engine.HAPTIC_BUTTON_FEEDBACK);

		// Back button onclick listener. Goes back to options menu
		back.setOnClickListener(this);        
	}

	@Override
	public void onClick(View view) {
		VibrationManager.getInstance().setVibration(Engine.MENU_CLICK_VIB);
		AboutActivity.this.finish();

	}
}