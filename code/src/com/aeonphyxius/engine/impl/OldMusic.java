package com.aeonphyxius.engine.impl;

import com.aeonphyxius.engine.Engine;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
* Music Object.
* 
* <P>Music .
*  
* <P>This class contains logic to play the game's music. 
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class OldMusic extends Service {
	
	private boolean isRunning = false;
	private MediaPlayer musicPlayer;
	private static Context context;

		
	public boolean isRunning() {
		return isRunning;
	}


	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}


	public MediaPlayer getMusicPlayer() {
		return musicPlayer;
	}


	public void setMusicPlayer(MediaPlayer musicPlayer) {
		this.musicPlayer = musicPlayer;
	}


	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	
	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
		setMusicOptions(Engine.LOOP_BACKGROUND_MUSIC, Engine.R_VOLUME, Engine.L_VOLUME, Engine.SPLASH_SCREEN_MUSIC);
	}

	/**
	 * 
	 * @param isLooped
	 * @param rVolume
	 * @param lVolume
	 * @param soundFile
	 */
	public void setMusicOptions(boolean isLooped, int rVolume, int lVolume, int soundFile) {
		musicPlayer = MediaPlayer.create(context, soundFile);
		musicPlayer.setLooping(isLooped);
		musicPlayer.setVolume(rVolume, lVolume);
	}

	/**
	 * 
	 */
	public int onStartCommand(Intent intent, int flags, int startId) {
		try {
			musicPlayer.start();
			isRunning = true;
		} catch (Exception e) {
			isRunning = false;
			musicPlayer.stop();
		}

		return 1;
	}

	/**
	 * 
	 */
	public void onStart(Intent intent, int startId) {
		// TODO
	}

	/**
	 * 
	 * @param arg0
	 * @return
	 */
	public IBinder onUnBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public void onStop() {
		isRunning = false;
	}

	/**
	 * 
	 */
	public void onPause() {
	}

	@Override
	public void onDestroy() {
		musicPlayer.stop();
		musicPlayer.release();
	}

	@Override
	public void onLowMemory() {
		musicPlayer.stop();
	}

}
