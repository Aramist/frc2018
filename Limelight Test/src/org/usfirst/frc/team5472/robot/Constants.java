package org.usfirst.frc.team5472.robot;

import com.team5472.robot.pathfinder.Trajectory;
import com.team5472.robot.pathfinder.fit.FitMethod;

public class Constants {
	public static final String AUTONOMOUS_CHOOSER_NAME = "Select an Autonomous Program: ";
	
	public static enum AutonomousProgram{

		DEFAULT("Drive Straight"), LIMELIGHT("Find the Switch (Limelight Vision)"), JEVOIS("Find the Switch (JeVois Vision)");
		
		private String title;
		private AutonomousProgram(String title) {
			this.title = title;
		}
		
		public String toString() {
			return this.title;
		}
		
		public String getName() {
			return this.title;
		}
		
	}
	
	
	public static final double WHEEL_DIAMETER = 0.10; // meters
	public static final double ROBOT_WHEELBASE_WIDTH = 0.75; // meters. Distance from center of left wheels to center of right wheels.
	public static final double LEFT_ENCODER_TICKS_PER_METER = 12489.0;
	public static final double RIGHT_ENCODER_TICKS_PER_METER = 12270.0;
	public static final double LEFT_ENCODER_TICKS_PER_REV = LEFT_ENCODER_TICKS_PER_METER * 2.0 * Math.PI * WHEEL_DIAMETER; // ticks / revolution
	public static final double RIGHT_ENCODER_TICKS_PER_REV = RIGHT_ENCODER_TICKS_PER_METER * 2.0 * Math.PI * WHEEL_DIAMETER; // ticks / revolution
	public static final double ROBOT_MAX_VELOCITY = 2.0; // m/s
	public static final double ENCODER_FOLLOWER_P = 1.0;
	public static final double ENCODER_FOLLOWER_I = 0.0;
	public static final double ENCODER_FOLLOWER_D = 0.0;
	public static final double ENCODER_FOLLOWER_V = 1.0 / ROBOT_MAX_VELOCITY;
	public static final double ENCODER_FOLLOWER_A = 0.0;
	public static final Trajectory.Config TRAJECTORY_CONFIG = new Trajectory.Config(FitMethod.HERMITE_QUINTIC, 1, 0.05, ROBOT_MAX_VELOCITY, 1, 60);
	
	public static final int INTAKE_LEFT_MOTOR_PWM = 1;
	public static final int INTAKE_RIGHT_MOTOR_PWM = 2;
	public static final int INTAKE_SOLENOID_ID_FORW = 0; //It's a double solenoid
	public static final int INTAKE_SOLENOID_ID_BACK = 1;
	public static final double INTAKE_INPUT_SPEED = 0.6;
	public static final double INTAKE_OUTPUT_SPEED = 1.0;
	
	public static final int DRIVE_LEFT_TALON_CAN = 3;
	public static final int DRIVE_LEFT_FOLLOWER_CAN = 5;
	public static final int DRIVE_RIGHT_TALON_CAN = 4;
	public static final int DRIVE_RIGHT_FOLLOWER_CAN = 6;
	
	public static final double LIMELIGHT_TARGET_AREA_THRESHOLD = 90;
	public static final double LIMELIGHT_APPROACH_BOX_KP = 0.01;
}
