package org.usfirst.frc5472.TrackerTest.commands;

import org.usfirst.frc5472.TrackerTest.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftGearCommandEnable extends Command {
	private boolean completed = false;

	public ShiftGearCommandEnable() {
		requires(Robot.getInstance().getDriveSubsystem());
	}

	@Override
	public void execute() {
		Robot.getInstance().getDriveSubsystem().shiftGearEnable();

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return completed;
	}
}
