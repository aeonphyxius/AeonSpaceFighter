package com.aeonphyxius.gamecomponents.manager;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.MusicManager;
import com.aeonphyxius.gamecomponents.drawable.Weapon;


public class WeaponManager {
	private static WeaponManager instance = null;

	protected WeaponManager() {
		playeFireList = new Vector<Weapon>();
	}

	public static WeaponManager getInstance() {
		if (instance == null) {
			instance = new WeaponManager();
		}
		return instance;
	}

	private Vector<Weapon> playeFireList;
	private Vector<Weapon> enemyFireList;
	
	
	

	public Vector<Weapon> getPlayeFireList() {
		return playeFireList;
	}

	public void setPlayeFireList(Vector<Weapon> playeFireList) {
		this.playeFireList = playeFireList;
	}

	public Vector<Weapon> getEnemyFireList() {
		return enemyFireList;
	}

	public void setEnemyFireList(Vector<Weapon> enemyFireList) {
		this.enemyFireList = enemyFireList;
	}

	public void initializePlayerWeapon(){
		for(int x = 0; x < 4; x++){
			playeFireList.add(new Weapon());
			}
		
		playeFireList.get(0).shotFired = true;
		playeFireList.get(0).posX = Engine.playerBankPosX;
		playeFireList.get(0).posY = 1.25f;		
		
	}
	

	public void firePlayerWeapon(GL10 gl,int[] spriteSheets){
		for(int x = 0; x < playeFireList.size(); x++  ){
			if (playeFireList.get(x).shotFired){
				int nextShot = 0;
				if (playeFireList.get(x).posY > 5.25){ // TODO: add constant
					playeFireList.get(x).shotFired = false;
				}else{
					if (playeFireList.get(x).posY> 2){
						if (x == playeFireList.size()-2){
							nextShot = 0;
						}else{
							nextShot = x + 1;
						}
						if (playeFireList.get(nextShot).shotFired == false){
							MusicManager.getInstance().playSound("blaster");
							playeFireList.get(nextShot).shotFired = true;
							playeFireList.get(nextShot).posX = Engine.playerBankPosX;
							playeFireList.get(nextShot).posY = 1.25f;
						}
						
					}					
					playeFireList.get(x).posY += Engine.PLAYER_BULLET_SPEED;
					gl.glMatrixMode(GL10.GL_MODELVIEW);
					gl.glLoadIdentity();
					gl.glPushMatrix();
					gl.glScalef(.15f, .15f, 0f);
					gl.glTranslatef(playeFireList.get(x).posX, playeFireList.get(x).posY, 0f);
				    
					gl.glMatrixMode(GL10.GL_TEXTURE);
					gl.glLoadIdentity();
					gl.glTranslatef(0.0f,0.0f, 0.0f); 
					   
					playeFireList.get(x).draw(gl,spriteSheets);
					gl.glPopMatrix();
					gl.glLoadIdentity();

				}
			}
		}
	}



}
