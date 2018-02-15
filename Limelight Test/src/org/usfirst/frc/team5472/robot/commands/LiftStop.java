package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Controls;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class LiftStop extends Command{
	
	private LiftSubsystem lift;
	private Controls controls = Robot.controls;
	private boolean finished;
	
	@Override
	public void initialize() {
		lift = Robot.liftSubsystem;
		
	}
	
	@Override
	public void execute() {
		if(Math.max(0, (controls.getLiftUpAxis() - 0.05) / 1.25) != 0.0)
			lift.stopLift();
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
}
