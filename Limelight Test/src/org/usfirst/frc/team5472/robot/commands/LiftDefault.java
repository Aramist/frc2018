package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LiftDefault extends Command {

	private LiftSubsystem lift;
	private Joystick stick;

	public LiftDefault() {
		requires(Robot.liftSubsystem);
	}

	@Override
	public void initialize() {
		lift = Robot.liftSubsystem;
		stick = Robot.controls.getXBOX();
	}

	@Override
	public void execute() {
		lift.setLiftPercent(stick.getRawAxis(5) * 0.6);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
