package com.aeonphyxius.data;

public class ExplosionData {
	
	public float x;
	public float y;
	public long elapsed;
	public int animation;
	public boolean isDisabled;
	
	public ExplosionData(float x, float y){
		this.x = x;
		this.y = y;
		elapsed = System.currentTimeMillis();
		animation = 0;
		isDisabled = false;		
	}

}
