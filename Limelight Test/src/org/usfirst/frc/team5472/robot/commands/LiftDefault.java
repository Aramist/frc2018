package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Controls;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class LiftDefault extends Command {

	private LiftSubsystem lift;
	private Controls controls = Robot.controls;
	
	public LiftDefault() {
		requires(Robot.liftSubsystem);
	}

	@Override
	public void initialize() {
		lift = Robot.liftSubsystem;
	}

	@Override
	public void execute() {
//		lift.setLiftPercent(stick.getRawAxis(5) * 0.6);
		double x = Math.max(0, (controls.getLiftUpAxis() - 0.05) / 1.25);
		double y = Math.max(-0.4, (controls.getLiftDownAxis() - 0.05) / -2.0);
		lift.setLiftPercent(x + y);
//		int x = (int)(stick.getRawAxis(2) * 10);
//		int y = (int)(stick.getRawAxis(3) * 5);
//		lift.setPosition(lift.getPosition() + x - y);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}