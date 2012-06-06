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

public interface Music {
	
	/**
	 * Start the music playback
	 */
    public void play();

    /**
     * Stop the music playback
     */
    public void stop();

    /**
     * Pause the music playback
     */
    public void pause();

    /**
     * Set looping value. True to repeat the music sound. False to just play once
     * @param looping
     */
    public void setLooping(boolean looping);

    /**
     * Set musics volume
     * @param volume
     */
    public void setVolume(float volume);

    /**
     * is it playing  the current game's music?
     * @return
     */
    public boolean isPlaying();

    /**
     * is it stopped the current game's music?
     * @return
     */
    public boolean isStopped();

    /**
     * is it looping the current game's music?
     * @return
     */
    public boolean isLooping();

    /**
     * Dispose the game's music
     */
    public void dispose();
}
