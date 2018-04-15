package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.commands.TeleopDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Most of this stuff is just copied from the main project.
 */
public class Drive extends Subsystem{
	
	private AHRS navx = new AHRS(SPI.Port.kMXP);
	private TalonSRX left, right, leftF, rightF;
	private final ControlMode controlMode = ControlMode.PercentOutput;

	public Drive() {

		left = new TalonSRX(Constants.LEFT_TALON);
		right = new TalonSRX(Constants.RIGHT_TALON);
		leftF = new TalonSRX(Constants.LEFT_FOLLOWER);
		rightF = new TalonSRX(Constants.RIGHT_FOLLOWER);

		left.setInverted(false);
		leftF.setInverted(false);
		right.setInverted(true);
		rightF.setInverted(true);
		
		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		left.setSensorPhase(true);
		right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		right.setSensorPhase(true);
		
		left.setNeutralMode(NeutralMode.Coast);
		leftF.setNeutralMode(NeutralMode.Coast);
		right.setNeutralMode(NeutralMode.Coast);
		rightF.setNeutralMode(NeutralMode.Coast);

		left.set(controlMode, 0);
		leftF.follow(left);
		right.set(controlMode, 0);
		rightF.follow(right);

	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleopDrive());
	}
	

	public void drive(double left, double right) {
		this.left.set(controlMode, left);
		this.right.set(controlMode, right);
		this.leftF.set(controlMode, left);
		this.rightF.set(controlMode, right);
	}
	
	public void resetEncoders() {
		left.setSelectedSensorPosition(0, 0, 0);
		right.setSelectedSensorPosition(0, 0, 0);
	}
	
	

	public int getLeftRaw() {
		return left.getSelectedSensorPosition(0);
	}

	public double getLeftPosition() {
		return left.getSelectedSensorPosition(0) / Constants.ENCODER_TICKS_PER_METER;
	}

	public double getLeftVelocity() {
		return 10 * left.getSelectedSensorVelocity(0) / Constants.ENCODER_TICKS_PER_METER;
	}
	
	public double getLeftPercent() {
		return left.getMotorOutputPercent();
	}
	
	
	

	public int getRightRaw() {
		return right.getSelectedSensorPosition(0);
	}

	public double getRightPosition() {
		return right.getSelectedSensorPosition(0) / Constants.ENCODER_TICKS_PER_METER;
	}

	public double getRightVelocity() {
		return 10 * right.getSelectedSensorVelocity(0) / Constants.ENCODER_TICKS_PER_METER;
	}

	public double getRightPercent() {
		return right.getMotorOutputPercent();
	}
	
	
	
	
	public double getHeading() {
		return -navx.getAngle();
	}

	public void resetHeading() {
		navx.zeroYaw();
	}
}
