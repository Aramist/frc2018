package org.usfirst.frc5472.TrackerTest.commands;

import org.usfirst.frc5472.TrackerTest.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftGearCommand extends Command {
	private boolean completed = false;

	public ShiftGearCommand() {
		requires(Robot.getInstance().getDriveSubsystem());
	}

	@Override
	public void execute() {
		Robot.getInstance().getDriveSubsystem().shiftGear();
		completed = true;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return completed;
	}
}
