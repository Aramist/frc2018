package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class RaiseLiftLow extends Command{
	
	private boolean finished = false;
	private LiftSubsystem lift;

	@Override
	public void initialize() {
		lift = Robot.liftSubsystem;
	}
	
	@Override
	public void execute() {
		lift.setPosition(5000.0);
		finished = true;
	}
	
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
