package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.commands.JoystickDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSubsystem extends Subsystem {

	private AHRS navx;
	private TalonSRX left, right, followerLeft, followerRight;
	private ControlMode controlMode;
	private Solenoid shiftSolenoid;// , rightSolenoid;

	public DriveSubsystem() {
		navx = new AHRS(Port.kMXP);

		left = new TalonSRX(Constants.DRIVE_LEFT_TALON_CAN);
		right = new TalonSRX(Constants.DRIVE_RIGHT_TALON_CAN);
		followerLeft = new TalonSRX(Constants.DRIVE_LEFT_FOLLOWER_CAN);
		followerRight = new TalonSRX(Constants.DRIVE_RIGHT_FOLLOWER_CAN);
		shiftSolenoid = new Solenoid(Constants.DRIVE_SHIFT_SOLENOID);

		left.setInverted(false);
		followerLeft.setInverted(false);
		right.setInverted(true);
		followerRight.setInverted(true);

		// left.setNeutralMode(NeutralMode.Brake);
		// followerLeft.setNeutralMode(NeutralMode.Brake);
		// right.setNeutralMode(NeutralMode.Brake);
		// followerRight.setNeutralMode(NeutralMode.Brake);

		// Results of experiment:
		// Left: 12489 Ticks per Meter
		// Right: 12270 Ticks per Meter
		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 100);
		// followerLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,
		// 0, 100);
		right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 100);
		// followerRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,
		// 0, 100);

		controlMode = ControlMode.PercentOutput;

		left.set(controlMode, 0);
		followerLeft.set(controlMode, left.getDeviceID());
		right.set(controlMode, 0);
		followerRight.set(controlMode, right.getDeviceID());
	}

	public void setControlMode(ControlMode newMode) {
		controlMode = newMode;
	}

	public void drive(double left, double right) {
		this.left.set(controlMode, left);
		this.right.set(controlMode, right);
		this.followerLeft.set(controlMode, left);
		this.followerRight.set(controlMode, right);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveCommand());
	}

	public void shiftGear() {
		shiftSolenoid.set(!shiftSolenoid.get());
	}

	public void highGear() {
		shiftSolenoid.set(false);
	}

	public void lowGear() {
		shiftSolenoid.set(true);
	}

	public void resetEncoders() {
		left.setSelectedSensorPosition(0, 0, 100);
		right.setSelectedSensorPosition(0, 0, 100);
	}

	public int getLeftRaw() {
		return left.getSelectedSensorPosition(0);
	}

	public double getLeftPosition() {
		return left.getSelectedSensorPosition(0) * Constants.LEFT_ENCODER_TICKS_PER_METER;
	}

	public double getLeftVelocity() {
		return left.getSelectedSensorVelocity(0) * Constants.LEFT_ENCODER_TICKS_PER_METER;
	}

	public int getRightRaw() {
		return right.getSelectedSensorPosition(0);
	}

	public int getRawRight() {
		return right.getSelectedSensorPosition(0);
	}

	public double getRightPosition() {
		return right.getSelectedSensorPosition(0) * Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	public double getRightVelocity() {
		return right.getSelectedSensorVelocity(0) * Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	public double getHeading() {
		return navx.getAngle();
	}

	public void resetHeading() {
		navx.zeroYaw();
	}

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
