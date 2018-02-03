package org.usfirst.frc5472.TrackerTest.commands;

import org.usfirst.frc5472.TrackerTest.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class LiftCommand extends Command {
	private boolean completed = false;
	private JoystickButton liftButton;
	private JoystickButton lowerButton;

	public LiftCommand() {
		requires(Robot.getInstance().getLiftSubsystem());
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		Robot.getInstance().getLiftSubsystem().climb(Robot.oi.getXbox().getTriggerAxis(Hand.kLeft) * 0.75);
	}

	@Override
	public boolean isFinished() {
		return completed;
	}
}