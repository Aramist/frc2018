package org.usfirst.frc.team5472.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.DataProvider;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem implements DataProvider{
	
	private TalonSRX leftSide;
	private TalonSRX rightSide;
	
	private static final ControlMode MODE = ControlMode.PercentOutput;
	
	private DoubleSolenoid constrictor;
	
	/**
	 * Instantiates the Intake Subsystem and initializes motor controllers.
	 */
	public IntakeSubsystem() {
		leftSide = new TalonSRX(Constants.INTAKE_LEFT_MOTOR_CAN);
		rightSide = new TalonSRX(Constants.INTAKE_RIGHT_MOTOR_CAN);
		constrictor = new DoubleSolenoid(Constants.INTAKE_SOLENOID_ID_FORW, Constants.INTAKE_SOLENOID_ID_BACK);
		
		rightSide.setInverted(true);
		leftSide.setInverted(false);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	/**
	 * Activates the intake in the 'in' direction.
	 *
	 * @param slow If true, the intake will use the slow speed given in Constants.
	 */
	public void start(boolean slow) {
		double speed = slow ? Constants.INTAKE_INPUT_SLOW_SPEED : Constants.INTAKE_INPUT_SPEED;
		leftSide.set(MODE, speed);
		rightSide.set(MODE, speed);
	}
	
	/**
	 * Activates the intake in the 'out' direction
	 *
	 * @param slow If true, the intake will use the slow speed given in Constants.
	 */
	public void reverse(boolean slow) {
		double speed = slow ? Constants.INTAKE_OUTPUT_SLOW_SPEED : Constants.INTAKE_OUTPUT_SPEED;
		leftSide.set(MODE, -speed);
		rightSide.set(MODE, -speed);
	}
	
	/**
	 * Activates the intake in the 'in' direction at a speed designated for autonomous use.
	 */
	public void startAuto() {
		double speed = 0.50;
		leftSide.set(MODE, speed);
		rightSide.set(MODE, speed);
	}
	
	/**
	 * Activates the intake in the 'out' direction at a speed designated for autonomous use.
	 */
	public void reverseAuto() {
		double speed = 0.60;
		leftSide.set(MODE, -speed);
		rightSide.set(MODE, -speed);
	}
	
	/**
	 * Stops the intake motors.
	 */
	public void stop() {
		leftSide.set(MODE, 0.0);
		rightSide.set(MODE, 0.0);
	}
	
	/**
	 * Opens the grabber.
	 */
	public void open() {
		constrictor.set(Value.kForward);
	}
	
	/**
	 * Closes the grabber.
	 */
	public void close() {
		constrictor.set(Value.kReverse);
	}
	
	/**
	 * Returns the current state of the grabber.
	 *
	 * @return true, if the grabber is open.
	 */
	public boolean gripIsOpen() {
		return getSolenoidValue().equals(Value.kForward);
	}
	
	/**
	 * Gets the raw state of the solenoid controlling the grabber.
	 *
	 * @return The Value assigned to the solenoid controlling the grabber.
	 */
	public Value getSolenoidValue() {
		return constrictor.get();
	}
	
	/**
	 * Gets the current output of the intake motors, in percent.
	 *
	 * @return the motor output, in percent.
	 */
	public double getMotorSpeed() {
		return Math.abs(leftSide.getMotorOutputPercent());
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team5472.robot.DataProvider#getData()
	 */
	public HashMap<String, double[]> getData(){
		HashMap<String, double[]> toReturn = new HashMap<>();
		toReturn.put("Intake Output Percent", new double[] {
				leftSide.getMotorOutputPercent(), rightSide.getMotorOutputPercent()
		});
		toReturn.put("Intake Output Current", new double[] {
				leftSide.getOutputCurrent(), rightSide.getOutputCurrent()
		});
		toReturn.put("Intake Solenoid State", new double[] {
				gripIsOpen() ? 1 : 0
		});
		return toReturn;
	}
}
