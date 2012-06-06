package com.aeonphyxius.engine;

import android.content.Context;
import android.os.Vibrator;

/**
 * VibrationManager Object.
 * 
 * <P>
 * Vibration manager
 * 
 * <P>
 * This class contains logic to make the device vibrate. Depends of what kind of action, de vibration, will be longer or smaller. 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class VibrationManager {
	
	
	private static VibrationManager instance = null;			// Singleton pattern implementation	
	private Vibrator v;											// Phone's vibrator handler
	
	
	/**
	 * Singleton implementation of the unique instance of this class
	 * @return unique instance of GameOverOvelay
	 */
	public static VibrationManager getInstance() {
		if (instance == null) {
			instance = new VibrationManager();
		}
		return instance;
	}
	
	/**
	 * Unique Vibration Manager constructor, will initialize the Vibrator
	 */
	private VibrationManager(){
		v = (Vibrator) Engine.context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	/**
	 * Set the phones to vibrates with the given mode (milisecs)
	 * @param vibrationMode
	 */
	public void setVibration(long vibrationMode){
		if (Engine.isVibrated){
			v.vibrate(vibrationMode);
		}
	}
	
	

}
