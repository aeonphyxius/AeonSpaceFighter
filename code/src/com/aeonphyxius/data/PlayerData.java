package com.aeonphyxius.data;

import com.aeonphyxius.engine.Engine;

/**
 * PlayerData Object.
 * 
 * <P>
 * Player information. 
 * 
 * <P>
 * This class encapsulates all information related to the video game player (lives, damage, shields, points.... etc). 
 * 
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */

public class PlayerData {
	private boolean isDestroyed;				// Has the player been destroyed
	private int damage;							// Damage on the player's ship
	private int points;							// Player's points	
	private int lives;							// Player's remaining lives
	private int shield;							// Player's ship shields

	/**
	 * Default constructor, defining initial player information
	 */
	public PlayerData(){
		this.damage = 0;
		this.points = 0;
		this.isDestroyed = false;

		// Using default values
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


	/**
	 * Increase player points depending on game level
	 */
	public void increasePoints(){ 
		switch(Engine.difficulty){
		case Engine.DIFF_EASY:	
			this.points += Engine.POINTS_EASY;
			break;
		case Engine.DIFF_NORMAL:
			this.points += Engine.POINTS_NORMAL;
			break;			
		case Engine.DIFF_HARD:
			this.points += Engine.POINTS_HARD;
			break;
		}
	}


	/**
	 * Increase players Damage on shields and structure depending on game level
	 */
	public void increaseDamage(){ 
		switch(Engine.difficulty){
		case Engine.DIFF_EASY:	
			if (this.shield > 0){
				this.shield -= Engine.ENGINE_SHIELD_EASY;
			}else{
				this.damage -=Engine.ENGINE_DAMAGE_EASY;
			}
			break;
		case Engine.DIFF_NORMAL:
			if (this.shield > 0){
				this.shield -= Engine.ENGINE_SHIELD_NORMAL;
			}else{
				this.damage -=Engine.ENGINE_DAMAGE_NORMAL;
			}
			break;			
		case Engine.DIFF_HARD:
			if (this.shield > 0){
				this.shield -= Engine.ENGINE_SHIELD_HARD;
			}else{
				this.damage -=Engine.ENGINE_DAMAGE_NORMAL;
			}
			break;
		}
	}


	/**
	 * When the game have finished, restart all Player's data to default values
	 */
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

	/**
	 * When the player space ship have been destroyed, reset the space ship related information
	 */
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

	/**
	 * The structural damage of player's space ship status is displayed in 25% slots
	 * @return percentage containing structural status
	 */
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

	/**
	 * The structural damage of player's space ship status is displayed in 25% slots
	 * @return percentage containing shields status
	 */
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
