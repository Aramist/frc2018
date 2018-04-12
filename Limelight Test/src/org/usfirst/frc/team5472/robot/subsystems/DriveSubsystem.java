package org.usfirst.frc.team5472.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.DataProvider;
import org.usfirst.frc.team5472.robot.commands.JoystickDriveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSubsystem extends Subsystem implements DataProvider{

	private AHRS navx = new AHRS(SPI.Port.kMXP);
	private TalonSRX left, right, leftFollower, rightFollower;
	private ControlMode controlMode;
	private Solenoid shiftSolenoid;

	public DriveSubsystem() {

		left = new TalonSRX(Constants.DRIVE_LEFT_TALON_CAN);
		right = new TalonSRX(Constants.DRIVE_RIGHT_TALON_CAN);
		leftFollower = new TalonSRX(Constants.DRIVE_LEFT_FOLLOWER_CAN);
		rightFollower = new TalonSRX(Constants.DRIVE_RIGHT_FOLLOWER_CAN);
		
		shiftSolenoid = new Solenoid(Constants.DRIVE_SHIFT_SOLENOID);

		left.setInverted(false);
		leftFollower.setInverted(false);
		right.setInverted(true);
		rightFollower.setInverted(true);
		
		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		left.setSensorPhase(true);
		right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		right.setSensorPhase(true);

		controlMode = ControlMode.PercentOutput;
		
		left.setNeutralMode(NeutralMode.Coast);
		leftFollower.setNeutralMode(NeutralMode.Coast);
		right.setNeutralMode(NeutralMode.Coast);
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

	public boolean isHighGear() {
		return shiftSolenoid.get();
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
		left.setSelectedSensorPosition(0, 0, 0);
		right.setSelectedSensorPosition(0, 0, 0);
	}

	public int getLeftRaw() {
		return left.getSelectedSensorPosition(0);
	}

	public double getLeftPosition() {
		return left.getSelectedSensorPosition(0) / Constants.LEFT_ENCODER_TICKS_PER_METER;
	}

	public double getLeftVelocity() {
		return left.getSelectedSensorVelocity(0) / Constants.LEFT_ENCODER_TICKS_PER_METER;
	}
	
	public double getLeftPercent() {
		return left.getMotorOutputPercent();
	}

	public int getRightRaw() {
		return right.getSelectedSensorPosition(0);
	}

	public double getRightPosition() {
		return right.getSelectedSensorPosition(0) / Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	public double getRightVelocity() {
		return right.getSelectedSensorVelocity(0) / Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	public double getRightPercent() {
		return right.getMotorOutputPercent();
	}
	
	public double getHeading() {
		SmartDashboard.putData(navx);
		return -navx.getAngle();
	}

	public void resetHeading() {
		navx.zeroYaw();
	}
	
	public void setBrake() {
		left.setNeutralMode(NeutralMode.Brake);
		leftFollower.setNeutralMode(NeutralMode.Brake);
		right.setNeutralMode(NeutralMode.Brake);
		rightFollower.setNeutralMode(NeutralMode.Brake);
	}
	
	public void setCoast() {
		left.setNeutralMode(NeutralMode.Coast);
		leftFollower.setNeutralMode(NeutralMode.Coast);
		right.setNeutralMode(NeutralMode.Coast);
		rightFollower.setNeutralMode(NeutralMode.Coast);
	}
	
	// Autonomous Stuff
	public static double truncate(double value, double limit) {
		limit = Math.abs(limit);
		return value > limit ? limit : value < -limit ? -limit : value;
	}
	
	
	public HashMap<String, double[]> getData(){
		HashMap<String, double[]> toReturn = new HashMap<>();
		toReturn.put("Drive Motor Output Percent", new double[] {
				leftFollower.getMotorOutputPercent(), rightFollower.getMotorOutputPercent(),
				left.getMotorOutputPercent(), right.getMotorOutputPercent()
		});
		toReturn.put("Drive Motor Output Current", new double[] {
				leftFollower.getOutputCurrent(), rightFollower.getOutputCurrent(),
				left.getOutputCurrent(), right.getOutputCurrent()
		});
		toReturn.put("Drive Motor Encoder Position", new double[] {
				getLeftPosition(), getRightPosition()
		});
		toReturn.put("Drive Motor Encoder Velocity", new double[] {
				getLeftVelocity(), getRightVelocity()
		});
		toReturn.put("Robot Heading", new double[] {
				getHeading()
		});
		return toReturn;
	}
}
