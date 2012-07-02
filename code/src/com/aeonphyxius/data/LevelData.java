package com.aeonphyxius.data;


/**
* LevelData Object.
* 
* <P>Data manager for level.
*  
* <P>Contains all information needed to manage each level (background, level number)
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class LevelData {
	
	
	private String bg1TextureFile;
	private String bg2TextureFile;
	private String musicFile;
	private String endTextureFile;
	
	
	
	public LevelData(String bg1TextureFile, String bg2TextureFile, String musicFile, String endTextureFile) {
		
		this.bg1TextureFile = bg1TextureFile;
		this.bg2TextureFile = bg2TextureFile;
		this.musicFile = musicFile;
		this.endTextureFile = endTextureFile;
	}
	
	
	public String getBg1TextureFile() {
		return bg1TextureFile;
	}
	public void setBg1TextureFile(String bg1TextureFile) {
		this.bg1TextureFile = bg1TextureFile;
	}
	public String getBg2TextureFile() {
		return bg2TextureFile;
	}
	public void setBg2TextureFile(String bg2TextureFile) {
		this.bg2TextureFile = bg2TextureFile;
	}
	public String getMusicFile() {
		return musicFile;
	}
	public void setMusicFile(String musicFile) {
		this.musicFile = musicFile;
	}
	public String getEndTextureFile() {
		return endTextureFile;
	}
	public void setEndTextureFile(String endTextureFile) {
		this.endTextureFile = endTextureFile;
	}
	
	
	

}
