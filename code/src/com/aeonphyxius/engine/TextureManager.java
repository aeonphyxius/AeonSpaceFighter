package com.aeonphyxius.engine;

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
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

/**
 * Texture Object.
 * 
 * <P>
 * Texture class, containing all the information needed to manage textures
 * 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class TextureManager {

	
	private static TextureManager instance = null;			// Singleton implementation
	private Map<Integer, String> textureList;


	/**
	 * Singleton implementation of the unique instance of this class
	 * @return
	 */
	public static TextureManager getInstance() {
		if (instance == null) {
			instance = new TextureManager();
		}
		return instance;
	}

	private TextureManager(){
		textureList = new Hashtable<Integer, String>();
	}
	
	
	
	/**
	 * Generates textures
	 * @param gl
	 */
	public TextureManager(GL10 gl){
		

	}

	/**
	 * Loads all the squadrons from the level XML file
	 * @param level
	 * @throws Exception
	 */
	public void loadTextures(GL10 gl) throws Exception{

		AssetManager assetManager = Engine.context.getAssets();
		String fileName = "textures.xml";
		InputStream is;
		try {
			is = assetManager.open(fileName);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xmlReader = sp.getXMLReader();
			xmlReader.setContentHandler(new TexturesContentHandler());
			xmlReader.parse(new InputSource(is));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		for (Map.Entry<Integer, String> entry : textureList.entrySet()) {
		    Integer textureKey = entry.getKey();
		    String textureFile = entry.getValue();
		    loadTexture(gl,textureFile,Engine.context,textureKey,false);
		}
	}


	public void loadTexture(GL10 gl,String fileName,int textureNumber,boolean isBackGround) {
		this.loadTexture(gl, fileName,Engine.context, textureNumber,isBackGround);
		
	}
	
	/**
	 * 
	 * @param gl
	 * @param fileName
	 * @param context
	 * @param textureNumber
	 */
	private void loadTexture(GL10 gl,String fileName, Context context,int textureNumber,boolean isBackGround) {

		InputStream imagestream;
		Bitmap bitmap = null;
		try {
			imagestream = context.getAssets().open(fileName);			
			bitmap = BitmapFactory.decodeStream(imagestream);
			imagestream.close();
			imagestream = null;
		}catch(Exception e){

		}
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureNumber);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		if (isBackGround){
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		}else{
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
		}
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		bitmap.recycle();
	}
	
	
	/**
	 * To load the texture given by parameter. 
	 * @param gl
	 * @param texture
	 * @param context
	 * @param textureNumber
	 * @return array containing the loaded textures ids
	 */
	//public int[] loadTexture(GL10 gl,int texture, Context context,int textureNumber) {
	/*public int[] loadTexture(GL10 gl,String fileName, Context context,int textureNumber) {
		//InputStream imagestream = context.getResources().openRawResource(texture);
		InputStream imagestream;
		Bitmap bitmap = null;
		try {
			imagestream = context.getAssets().open(fileName);			
			bitmap = BitmapFactory.decodeStream(imagestream);
			imagestream.close();
			imagestream = null;
		}catch(Exception e){

		}		
		//textures[textureNumber] = textureNumber;
		//gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[textureNumber]);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureNumber);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		bitmap.recycle();

		//return textures;
	}*/
	
	
	/**
	 * Sax parser handler to parse the level files.
	 * See also assets/level.dtd
	 */
	private class TexturesContentHandler extends DefaultHandler {

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

			int textireIndex;
			String textureFile;

			if (localName.equals("texture")) {
				textireIndex = Integer.parseInt(attributes.getValue("index"));	            	
				textureFile = attributes.getValue("fileName");
				textureList.put(textireIndex,textureFile);
				
			}
		}
	}	    

}
