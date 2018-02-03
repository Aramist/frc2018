package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LiftDefault extends Command{

	private LiftSubsystem lift;
	private Joystick stick;
	
	public LiftDefault() {
		requires(Robot.liftSubsystem);
	}
	
	@Override
	public void initialize() {
		lift = Robot.liftSubsystem;
		stick = Robot.controls.getJoystick();
	}
	
	@Override
	public void execute() {
		if(stick.getPOV() == 0)
			lift.setLiftPercent(0.75);
		else if(stick.getPOV() == 180)
			lift.setLiftPercent(-0.75);
		else
			lift.stopLift();
	}
	
	protected boolean isFinished() {
		return false;
	}
}
