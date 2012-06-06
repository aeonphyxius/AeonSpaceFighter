package com.aeonphyxius.engine.sound;

/**
 * Sound Interface.
 * 
 * <P>
 * Sound interface for sound implementation
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public interface Sound {
	
	/**
	 * Play the sound at the given volume
	 * @param volume
	 */
    public void play(float volume);

    /**
     * Dispose this sound
     */
    public void dispose();
}
