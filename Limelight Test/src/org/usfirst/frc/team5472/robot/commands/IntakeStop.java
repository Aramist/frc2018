package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeStop extends Command{
	
	private IntakeSubsystem intake;
	private boolean finished;
	
	@Override
	public void initialize() {
		intake = Robot.intakeSubsystem;
		finished = false;
	}
	
	@Override
	public void execute() {
		intake.stop();
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}