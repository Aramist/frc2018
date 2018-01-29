package org.usfirst.frc5472.TrackerTest.commands;

import org.usfirst.frc5472.TrackerTest.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class FeedCommand extends Command {
	private XboxController joystick;
	private boolean completed = false;

	public FeedCommand() {
		requires(Robot.getInstance().getIntakeSubsystem());
	}

	@Override
	public void initialize() {
		joystick = Robot.oi.getXbox();
	}

	@Override
	public void execute() {
		if (joystick.getBumper(Hand.kLeft)) {
			Robot.getInstance().getIntakeSubsystem().feedIn();
		} else if (joystick.getBumper(Hand.kRight)) {
			Robot.getInstance().getIntakeSubsystem().feedOut();
		} else {
			Robot.getInstance().getIntakeSubsystem().stop();
		}
	}

	@Override
	public boolean isFinished() {
		return completed;
	}

	@Override
	public void end() {
		Robot.getInstance().getIntakeSubsystem().stop();
	}

	@Override
	public void interrupted() {

	}

}
