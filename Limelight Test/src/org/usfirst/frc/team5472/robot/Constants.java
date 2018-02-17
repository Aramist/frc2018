package org.usfirst.frc.team5472.robot;

public class Constants {
	public static final String AUTONOMOUS_CHOOSER_NAME = "Select an Autonomous Program: ";

	public static enum AutonomousProgram {

		DEFAULT("Drive Straight"), LIMELIGHT("Find the Switch (Limelight Vision)"), JEVOIS("Find the Switch (JeVois Vision)");

		private String title;

		private AutonomousProgram(String title) {
			this.title = title;
		}

		@Override
		public String toString() {
			return this.title;
		}

		public String getName() {
			return this.title;
		}

	}

	// Measured in meters.
	public static final double WHEEL_DIAMETER = 0.1016;
	// Measured in meters. Distance from center of left wheels to center of
	// right wheels.
	public static final double ROBOT_WIDTH = 0.7112;
	public static final double ROBOT_LENGTH = 0.8382;
	public static final double V_CONSTANT = 0.30;

	public static final double ROBOT_WHEELBASE_WIDTH = 0.6731;
	public static final double LEFT_ENCODER_TICKS_PER_METER = 12300;
	public static final double RIGHT_ENCODER_TICKS_PER_METER = 12800;
	public static final double LEFT_ENCODER_TICKS_PER_REV = LEFT_ENCODER_TICKS_PER_METER * 2 * Math.PI * WHEEL_DIAMETER;
	public static final double RIGHT_ENCODER_TICKS_PER_REV = RIGHT_ENCODER_TICKS_PER_METER * 2 * Math.PI * WHEEL_DIAMETER;
	public static final double ROBOT_MAX_VELOCITY = 5.0; // m/s
	public static final double DRIVE_FOLLOWER_P = 0.6;
	public static final double DRIVE_FOLLOWER_I = 0.0;
	public static final double DRIVE_FOLLOWER_D = 0.2;
	public static final double DRIVE_FOLLOWER_V = 1.0 / ROBOT_MAX_VELOCITY;
	public static final double DRIVE_FOLLOWER_A = 0;

	public static final int INTAKE_LEFT_MOTOR_CAN = 6;
	public static final int INTAKE_RIGHT_MOTOR_CAN = 7;
	public static final int INTAKE_SOLENOID_ID_FORW = 1; // double solenoid
	public static final int INTAKE_SOLENOID_ID_BACK = 2;
	public static final double INTAKE_INPUT_SPEED = 1.0;
	public static final double INTAKE_OUTPUT_SPEED = 0.4;

	public static final int DRIVE_LEFT_TALON_CAN = 1;
	public static final int DRIVE_LEFT_FOLLOWER_CAN = 2;
	public static final int DRIVE_RIGHT_TALON_CAN = 4;
	public static final int DRIVE_RIGHT_FOLLOWER_CAN = 3;
	public static final int DRIVE_SHIFT_SOLENOID = 0;
	public static final double DRIVE_AUTO_OUTPUT_LIMIT = 0.6;
	public static final double DRIVE_AUTO_TURN_P = 0.4;
	public static final double DRIVE_AUTO_TURN_I = 0.0;
	public static final double DRIVE_AUTO_TURN_D = 0.4;
	public static final double LIMELIGHT_APPROACH_BOX_KP = 0.04;

	public static final int LIFT_TALON_CAN = 5;
	public static final double LIFT_PIDF_P = 0.0003;
	public static final double LIFT_PIDF_I = 0.0000;
	public static final double LIFT_PIDF_D = 0.0001;
	public static final double LIFT_PIDF_F = 0.3000;
	public static final int LIFT_PIDF_INTZONE = 40;
	
	public static final int LED_RED_DIO = 0;
	public static final int LED_GREEN_DIO = 1;
	public static final int LED_BLUE_DIO = 2;
}
