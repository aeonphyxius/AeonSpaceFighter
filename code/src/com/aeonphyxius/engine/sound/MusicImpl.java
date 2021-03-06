package com.aeonphyxius.engine.sound;

import java.io.IOException;

import com.aeonphyxius.engine.Engine;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
/**
* MusicImpl Object.
* 
* <P>Music manager to manage game's music
*  
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class MusicImpl implements Music, OnCompletionListener {
    MediaPlayer mediaPlayer;					// media player, to play music
    boolean isPrepared = false;					// is prepared to play music?

    /**
     * Creates the music manager, loadingg the sounds from the assets folder
     * @param assetDescriptor
     */
    public MusicImpl(AssetFileDescriptor assetDescriptor) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                    assetDescriptor.getStartOffset(),
                    assetDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setVolume(Engine.L_VOLUME, Engine.R_VOLUME);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load music");
        }
    }

    @Override
    public void dispose() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public void play() {
        if (mediaPlayer.isPlaying())
            return;

        try {
            synchronized (this) {
                if (!isPrepared)
                    mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLooping(boolean isLooping) {
        mediaPlayer.setLooping(isLooping);
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        synchronized (this) {
            isPrepared = false;
        }
    }

    @Override
    public void onCompletion(MediaPlayer arg0) {
        synchronized (this) {
            isPrepared = false;
        }
    }
}
