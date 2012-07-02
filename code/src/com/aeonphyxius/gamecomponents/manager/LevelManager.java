package com.aeonphyxius.gamecomponents.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import android.content.res.AssetManager;
import com.aeonphyxius.data.LevelData;
import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.TextureManager;



public class LevelManager {
	
	private static LevelManager instance = null;			// Singleton implementation
	private Map<Integer, LevelData> levelList;
	private int currentLevel;
	
	
	/**
	 * Singleton implementation of the unique instance of this class
	 * @return
	 */
	public static LevelManager getInstance() {
		if (instance == null) {
			instance = new LevelManager();
		}
		return instance;
	}
	
	private LevelManager(){
		levelList = new Hashtable<Integer, LevelData>();
		this.currentLevel  = 1; 			
	}
	
	public void loadCurrentLevelData(GL10 gl){
		LevelData tempLevelData = levelList.get(currentLevel);
		
		TextureManager.getInstance().loadTexture(gl,tempLevelData.getBg1TextureFile(),Engine.TEXTURE_BG1,true);
		TextureManager.getInstance().loadTexture(gl,tempLevelData.getBg2TextureFile(),Engine.TEXTURE_BG2,true);
		TextureManager.getInstance().loadTexture(gl,tempLevelData.getEndTextureFile(),Engine.TEXTURE_END_LEVEL,false);
		
	}
	
	public void loadLevelsData() throws Exception{

		AssetManager assetManager = Engine.context.getAssets();
		String fileName = "conf_level_data.xml";
		InputStream is;
		try {
			is = assetManager.open(fileName);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xmlReader = sp.getXMLReader();
			xmlReader.setContentHandler(new LevelContentHandler());
			xmlReader.parse(new InputSource(is));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getBg1TextureFile(){
		return levelList.get(currentLevel).getBg1TextureFile();
	}
	
	public String getBg2TextureFile(){
		return levelList.get(currentLevel).getBg2TextureFile();
	}
	
	
	public void resetLevelData(){
		this.currentLevel = 1;
	}
	

	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public void increaseLevel(){
		if (currentLevel < Engine.MAX_LEVEL){
			currentLevel ++;
		}		
	}
	
	/**
	 * Sax parser handler to parse the level files.
	 * See also assets/level.dtd
	 */
	private class LevelContentHandler extends DefaultHandler {

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

			
			if (localName.equals("level")) {
				int num;
				LevelData levelData;
				num = Integer.parseInt(attributes.getValue("num"));
				levelData = new LevelData(
						attributes.getValue("bg1TextureFile"),
						attributes.getValue("bg2TextureFile"),
						attributes.getValue("musicFile"),
						attributes.getValue("endTextureFile"));
				
				levelList.put(num,levelData);
				
			}
		}
	}
}
