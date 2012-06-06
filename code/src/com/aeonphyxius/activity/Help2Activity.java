package com.aeonphyxius.activity;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**Help2Activity
 * AboutActivity Object.
 * 
 * <P>Help screen activity number 2
 *  
 * <P>Shows the 2nd help screen
 *  
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class Help2Activity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.help2);
		Engine.context = getApplicationContext();

		// 
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Help2Activity.this.finish();		
		return true;
	}
}