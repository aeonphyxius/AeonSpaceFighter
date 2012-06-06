package com.aeonphyxius.engine.sound;


/**
 * Audio Interface.
 * 
 * <P>
 * Audio interface for music and sound implementation
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */


public interface Audio {
	
	/**
	 * When loading a new music file
	 * @param filename
	 * @return
	 */
    public Music newMusic(String filename);

    /**
     * When loading a new sound file
     * @param filename
     * @return
     */
    public Sound newSound(String filename);
}
