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

	/**
	 * Instantiates the Drive Subsystem and initalizes all motor controllers.
	 */
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

	/**
	 * Sets the control mode used for the TalonSRX speed controllers.
	 *
	 * @param newMode the new control mode
	 */
	public void setControlMode(ControlMode newMode) {
		controlMode = newMode;
	}

	/**
	 * Differential drive.
	 *
	 * @param left Percent output given to the left side
	 * @param right Percent output given to the right side
	 */
	public void drive(double left, double right) {
		this.left.set(controlMode, left);
		this.right.set(controlMode, right);
		this.leftFollower.set(controlMode, left);
		this.rightFollower.set(controlMode, right);
	}
	
	/**
	 * A shortcut method for differential drive that adds a 'turn' parameter.
	 *
	 * @param throttle the percent output given to both motors
	 * @param twist the amount added to the left side and subtracted from the right.
	 */
	public void turn(double throttle, double twist) {
		left.set(controlMode, throttle + twist);
		leftFollower.set(controlMode, throttle - twist); //TODO: This looks wierd, check it out on Monday
		right.set(controlMode, throttle - twist);
		rightFollower.set(controlMode, throttle + twist);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDriveCommand());
	}

	/**
	 * Checks if high gear is activated.
	 *
	 * @return true, if the robot is currently in high gear
	 */
	public boolean isHighGear() {
		return shiftSolenoid.get();
	}
	
	/**
	 * Shifts the gear of the drivetrain
	 */
	public void shiftGear() {
		shiftSolenoid.set(!shiftSolenoid.get());
	}

	/**
	 * Sets the drivetrain to high gear
	 */
	public void highGear() {
		shiftSolenoid.set(true);
	}

	/**
	 * Sets the drivetrain to low gear
	 */
	public void lowGear() {
		shiftSolenoid.set(false);
	}

	/**
	 * Reset the encoders' positions to zero.
	 */
	public void resetEncoders() {
		left.setSelectedSensorPosition(0, 0, 0);
		right.setSelectedSensorPosition(0, 0, 0);
	}

	/**
	 * Gets the raw output of the left wheels' encoder. 4096 ticks are equivalent to one revolution.
	 *
	 * @return raw output of left encoder
	 */
	public int getLeftRaw() {
		return left.getSelectedSensorPosition(0);
	}

	/**
	 * Gets the distance traveled by the left side of the drivetrain in meters.
	 *
	 * @return the left position in meters
	 */
	public double getLeftPosition() {
		return left.getSelectedSensorPosition(0) / Constants.LEFT_ENCODER_TICKS_PER_METER;
	}

	/**
	 * Gets the velocity of the left side of the drivetrain in meters per second.
	 *
	 * @return left side velocity in m/s
	 */
	public double getLeftVelocity() {
		return 10 * left.getSelectedSensorVelocity(0) / Constants.LEFT_ENCODER_TICKS_PER_METER;
	}
	
	/**
	 * Gets the signal currently being sent to the left side's motors, in percent.
	 *
	 * @return the output being sent to the left side's motors
	 */
	public double getLeftPercent() {
		return left.getMotorOutputPercent();
	}

	/**
	 * Gets the raw output of the right wheels' encoder. 4096 ticks are equivalent to one revolution.
	 *
	 * @return raw output of right encoder
	 */
	public int getRightRaw() {
		return right.getSelectedSensorPosition(0);
	}

	/**
	 * Gets the distance traveled by the right side of the drivetrain in meters.
	 *
	 * @return the right position in meters
	 */
	public double getRightPosition() {
		return right.getSelectedSensorPosition(0) / Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	/**
	 * Gets the velocity of the right side of the drivetrain in meters per second.
	 *
	 * @return right side velocity in m/s
	 */
	public double getRightVelocity() {
		return 10 * right.getSelectedSensorVelocity(0) / Constants.RIGHT_ENCODER_TICKS_PER_METER;
	}

	/**
	 * Gets the signal currently being sent to the right side's motors, in percent.
	 *
	 * @return the output being sent to the right side's motors
	 */
	public double getRightPercent() {
		return right.getMotorOutputPercent();
	}
	
	/**
	 * Gets the current heading of the robot from the navx-mxp
	 *
	 * @return the heading, in degrees
	 */
	public double getHeading() {
		SmartDashboard.putData(navx);
		return -navx.getAngle();
	}

	/**
	 * Resets robot heading to zero.
	 */
	public void resetHeading() {
		navx.zeroYaw();
	}
	
	/**
	 * Sets the motor controllers' neutral mode to brake
	 */
	public void setBrake() {
		left.setNeutralMode(NeutralMode.Brake);
		leftFollower.setNeutralMode(NeutralMode.Brake);
		right.setNeutralMode(NeutralMode.Brake);
		rightFollower.setNeutralMode(NeutralMode.Brake);
	}
	
	/**
	 * Sets the motor controllers' neutral mode to coast
	 */
	public void setCoast() {
		left.setNeutralMode(NeutralMode.Coast);
		leftFollower.setNeutralMode(NeutralMode.Coast);
		right.setNeutralMode(NeutralMode.Coast);
		rightFollower.setNeutralMode(NeutralMode.Coast);
	}
	
	/**
	 * Truncates a value such that its magnitude is less than or equal to some threshold.
	 *
	 * @param value the value to be truncated
	 * @param limit the threshold
	 * @return the result of the truncation
	 */
	// Autonomous Stuff
	public static double truncate(double value, double limit) {
		limit = Math.abs(limit);
		return value > limit ? limit : value < -limit ? -limit : value;
	}
	
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team5472.robot.DataProvider#getData()
	 */
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
