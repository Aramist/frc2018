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
		stick = Robot.controls.getJoystick();
	}

	@Override
	public void initialize() {
		lift = Robot.liftSubsystem;
	}

	@Override
	public void execute() {
//		lift.setLiftPercent(stick.getRawAxis(5) * 0.6);
//		double x = Math.max(0, (stick.getRawAxis(2) - 0.05) / 1.25);
		int x = (int)(stick.getRawAxis(2) * 10);
		int y = (int)(stick.getRawAxis(3) * 5);
		lift.setPosition(lift.getPosition() + x - y);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}