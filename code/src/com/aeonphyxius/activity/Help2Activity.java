package com.aeonphyxius.activity;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
 
/**Help1Activity
* AboutActivity Object.
* 
* <P>Help screen activity
*  
* <P>Shows some information from this game creator 
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class Help2Activity extends Activity implements OnClickListener {

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
		// load about screen
    	/*Help2Activity.this.finish();
		Intent help3 = new Intent(getApplicationContext(),Help3Activity.class);
		Help2Activity.this.startActivity(help3);*/
		
    	return true;
    }

	@Override
	public void onClick(View arg0) {
		Help2Activity.this.finish();
		Intent help3 = new Intent(getApplicationContext(),Help3Activity.class);
		Help2Activity.this.startActivity(help3);
		
	}
}