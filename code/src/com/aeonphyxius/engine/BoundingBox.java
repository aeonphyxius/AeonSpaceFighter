package com.aeonphyxius.engine;
/**
 * EngineGL Object.
 * 
 * <P>All OpenGL related rendering operations .
 *  
 * <P>This class contains logic to display enemies, player and firepower. 
 *  
 * @author Alejandro Santiago
 * @version 1.0
 * @email alejandro@aeonphyxius.com - asantiago@uoc.edu
 */


public class BoundingBox {

	public int minX;
	public int minY;
	public int maxX;
	public int maxY;	
	private static BoundingBox instance = null;

	/**
	 * Singleton pattern implementation
	 * @return the unique instance of the Music Manager
	 */
	public static BoundingBox getInstance() {
		if (instance == null) {
			instance = new BoundingBox();
		}
		return instance;
	}

	/**
	 * 
	 */
	public BoundingBox() {

		this.minX = 0;
		this.minY = 0;
		this.maxX = 0;
		this.maxY = 0;
	}

	/**
	 * 
	 * @param bbox
	 */
	public BoundingBox(BoundingBox bbox) {

		this.minX = bbox.minX;
		this.minY = bbox.minY;
		this.maxX = bbox.maxX;
		this.maxY = bbox.maxY;
	}

	/**
	 * 
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 */
	public BoundingBox(int minX, int maxX, int minY, int maxY) {

		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	/**
	 * Overlaps the given bounding box within the current box
	 * @param box
	 * @return
	 */
	public boolean overlaps(BoundingBox box) {

		return overlaps(box.minX, box.minY, box.maxX, box.maxY);
	}

	/**
	 * Overlpas the given boxes
	 * @param minX1
	 * @param minY1
	 * @param maxX1
	 * @param maxY1
	 * @param minX2
	 * @param minY2
	 * @param maxX2
	 * @param maxY2
	 * @return
	 */
	public boolean overlaps(float minX1, float minY1, float maxX1, float maxY1,float minX2, float minY2, float maxX2, float maxY2) {            
		if(maxX1 <= minX2 || minX1 >= maxX2)
			return false;

		if(maxY1 <= minY2 || minY1 >= maxY2)
			return false;

		return true;
	}
	/**
	 * Overlpas the given box within the curren box
	 * @param minX
	 * @param minY
	 * @param maxX
	 * @param maxY
	 * @return
	 */
	public boolean overlaps(int minX, int minY, int maxX, int maxY) {

		if (maxX <= this.minX || minX >= this.maxX)
			return false;

		if (maxY <= this.minY || minY >= this.maxY)
			return false;

		return true;
	}

	/**
	 * Is included given box coordinates in the current bounding box
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean isIncludedIn(int x1, int y1, int x2, int y2) {

		if (this.minX < x1 || this.maxX > x2)
			return false;

		if (this.minY < y1 || this.maxY > y2)
			return false;

		return true;
	}

	/**
	 * Are both the same bounding box
	 */
	public boolean equals(Object object) {

		if (object instanceof BoundingBox) {
			BoundingBox bbox = (BoundingBox) object;
			return (this.minX == bbox.minX) && (this.maxX == bbox.maxX)
					&& (this.minY == bbox.minY) && (this.maxY == bbox.maxY);
		}
		return false;
	}

}
