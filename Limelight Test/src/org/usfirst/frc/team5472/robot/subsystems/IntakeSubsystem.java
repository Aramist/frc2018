package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem{
	
	private PowerDistributionPanel pdp;
	private VictorSP leftSide;
	private VictorSP rightSide;
	
	private DoubleSolenoid constrictor;
	
	public IntakeSubsystem() {
		leftSide = new VictorSP(Constants.INTAKE_LEFT_MOTOR_PWM);
		rightSide = new VictorSP(Constants.INTAKE_RIGHT_MOTOR_PWM);
		constrictor = new DoubleSolenoid(Constants.INTAKE_SOLENOID_ID_FORW, Constants.INTAKE_SOLENOID_ID_BACK);
		pdp = Robot.pdp;
		
		leftSide.setInverted(true);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
	
	public void start() {
		leftSide.set(Constants.INTAKE_INPUT_SPEED);
		rightSide.set(Constants.INTAKE_INPUT_SPEED);
	}
	
	public void reverse() {
		leftSide.set(-Constants.INTAKE_OUTPUT_SPEED);
		rightSide.set(-Constants.INTAKE_OUTPUT_SPEED);
	}
	
	public void stop() {
		leftSide.set(0.0);
		rightSide.set(0.0);
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
		return Math.abs(leftSide.get());
	}
	
	public double getLeftMotorCurrent() {
		return pdp.getCurrent(Constants.INTAKE_LEFT_MOTOR_PDP);
	}
	
	public double getRightMotorCurrent() {
		return pdp.getCurrent(Constants.INTAKE_RIGHT_MOTOR_PDP);
	}
}
