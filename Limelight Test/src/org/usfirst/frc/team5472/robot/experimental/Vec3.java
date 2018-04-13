package org.usfirst.frc.team5472.robot.experimental;

public class Vec3 implements Interpolable<Vec3>{
	
	public IDouble x, y, z;
	
	
	public Vec3() {
		this(0.0, 0.0, 0.0);
	}
	
	
	public Vec3(Vec3 toCopy) {
		
	}
	
	public Vec3(IDouble x, IDouble y, IDouble z) {
		this(x.value, y.value, z.value); // Avoid using same reference, copy the data
	}
	
	public Vec3(double x, double y, double z) {
		this.x = new IDouble(x);
		this.y = new IDouble(y);
		this.z = new IDouble(z);
	}
	
	
	public Vec3 clone() {
		return new Vec3(x, y, z);
	}
	
	public Vec3 add(Vec3 vec) {
		this.x.value += vec.x.value;
		this.y.value += vec.y.value;
		this.z.value += vec.z.value;
		return this;
	}
	
	public Vec3 sub(Vec3 vec) {
		return add(vec.clone().mult(-1.0));
	}
	
	public Vec3 mult(double scalar) {
		this.x.value *= scalar;
		this.y.value *= scalar;
		this.z.value *= scalar;
		return this;
	}
	
	public Vec3 div(double scalar) {
		return mult(1.0 / scalar);
	}
	
	public double magSquared() {
		return x.value * x.value
				+ y.value * y.value
				+ z.value * z.value;
	}
	
	public double mag() {
		return Math.sqrt(magSquared());
	}
	
	public Vec3 mult(Mat3 mat) {
		// Modifies this object. returns Mat * vec
		double newX = mat.get(0, 0).value * x.value + mat.get(0, 1).value * y.value + mat.get(0, 2).value * z.value;
		double newY = mat.get(1, 0).value * x.value + mat.get(1, 1).value * y.value + mat.get(1, 2).value * z.value;
		double newZ = mat.get(2, 0).value * x.value + mat.get(2, 1).value * y.value + mat.get(2, 2).value * z.value;
		
		this.x.value = newX;
		this.y.value = newY;
		this.z.value = newZ;
		
		return this;
	}
	
	
	public Vec3 interpolate(Vec3 vec, double t) {
		Vec3 toReturn = new Vec3();
		toReturn.x = this.x.interpolate(vec.x, t);
		toReturn.y = this.y.interpolate(vec.y, t);
		toReturn.z = this.z.interpolate(vec.z, t);
		return toReturn;
	}
}
