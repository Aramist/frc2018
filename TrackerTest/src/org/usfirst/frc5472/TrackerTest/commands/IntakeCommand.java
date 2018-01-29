package org.usfirst.frc5472.TrackerTest.commands;

import org.usfirst.frc5472.TrackerTest.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCommand extends Command {
	private boolean completed = false;

	public IntakeCommand() {
		requires(Robot.getInstance().getIntakeSubsystem());
	}

	@Override
	public void initialize() {

	}

	@Override
	public void execute() {
		Robot.getInstance().getIntakeSubsystem().changeState();
		completed = true;
	}

	@Override
	public boolean isFinished() {
		return completed;
	}

	@Override
	public void interrupted() {

	}

	@Override
	public void end() {

	}
}
