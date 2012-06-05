package com.aeonphyxius.data;

import com.aeonphyxius.engine.Engine;

/**
 * HUDControl Object.
 * 
 * <P>
 * HUD component to show the control area
 * 
 * <P>
 * This class contains logic to display the user  controls at the bottom of the playable area 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class PlayerData {
	private boolean isDestroyed;
	private int damage;
	private int points;
	private int lives;
	private int shield;
	
	public PlayerData(){
		this.damage = 0;
		this.points = 0;
		this.isDestroyed = false;
		
		switch(Engine.difficulty){
		case Engine.DIFF_EASY:			
			this.lives = Engine.LIVES_EASY;
			this.damage = Engine.DAMAGE_EASY;
			this.shield = Engine.SHIELD_EASY;
			break;
		case Engine.DIFF_NORMAL:
			this.lives = Engine.LIVES_NORMAL;
			this.damage = Engine.DAMAGE_NORMAL;
			this.shield = Engine.SHIELD_NORMAL;
			break;			
		case Engine.DIFF_HARD:
			this.lives = Engine.LIVES_HARD;
			this.damage = Engine.DAMAGE_HARD;
			this.shield = Engine.SHIELD_HARD;
			break;
		}	
	}

	public void increaseDamage(){ 
		if (this.shield > 0){
			this.shield -= 25;
		}else{
			this.damage -=25;
		}
		
	}
	

	public void resetAllStatus(){
		this.damage = 0;
		this.points = 0;
		this.isDestroyed = false;
		
		switch(Engine.difficulty){
		case Engine.DIFF_EASY:			
			this.lives = Engine.LIVES_EASY;
			this.damage = Engine.DAMAGE_EASY;
			this.shield = Engine.SHIELD_EASY;
			break;
		case Engine.DIFF_NORMAL:
			this.lives = Engine.LIVES_NORMAL;
			this.damage = Engine.DAMAGE_NORMAL;
			this.shield = Engine.SHIELD_NORMAL;
			break;			
		case Engine.DIFF_HARD:
			this.lives = Engine.LIVES_HARD;
			this.damage = Engine.DAMAGE_HARD;
			this.shield = Engine.SHIELD_HARD;
			break;
		}		
	}	
	public void resetStatus(){
		switch(Engine.difficulty){
		case Engine.DIFF_EASY:
			this.damage = Engine.DAMAGE_EASY;
			this.shield = Engine.SHIELD_EASY;
			break;
		case Engine.DIFF_NORMAL:			
			this.damage = Engine.DAMAGE_NORMAL;
			this.shield = Engine.SHIELD_NORMAL;
			break;			
		case Engine.DIFF_HARD:			
			this.damage = Engine.DAMAGE_HARD;
			this.shield = Engine.SHIELD_HARD;
			break;
		}	
	}

	public int getDamagePercentage() {
		if (damage == 100){
			return 4;
		}else if(damage < 100 && damage >=75){
			return 3;		
		}else if(damage < 75 && damage >=50){
			return 2;		
		}else if(damage <50 && damage >=25){
			return 1;
		}		
		return 0;
	}
	
	
	public int getShieldPercentage() {
		if (shield == 100){
			return 4;
		}else if(shield < 100 && shield >=75){
			return 3;		
		}else if(shield < 75 && shield >=50){
			return 2;		
		}else if(shield <50 && shield >=25){
			return 1;
		}		
		return 0;
	}
	
	public int getShield() {
		return shield;
	}
	
	public void setShield(int shield) {
		this.shield = shield;
	}


	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	

}
