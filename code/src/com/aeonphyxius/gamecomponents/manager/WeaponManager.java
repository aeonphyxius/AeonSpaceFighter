package com.aeonphyxius.gamecomponents.manager;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.aeonphyxius.engine.Engine;
import com.aeonphyxius.engine.MusicManager;
import com.aeonphyxius.gamecomponents.drawable.Weapon;


public class WeaponManager {
	private static WeaponManager instance = null;

	protected WeaponManager() {
		playeFireList = new ArrayList<Weapon>();
	}

	public static WeaponManager getInstance() {
		if (instance == null) {
			instance = new WeaponManager();
		}
		return instance;
	}

	private ArrayList<Weapon> playeFireList;
	private ArrayList<Weapon> enemyFireList;
	
	
	

	public ArrayList<Weapon> getPlayeFireList() {
		return playeFireList;
	}

	public void setPlayeFireList(ArrayList<Weapon> playeFireList) {
		this.playeFireList = playeFireList;
	}

	public ArrayList<Weapon> getEnemyFireList() {
		return enemyFireList;
	}

	public void setEnemyFireList(ArrayList<Weapon> enemyFireList) {
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
		for(int x = 0; x < 4; x++  ){
			if (playeFireList.get(x).shotFired){
				int nextShot = 0;
				if (playeFireList.get(x).posY > 5.25){
					playeFireList.get(x).shotFired = false;
				}else{
					if (playeFireList.get(x).posY> 2){
						if (x == 3){
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
