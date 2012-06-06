package com.aeonphyxius.data;

/**
* ExplosionData Object.
* 
* <P>Data manager for explosions.
*  
* <P>Contains all information needed to display the explosion animation
*  
* @author Alejandro Santiago
* @version 1.0
* @email alejandro@aeonphyxius.com - asantiago@uoc.edu
*/

public class ExplosionData {
	
	public float x;							// Explosion x coordinate
	public float y;							// Explosion y coordinate
	public long elapsed;					// Explosion frame elapsed time
	public int animation;					// Animation frame
	public boolean isDisabled;				// is Disabled? (have we reach the end of the animation
	
	/**
	 * Construct an explosion, at x,y
	 * @param x Coordinate
	 * @param y Coordinate
	 */
	public ExplosionData(float x, float y){
		this.x = x;
		this.y = y;
		elapsed = System.currentTimeMillis();
		animation = 0;
		isDisabled = false;		
	}

}
