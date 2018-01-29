package org.usfirst.frc5472.TrackerTest.commands;

import org.usfirst.frc5472.TrackerTest.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestCommand extends Command {
	private boolean completed = false;

	public TestCommand() {
		requires(Robot.getInstance().getDriveSubsystem());
	}

	public void inititalize() {

	}

	@Override
	public void execute() {
		SmartDashboard.putNumber("Number", Math.random());
		completed = true;
	}

	@Override
	public boolean isFinished() {
		return completed;
	}

	@Override
	public void end() {

	}

	@Override
	public void interrupted() {

	}
}
