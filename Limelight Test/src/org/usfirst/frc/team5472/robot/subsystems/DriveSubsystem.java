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
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	private AHRS navx;
	private TalonSRX left, right, leftFollower, rightFollower;
	private ControlMode controlMode;
	private Solenoid shiftSolenoid;

	public DriveSubsystem() {
		navx = new AHRS(SPI.Port.kMXP);

		left = new TalonSRX(Constants.DRIVE_LEFT_TALON_CAN);
		right = new TalonSRX(Constants.DRIVE_RIGHT_TALON_CAN);
		leftFollower = new TalonSRX(Constants.DRIVE_LEFT_FOLLOWER_CAN);
		rightFollower = new TalonSRX(Constants.DRIVE_RIGHT_FOLLOWER_CAN);
		shiftSolenoid = new Solenoid(Constants.DRIVE_SHIFT_SOLENOID);

		left.setInverted(false);
		leftFollower.setInverted(false);
		right.setInverted(true);
		rightFollower.setInverted(true);
		
		leftFollower.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		leftFollower.setSensorPhase(true);
		rightFollower.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		rightFollower.setSensorPhase(true);

		controlMode = ControlMode.PercentOutput;
		
		left.setNeutralMode(NeutralMode.Brake);
		leftFollower.setNeutralMode(NeutralMode.Coast);
		right.setNeutralMode(NeutralMode.Brake);
		rightFollower.setNeutralMode(NeutralMode.Coast);

		left.set(controlMode, 0);
		leftFollower.set(controlMode, 0);
		right.set(controlMode, 0);
		rightFollower.set(controlMode, 0);
		
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
		return leftFollower.getSelectedSensorPosition(0) / Constants.LEFT_ENCODER_TICKS_PER_METER;
	}

	public double getLeftVelocity() {
		return leftFollower.getSelectedSensorVelocity(0) / Constants.LEFT_ENCODER_TICKS_PER_METER;
	}

	public int getRightRaw() {
		return rightFollower.getSelectedSensorPosition(0);
	}

	public double getRightPosition() {
		return rightFollower.getSelectedSensorPosition(0) / Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	public double getRightVelocity() {
		return rightFollower.getSelectedSensorVelocity(0) / Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	public double getHeading() {
		return -navx.getAngle();
	}

	public void resetHeading() {
		navx.zeroYaw();
	}
	
	// Autonomous Stuff
	public static double truncate(double value, double limit) {
		limit = Math.abs(limit);
		return value > limit ? limit : value < -limit ? -limit : value;
	}
	
	private final PIDOutput driveOutput = (double output) -> {
		drive(output, output);
	};
	
	private final PIDOutput turnOutput = (double output) -> {
		drive(-output, output);
	};
	
	private final PIDSource turnAngleSource = new PIDSource() {
		public double pidGet() {
			return getHeading();
		}
		public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}
		public void setPIDSourceType(PIDSourceType t) {}
	};
	
	private final PIDSource drivePositionSource = new PIDSource() {
		public double pidGet() {
			return (getLeftPosition() + getRightPosition()) / 2;
		}
		public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}
		public void setPIDSourceType(PIDSourceType t) {}
	};
	
	public final PIDController drivePositionController = new PIDController(Constants.DRIVE_FOLLOWER_P, Constants.DRIVE_FOLLOWER_I, Constants.DRIVE_FOLLOWER_D,
																			Constants.DRIVE_FOLLOWER_V, drivePositionSource, driveOutput);
	public final PIDController turnAngleController = new PIDController(Constants.DRIVE_AUTO_TURN_P, Constants.DRIVE_AUTO_TURN_I, Constants.DRIVE_AUTO_TURN_D, turnAngleSource, turnOutput);
}
