package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.commands.JoystickDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSubsystem extends Subsystem{
	
<<<<<<< HEAD
//	private AHRS navx;
	private TalonSRX left, right; //, followerLeft, followerRight;
=======
	private AHRS navx;
	private TalonSRX left, right;
>>>>>>> branch 'devel' of https://github.com/Aramist/frc2018.git
	private ControlMode leftControlMode, rightControlMode;
//	private Solenoid leftSolenoid, rightSolenoid;
	
	public DriveSubsystem() {
		navx = new AHRS(Port.kMXP);
		
		left = new TalonSRX(Constants.DRIVE_LEFT_TALON_CAN);
		right = new TalonSRX(Constants.DRIVE_RIGHT_TALON_CAN);
//		followerLeft = new TalonSRX(Constants.DRIVE_LEFT_FOLLOWER_CAN);
//		followerRight = new TalonSRX(Constants.DRIVE_RIGHT_FOLLOWER_CAN);
//		leftSolenoid = new Solenoid(RobotMap.leftSolenoid);
//		rightSolenoid = new Solenoid(RobotMap.rightSolenoid);
		
		left.setInverted(true);
//		followerLeft.setInverted(true);
		right.setInverted(false);
//		followerRight.setInverted(false);
		
		left.setNeutralMode(NeutralMode.Brake);
//		followerLeft.setNeutralMode(NeutralMode.Brake);
		right.setNeutralMode(NeutralMode.Brake);
//		followerRight.setNeutralMode(NeutralMode.Brake);
		
		
		//Results of experiment:
		//Left: 12489 Ticks per Meter
		//Right: 12270 Ticks per Meter
		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 100);
//		followerLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 100);
		right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 100);
//		followerRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 100);
		
		leftControlMode = ControlMode.PercentOutput;
		rightControlMode = ControlMode.PercentOutput;
		
		left.set(leftControlMode, 0);
//		followerLeft.set(ControlMode.Follower, left.getDeviceID());
		right.set(rightControlMode, 0);
//		followerRight.set(ControlMode.Follower, right.getDeviceID());
	}
	
	public void setControlMode(ControlMode newMode) {
		leftControlMode = rightControlMode = newMode;
	}
	
	public void drive(double left, double right) {
		this.left.set(leftControlMode, left);
		this.right.set(rightControlMode, right);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveCommand());
	}
	
//	public void shiftGear()
//	{
//		leftSolenoid.set(!leftSolenoid.get());
//		rightSolenoid.set(!rightSolenoid.get());
//	}
	
	public void resetEncoders() {
		left.setSelectedSensorPosition(0, 0, 100);
		right.setSelectedSensorPosition(0, 0, 100);
	}
	
<<<<<<< HEAD
	public int getLeftRaw() {
=======
	public int getRawLeft() {
>>>>>>> branch 'devel' of https://github.com/Aramist/frc2018.git
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
	
	public void reportDebugInformation() {
		/**
		 * Reports the following information:
		 * - Voltage......(For each motor controller)
		 * - Current......(For each motor controller)
		 * - Temperature..(For each motor controller)
		 * - Velocity.....(Left side and Right side)
		 * - Encoder Readings
		 * - Bearing......(Relative to starting position of robot, not North)
		 * - Heading......(Relative to vision target, if any)
		 */

//		SmartDashboard.putNumber("Left Voltage", left.getBusVoltage());
//		SmartDashboard.putNumber("Left Current", left.getOutputCurrent());
//		SmartDashboard.putNumber("Left Temperature", left.getTemperature());
//		SmartDashboard.putNumber("Left Output", left.getMotorOutputPercent());
		SmartDashboard.putNumber("Left Encoder", getLeftPosition());
		SmartDashboard.putNumber("Left Encoder Velocity", getLeftVelocity());
//		
//		
//		SmartDashboard.putNumber("Right Current", right.getOutputCurrent());
//		SmartDashboard.putNumber("Right Voltage", right.getBusVoltage());
//		SmartDashboard.putNumber("Right Temperature", right.getTemperature());
//		SmartDashboard.putNumber("Right Output", right.getMotorOutputPercent());
		SmartDashboard.putNumber("Right Encoder", getRightPosition());
		SmartDashboard.putNumber("Right Encoder Velocity", getRightVelocity());
//		SmartDashboard.putNumber("Bearing", navx.getAngle());
	}
}
