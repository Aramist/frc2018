package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class LiftZero extends Command{
	
	private LiftSubsystem lift;
	private boolean finished;
	
	public LiftZero() {
		lift = Robot.lift;
	}
	
	@Override
	public void execute() {
		lift.setSetpoint(0);
		finished = lift.onTarget();
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
