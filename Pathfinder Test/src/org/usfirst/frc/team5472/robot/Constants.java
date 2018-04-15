package org.usfirst.frc.team5472.robot;

public class Constants {
	
	public static final int LEFT_TALON = 1;
	public static final int LEFT_FOLLOWER = 2;
	public static final int RIGHT_TALON = 3;
	public static final int RIGHT_FOLLOWER = 4;
	
	
	public static final double WHEEL_DIAMETER = 0.1016;
	public static final double ROBOT_WHEELBASE_WIDTH = 0.6731;
	public static final double ENCODER_TICKS_PER_METER = 12833;
	public static final int ENCODER_TICKS_PER_REV = 4096;
	
	
	public static final double PATH_DT = 0.05;
	public static final double PATH_P = 5.00;
	public static final double PATH_I = 0.03;
	public static final double PATH_V = 0.05;
	public static final double PATH_FFV = 1.00;
	public static final double PATH_FFA = 0.05;
	
	public static final double PATH_HEADING_P = 0.1;
}
