package com.aeonphyxius.engine;


public class BoundingBox {

	public int minX;
	public int minY;
	public int maxX;
	public int maxY;	
	private static BoundingBox instance = null;
	
	public static BoundingBox getInstance() {
		if (instance == null) {
			instance = new BoundingBox();
		}
		return instance;
	}
	
	public BoundingBox() {

		this.minX = 0;
		this.minY = 0;
		this.maxX = 0;
		this.maxY = 0;
	}

	public BoundingBox(BoundingBox bbox) {

		this.minX = bbox.minX;
		this.minY = bbox.minY;
		this.maxX = bbox.maxX;
		this.maxY = bbox.maxY;
	}

	public BoundingBox(int minX, int maxX, int minY, int maxY) {

		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public boolean overlaps(BoundingBox box) {

		return overlaps(box.minX, box.minY, box.maxX, box.maxY);
	}
	
	
    public boolean overlaps(float minX1, float minY1, float maxX1, float maxY1,float minX2, float minY2, float maxX2, float maxY2) {            
        if(maxX1 <= minX2 || minX1 >= maxX2)
                return false;

        if(maxY1 <= minY2 || minY1 >= maxY2)
                return false;

        return true;
}
    
	public boolean overlaps(int minX, int minY, int maxX, int maxY) {

		if (maxX <= this.minX || minX >= this.maxX)
			return false;

		if (maxY <= this.minY || minY >= this.maxY)
			return false;

		return true;
	}

	public boolean isIncludedIn(int x1, int y1, int x2, int y2) {

		if (this.minX < x1 || this.maxX > x2)
			return false;

		if (this.minY < y1 || this.maxY > y2)
			return false;

		return true;
	}

	public boolean equals(Object object) {

		if (object instanceof BoundingBox) {
			BoundingBox bbox = (BoundingBox) object;
			return (this.minX == bbox.minX) && (this.maxX == bbox.maxX)
					&& (this.minY == bbox.minY) && (this.maxY == bbox.maxY);
		}
		return false;
	}

}
