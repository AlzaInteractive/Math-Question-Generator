package com.alza.quiz.model.geom;

public class Point3D {
	public double x,y,z;

	public Point3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * 
	 * @param ratio
	 * @param angle in radian
	 * @return point projected in 2d space using cabinet projection (60 deg)
	 */
	public Point2D getProjectedPoint() {
		double angle = Math.PI/3;
		double newX = this.x + (0.5 * this.z * Math.cos(angle));
		double newY = this.y - (0.5 * this.z * Math.sin(angle));
		return new Point2D((int)newX, (int)newY);
	}
	
	public Point3D move(double xDistance, double yDistance, double zDistance){
		return new Point3D(x+xDistance, y+yDistance, z+zDistance);
	}
	
	public static Point3D getMidPoint(Point3D p1, Point3D p2){
		double x = p1.x + (p2.x-p1.x)/2;
		double y = p1.y + (p2.y-p1.y)/2;
		double z = p1.z + (p2.z-p1.z)/2;
		return new Point3D(x, y, z);
	}
	
	@Override
	public String toString() {
		return "("+(int)x+","+(int)y+","+(int)z+")";
	}

}
