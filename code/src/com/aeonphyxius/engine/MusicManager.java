package com.aeonphyxius.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import com.aeonphyxius.engine.impl.*;



public class MusicManager {

	private static MusicManager instance = null;
	private Map<String, SoundImpl> soundList;
	private MusicImpl music;
	//private Map<String, SoundImpl> audioList;

	/**
	 * Singleton implementation
	 * @return the unique instance of this class
	 */
	public static MusicManager getInstance() {
		if (instance == null) {
			instance = new MusicManager();
		}
		return instance;
	}
	
	private MusicManager(){
		
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void loadSounds() throws Exception {

		AssetManager assetManager = Engine.context.getAssets();
		String fileName = "sounds.xml";
		InputStream is;

		try {
			is = assetManager.open(fileName);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xmlReader = sp.getXMLReader();
			xmlReader.setContentHandler(new SoundContentHandler());
			xmlReader.parse(new InputSource(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sax parser handler to parse the level files. See also assets/sound.dtd
	 */
	private class SoundContentHandler extends DefaultHandler {

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			
			String tempName, tempSrc;
			int tempId;
			SoundImpl tempSound;
			SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

			if (localName.equals("sound")) {

				// get values from xml file
				tempName = attributes.getValue("name");
				tempSrc = attributes.getValue("src");

				// load the sound file into our soundpool.
				tempId = soundPool.load("//assets/"+tempSrc,1);
				
				// create a new sound and insert it into the sounds list
				tempSound = new SoundImpl(soundPool,tempId);
				soundList.put(tempName,tempSound);
			}
		}
	}
	
	/**
	 * 
	 */
	public void loadMusic(){
		AssetFileDescriptor assetDescriptor=null;
	
		try {
			assetDescriptor = new AssetFileDescriptor(Engine.context.getContentResolver().openFileDescriptor(Uri.fromFile(new File("//assets/level01.ogg")),""),0,-1);
		} catch (FileNotFoundException ex) {
            // fall through and open the fallback ringtone below
        }
		music = new MusicImpl(assetDescriptor);
	}
	
	/**
	 * 
	 */
	public void playMusic(){
		music.play();
	}
	

}
