package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.commands.JoystickDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSubsystem extends Subsystem{
	
//	private AHRS navx;
	private TalonSRX left, right;
	private ControlMode leftControlMode, rightControlMode;
	private double experimentalLeftScalar = 1.0 / 12489.0;
	private double experimentalRightScalar = 1.0 / 12270.0;
	
	public DriveSubsystem() {
//		navx = new AHRS(Port.kMXP);
		
		left = new TalonSRX(3);
		right = new TalonSRX(4);
		
		left.setInverted(true);
		right.setInverted(false);
		
		left.setNeutralMode(NeutralMode.Brake);
		right.setNeutralMode(NeutralMode.Brake);
		
		
		//Results of experiment:
		//Left: 12489 Ticks per Meter
		//Right: 12270 Ticks per Meter
		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 100);
		right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 100);
		
		leftControlMode = ControlMode.PercentOutput;
		rightControlMode = ControlMode.PercentOutput;
		
		left.set(leftControlMode, 0);
		right.set(rightControlMode, 0);
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
	
	public void resetEncoders() {
		left.setSelectedSensorPosition(0, 0, 100);
		right.setSelectedSensorPosition(0, 0, 100);
	}
	
	public double getLeftPosition() {
		return left.getSelectedSensorPosition(0) * experimentalLeftScalar;
	}
	
	public double getLeftVelocity() {
		return left.getSelectedSensorVelocity(0) * experimentalLeftScalar;
	}
	
	public double getRightPosition() {
		return right.getSelectedSensorPosition(0) * experimentalRightScalar;
	}
	
	public double getRightVelocity() {
		return right.getSelectedSensorVelocity(0) * experimentalRightScalar;
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
