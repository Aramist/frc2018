package org.usfirst.frc.team5472.robot.experimental;

public interface Interpolable<T> {
	public T interpolate(T obj, double t);
}
