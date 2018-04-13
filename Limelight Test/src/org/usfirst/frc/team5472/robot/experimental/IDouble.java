package org.usfirst.frc.team5472.robot.experimental;

public class IDouble implements Interpolable<IDouble>, InverseInterpolable<IDouble>{
	
	public double value;
	
	public IDouble(double val) {
		value = val;
	}
	
	public IDouble clone() {
		return new IDouble(value);
	}
	
	public IDouble interpolate(IDouble obj, double t) {
		double delta = obj.value - value;
		return new IDouble(value + delta * t);
	}
	
	public double inverseInterpolate(IDouble start, IDouble end) {
		double delta = end.value - start.value;
		if(Math.abs(delta) < 1e-4)
			return 0.0;
		double t = (value - start.value) / delta;
		return t;
	}
}
