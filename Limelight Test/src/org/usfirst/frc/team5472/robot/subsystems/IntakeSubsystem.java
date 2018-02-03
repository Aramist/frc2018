package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem{
	
	private TalonSRX leftSide;
	private TalonSRX rightSide;
	
	private static final ControlMode MODE = ControlMode.PercentOutput;
	
	private DoubleSolenoid constrictor;
	
	public IntakeSubsystem() {
		leftSide = new TalonSRX(Constants.INTAKE_LEFT_MOTOR_CAN);
		rightSide = new TalonSRX(Constants.INTAKE_RIGHT_MOTOR_CAN);
		constrictor = new DoubleSolenoid(Constants.INTAKE_SOLENOID_ID_FORW, Constants.INTAKE_SOLENOID_ID_BACK);
		
		leftSide.setInverted(true);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void start() {
		leftSide.set(MODE, Constants.INTAKE_INPUT_SPEED);
		rightSide.set(MODE, Constants.INTAKE_INPUT_SPEED);
	}
	
	public void reverse() {
		leftSide.set(MODE, -Constants.INTAKE_OUTPUT_SPEED);
		rightSide.set(MODE, -Constants.INTAKE_OUTPUT_SPEED);
	}
	
	public void stop() {
		leftSide.set(MODE, 0.0);
		rightSide.set(MODE, 0.0);
	}
	
	public void open() {
		constrictor.set(Value.kReverse);
	}
	
	public void close() {
		constrictor.set(Value.kForward);
	}
	
	public boolean gripIsOpen() {
		return getSolenoidValue().equals(Value.kReverse);
	}
	
	public Value getSolenoidValue() {
		return constrictor.get();
	}
	
	public double getMotorSpeed() {
		return Math.abs(leftSide.getMotorOutputPercent());
	}
	
}
