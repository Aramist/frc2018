package org.usfirst.frc.team5472.robot.experimental;

public class Mat3 implements Interpolable<Mat3>{
	
	
	public static Mat3 I(){
		Mat3 identity = new Mat3();
		identity.set(0, 0, 1);
		identity.set(1, 1, 1);
		identity.set(2, 2, 1);
		return identity;
	}
	
	
	private IDouble[][] data;
	
	
	public Mat3() {
		this(0.0);
	}
	
	public Mat3(double fill) {
		data = new IDouble[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				data[i][j] = new IDouble(fill);
	}
	
	public Mat3(Mat3 copy) {
		data = new IDouble[3][3];
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				data[i][j] = copy.get(i, j).clone();
	}
	
	public IDouble get(int i, int j) {
		return data[i][j].clone();
	}
	
	public void set(int i, int j, double d) {
		data[i][j].value = d;
	}
	
	public void set(int i, int j, IDouble d) {
		data[i][j].value = d.value;
	}
	
	public Mat3 add(Mat3 mat) {
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				set(i, j, get(i, j).value + mat.get(i, j).value);
		return this;
	}
	
	public Mat3 sub(Mat3 mat) {
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				set(i, j, get(i, j).value - mat.get(i, j).value);
		return this;
	}
	
	public Mat3 mult(double d) {
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				set(i, j, get(i, j).value * d);
		return this;
	}
	
	public Mat3 div(double d) {
		return mult(1.0 / d);
	}
	
	public Mat3 mult(Mat3 mat) {
		double aa = get(0, 0).value * mat.get(0, 0).value
				+ get(0, 1).value * mat.get(1, 0).value
				+ get(0, 2).value * mat.get(2, 0).value;
		
		double ab = get(1, 0).value * mat.get(0, 0).value
				+ get(1, 1).value * mat.get(1, 0).value
				+ get(1, 2).value * mat.get(2, 0).value;
		
		double ac = get(2, 0).value * mat.get(0, 0).value
				+ get(2, 1).value * mat.get(1, 0).value
				+ get(2, 2).value * mat.get(2, 0).value;
		
		
		double ba = get(0, 0).value * mat.get(0, 1).value
				+ get(0, 1).value * mat.get(1, 1).value
				+ get(0, 2).value * mat.get(2, 1).value;
		
		double bb = get(1, 0).value * mat.get(0, 1).value
				+ get(1, 1).value * mat.get(1, 1).value
				+ get(1, 2).value * mat.get(2, 1).value;
		
		double bc = get(2, 0).value * mat.get(0, 1).value
				+ get(2, 1).value * mat.get(1, 1).value
				+ get(2, 2).value * mat.get(2, 1).value;
		
		
		double ca = get(0, 0).value * mat.get(0, 2).value
				+ get(0, 1).value * mat.get(1, 2).value
				+ get(0, 2).value * mat.get(2, 2).value;
		
		double cb = get(1, 0).value * mat.get(0, 2).value
				+ get(1, 1).value * mat.get(1, 2).value
				+ get(1, 2).value * mat.get(2, 2).value;
		
		double cc = get(2, 0).value * mat.get(0, 2).value
				+ get(2, 1).value * mat.get(1, 2).value
				+ get(2, 2).value * mat.get(2, 2).value;
		
		this.set(0, 0, aa);
		this.set(0, 1, ab);
		this.set(0, 2, ac);
		
		this.set(1, 0, ba);
		this.set(1, 1, bb);
		this.set(1, 2, bc);
		
		this.set(2, 0, ca);
		this.set(2, 1, cb);
		this.set(2, 2, cc);
		
		return this;
	}
	
	public Vec3 mult(Vec3 vec) {
		return vec.mult(this);
	}
	
	
	public Mat3 interpolate(Mat3 mat, double t) {
		Mat3 toReturn = new Mat3();
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				toReturn.set(i, j, get(i, j).interpolate(mat.get(i, j), t));
		return toReturn;
	}
}
