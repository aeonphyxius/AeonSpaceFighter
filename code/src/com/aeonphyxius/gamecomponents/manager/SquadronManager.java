package com.aeonphyxius.gamecomponents.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import android.content.res.AssetManager;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.gamecomponents.drawable.Enemy;


public class SquadronManager {
	
	private static SquadronManager instance = null;
	
	private ArrayList<Squadron> squadronList;
	
	protected SquadronManager() {
		squadronList = new ArrayList<Squadron>();
	}

	public static SquadronManager getInstance() {
		if (instance == null) {
			instance = new SquadronManager();
		}
		return instance;
	}
	
	public ArrayList<Squadron> getSquadronList() {
		return squadronList;
	}

	public void setSquadronList(ArrayList<Squadron> squadronList) {
		this.squadronList = squadronList;
	}

	public void loadSquadronsLevel(int level) throws Exception{
		
		AssetManager assetManager = Engine.context.getAssets();
		String fileName = "level" + level +".xml";
		InputStream is;
		try {
			is = assetManager.open(fileName);
	        SAXParserFactory spf = SAXParserFactory.newInstance();
	        SAXParser sp = spf.newSAXParser();
	        XMLReader xmlReader = sp.getXMLReader();
	        xmlReader.setContentHandler(new LevelContentHandler());
	        xmlReader.parse(new InputSource(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
		/**
		* Sax parser handler to parse the level files.
		* See also assets/level.dtd
		*/
	    private class LevelContentHandler extends DefaultHandler {

	        @Override
	        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

	        	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	        	int enemy;
	        	int xpos;
	        	
	            if (localName.equals("squadron")) {

	            	int ypos = Integer.parseInt(attributes.getValue("ypos"));
	            	
	            	// Enemy1
	                enemy = Integer.parseInt(attributes.getValue("enemy1"));
	                xpos = Integer.parseInt(attributes.getValue("xpos1"));
	                enemyList.add(new Enemy (enemy,xpos));
	            	
	                // Enemy2
	                enemy = Integer.parseInt(attributes.getValue("enemy2"));
	                xpos = Integer.parseInt(attributes.getValue("xpos2"));
	                enemyList.add(new Enemy (enemy,xpos));
	                
	                // Enemy3
	                if (attributes.getValue("enemy3")!=null){
	                	enemy = Integer.parseInt(attributes.getValue("enemy3"));
	                	xpos = Integer.parseInt(attributes.getValue("xpos3"));
	                	enemyList.add(new Enemy (enemy,xpos));
	                }
	                
	                // Enemy4
	                if (attributes.getValue("enemy4")!=null){
	                	enemy = Integer.parseInt(attributes.getValue("enemy4"));
	                	xpos = Integer.parseInt(attributes.getValue("xpos4"));
	                	enemyList.add(new Enemy (enemy,xpos));
	                }
	                
	                // Enemy5
	                if (attributes.getValue("enemy5")!=null){
	                	enemy = Integer.parseInt(attributes.getValue("enemy5"));
	                	xpos = Integer.parseInt(attributes.getValue("xpos5"));
	                	enemyList.add(new Enemy (enemy,xpos));
	                }
	                
	                System.out.print("Squadron ypos:"+ypos);
	                Squadron sq = new Squadron(enemyList,ypos);
	                squadronList.add(sq);
	            }
	        }
	    }
}
