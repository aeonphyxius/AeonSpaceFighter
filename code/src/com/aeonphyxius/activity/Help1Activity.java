package com.aeonphyxius.activity;

import com.aeonphyxius.R;
import com.aeonphyxius.engine.Engine;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
 
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

public class Help1Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)  {
    	super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.help1);
        Engine.context = getApplicationContext();
          
    }

    @Override
   	public boolean onTouchEvent(MotionEvent event) {
		// load about screen
    	
		//Intent help2 = new Intent(getApplicationContext(),Help2Activity.class);
		//Help1Activity.this.startActivity(help2);
		Help1Activity.this.finish();
    	return true;
    }


}