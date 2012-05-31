package com.aeonphyxius.data;

import com.aeonphyxius.engine.Engine;

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
		this.shield = 100;
		
		
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
		this.damage += Engine.difficulty;
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
