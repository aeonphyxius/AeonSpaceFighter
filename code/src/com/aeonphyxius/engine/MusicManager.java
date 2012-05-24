package com.aeonphyxius.engine;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.content.res.AssetManager;

import com.aeonphyxius.engine.impl.*;

public class MusicManager {
	
	private Map <String, SoundImpl> soundList;
	private SoundImpl musicList;
	private Map <String,SoundImpl> audioList;
	
	
	
	
	public void loadSounds() throws Exception{
		
		AssetManager assetManager = Engine.context.getAssets();
		String fileName = "sounds.xml";
		InputStream is;
		
		try {
			is = assetManager.open(fileName);
	        SAXParserFactory spf = SAXParserFactory.newInstance();
	        SAXParser sp = spf.newSAXParser();
	        XMLReader xmlReader = sp.getXMLReader();
	        xmlReader.setContentHandler(new MusicContentHandler());
	        xmlReader.parse(new InputSource(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	
	
	public void loadMusic(){
		
	}
	
		/**
		* Sax parser handler to parse the level files.
		* See also assets/level.dtd
		*/
	    private class MusicContentHandler extends DefaultHandler {

	        @Override
	        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

	        	Map <String, SoundImpl> tempSoundList;
	        	int enemy;
	        	int xpos;
	        	int numEnemies = 0;
	        	
	            if (localName.equals("squadron")) {

	            	int ypos = Integer.parseInt(attributes.getValue("ypos"));	            	
	            	enemy = Integer.parseInt(attributes.getValue("enemy"));
	            	
	            	
	            	// Enemy1	                
	                xpos = Integer.parseInt(attributes.getValue("xpos1"));
	                enemyList.add(new Enemy (enemy,xpos));
	                numEnemies ++;
	                
	                // Enemy2	                
	                xpos = Integer.parseInt(attributes.getValue("xpos2"));
	                enemyList.add(new Enemy (enemy,xpos));
	                numEnemies ++;
	                
	                // Enemy3
	                if (attributes.getValue("xpos3")!=null){	                	
	                	xpos = Integer.parseInt(attributes.getValue("xpos3"));
	                	enemyList.add(new Enemy (enemy,xpos));
	                	numEnemies ++;
	                }
	                
	                // Enemy4
	                if (attributes.getValue("xpos4")!=null){	                	
	                	xpos = Integer.parseInt(attributes.getValue("xpos4"));
	                	enemyList.add(new Enemy (enemy,xpos));
	                	numEnemies ++;
	                }
	                
	                // Enemy5
	                if (attributes.getValue("xpos5")!=null){	                	
	                	xpos = Integer.parseInt(attributes.getValue("xpos5"));
	                	enemyList.add(new Enemy (enemy,xpos));
	                	numEnemies ++;
	                }
	                
	                System.out.print("Squadron ypos:"+ypos);
	                Squadron sq = new Squadron(enemyList,ypos,enemy,numEnemies,0);
	                squadronList.add(sq);
	            }
	        }
	    }

}
