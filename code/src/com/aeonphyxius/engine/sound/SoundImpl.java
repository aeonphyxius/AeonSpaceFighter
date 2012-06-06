package com.aeonphyxius.engine.sound;

import android.media.SoundPool;

/**
* SoundImpl Object.
* 
* <P>Sound manager to manage game's sounds
*  
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/


public class SoundImpl implements Sound {
    int soundId;						// Sound id inside the sound pool
    SoundPool soundPool;				// Sound pool containing all sounds
    
    /**
     * Creates a new sound pool with a given sound id 
     * @param soundPool
     * @param soundId
     */
    public SoundImpl(SoundPool soundPool,int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }

}
