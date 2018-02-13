package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.commands.JoystickDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSubsystem extends Subsystem {

	private AHRS navx;
	private TalonSRX left, right, leftFollower, rightFollower;
	private ControlMode controlMode;
	private Solenoid shiftSolenoid;// , rightSolenoid;

	public DriveSubsystem() {
		navx = new AHRS(Port.kMXP);

		left = new TalonSRX(Constants.DRIVE_LEFT_TALON_CAN);
		right = new TalonSRX(Constants.DRIVE_RIGHT_TALON_CAN);
		leftFollower = new TalonSRX(Constants.DRIVE_LEFT_FOLLOWER_CAN);
		rightFollower = new TalonSRX(Constants.DRIVE_RIGHT_FOLLOWER_CAN);
		shiftSolenoid = new Solenoid(Constants.DRIVE_SHIFT_SOLENOID);

		left.setInverted(false);
		leftFollower.setInverted(false);
		right.setInverted(true);
		rightFollower.setInverted(true);

		// left.setNeutralMode(NeutralMode.Brake);
		// followerLeft.setNeutralMode(NeutralMode.Brake);
		// right.setNeutralMode(NeutralMode.Brake);
		// followerRight.setNeutralMode(NeutralMode.Brake);

		// Results of experiment:
		// Left: 12489 Ticks per Meter
		// Right: 12270 Ticks per Meter
		leftFollower.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		leftFollower.setSensorPhase(true);
		// followerLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,
		// 0, 100);
		rightFollower.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		rightFollower.setSensorPhase(true);
		// followerRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,
		// 0, 100);

		controlMode = ControlMode.PercentOutput;
		
		left.setNeutralMode(NeutralMode.Coast);
		leftFollower.setNeutralMode(NeutralMode.Coast);
		right.setNeutralMode(NeutralMode.Coast);
		rightFollower.setNeutralMode(NeutralMode.Coast);

		left.set(controlMode, 10);
		leftFollower.set(controlMode, 10);
		right.set(controlMode, 10);
		rightFollower.set(controlMode, 10);
		
		highGear();
	}

	public void setControlMode(ControlMode newMode) {
		controlMode = newMode;
	}

	public void drive(double left, double right) {
		this.left.set(controlMode, left);
		this.right.set(controlMode, right);
		this.leftFollower.set(controlMode, left);
		this.rightFollower.set(controlMode, right);
	}
	
	public void turn(double throttle, double twist) {
		left.set(controlMode, throttle + twist);
		leftFollower.set(controlMode, throttle - twist);
		right.set(controlMode, throttle - twist);
		rightFollower.set(controlMode, throttle + twist);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveCommand());
	}

	public void shiftGear() {
		shiftSolenoid.set(!shiftSolenoid.get());
	}

	public void highGear() {
		shiftSolenoid.set(true);
	}

	public void lowGear() {
		shiftSolenoid.set(false);
	}

	public void resetEncoders() {
		leftFollower.setSelectedSensorPosition(0, 0, 0);
		rightFollower.setSelectedSensorPosition(0, 0, 0);
	}

	public int getLeftRaw() {
		return leftFollower.getSelectedSensorPosition(0);
	}

	public double getLeftPosition() {
		return leftFollower.getSelectedSensorPosition(0) * Constants.LEFT_ENCODER_TICKS_PER_METER;
	}

	public double getLeftVelocity() {
		return leftFollower.getSelectedSensorVelocity(0) * Constants.LEFT_ENCODER_TICKS_PER_METER;
	}

	public int getRightRaw() {
		return rightFollower.getSelectedSensorPosition(0);
	}

	public double getRightPosition() {
		return rightFollower.getSelectedSensorPosition(0) * Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	public double getRightVelocity() {
		return rightFollower.getSelectedSensorVelocity(0) * Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	public double getHeading() {
		return navx.getAngle();
	}

	public void resetHeading() {
		navx.zeroYaw();
	}
	
	// Autonomous Stuff
	public static double truncate(double value, double limit) {
		limit = Math.abs(limit);
		return value > limit ? limit : value < -limit ? -limit : value;
	}
	
	private final PIDSource driveVelocitySource = new PIDSource(){
		public double pidGet() {
			return (getLeftVelocity() + getRightVelocity()) / 2.0;
		}
		public PIDSourceType getPIDSourceType() {return PIDSourceType.kRate;}
		public void setPIDSourceType(PIDSourceType t) {}
	};
	
	private final PIDOutput driveOutput = (double output) -> {
		drive(output, output);
	};
	
	private final PIDSource drivePositionSource = new PIDSource() {
		public double pidGet() {
			return (getLeftPosition() + getRightPosition()) / 2;
		}
		public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}
		public void setPIDSourceType(PIDSourceType t) {}
	};
	
	public final PIDController drivePositionController = new PIDController(Constants.ENCODER_FOLLOWER_P, Constants.ENCODER_FOLLOWER_I, Constants.ENCODER_FOLLOWER_D,
																			Constants.ENCODER_FOLLOWER_V, drivePositionSource, driveOutput);
	
	public void reportDebugInformation() {
		/**
		 * Reports the following information: - Voltage......(For each motor
		 * controller) - Current......(For each motor controller) -
		 * Temperature..(For each motor controller) - Velocity.....(Left side
		 * and Right side) - Encoder Readings - Bearing......(Relative to
		 * starting position of robot, not North) - Heading......(Relative to
		 * vision target, if any)
		 */

		// SmartDashboard.putNumber("Left Voltage", left.getBusVoltage());
		// SmartDashboard.putNumber("Left Current", left.getOutputCurrent());
		// SmartDashboard.putNumber("Left Temperature", left.getTemperature());
		// SmartDashboard.putNumber("Left Output",
		// left.getMotorOutputPercent());
		SmartDashboard.putNumber("Left Encoder", getLeftPosition());
		SmartDashboard.putNumber("Left Encoder Velocity", getLeftVelocity());
		//
		//
		// SmartDashboard.putNumber("Right Current", right.getOutputCurrent());
		// SmartDashboard.putNumber("Right Voltage", right.getBusVoltage());
		// SmartDashboard.putNumber("Right Temperature",
		// right.getTemperature());
		// SmartDashboard.putNumber("Right Output",
		// right.getMotorOutputPercent());
		SmartDashboard.putNumber("Right Encoder", getRightPosition());
		SmartDashboard.putNumber("Right Encoder Velocity", getRightVelocity());
		// SmartDashboard.putNumber("Bearing", navx.getAngle());
	}
}
