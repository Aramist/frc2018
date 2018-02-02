package org.usfirst.frc5472.TrackerTest.commands;

import org.usfirst.frc5472.TrackerTest.Robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class LiftCommand extends Command {
	private boolean completed = false;
	private JoystickButton liftButton;
	private JoystickButton lowerButton;

	public LiftCommand() {
		requires(Robot.getInstance().getLiftSubsystem());
		liftButton = Robot.getInstance().getOI().getLiftButton();
		lowerButton = Robot.getInstance().getOI().getLowerButton();
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		if (liftButton.get())
			Robot.getInstance().getLiftSubsystem().climb(0.5);
		else if (lowerButton.get())
			Robot.getInstance().getLiftSubsystem().climb(-0.5);
		else
			Robot.getInstance().getLiftSubsystem().climb(0);
	}

	@Override
	public boolean isFinished() {
		return completed;
	}
}
