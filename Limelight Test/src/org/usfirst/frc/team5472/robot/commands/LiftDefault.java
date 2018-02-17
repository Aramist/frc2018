package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Controls;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class LiftDefault extends Command {

	private LiftSubsystem lift;
	private Controls controls = Robot.controls;
	
	public LiftDefault() {
		requires(Robot.lift);
	}

	@Override
	public void initialize() {
		lift = Robot.lift;
	}

	@Override
	public void execute() {
		if(DriverStation.getInstance().isAutonomous())
			return;
//		lift.setLiftPercent(stick.getRawAxis(5) * 0.6);
		double x = Math.max(0, (controls.getLiftUpAxis()) / 1.25);
		double y = Math.max(-0.5, (-controls.getLiftDownAxis()) / 1.00);
		if(x+y != 0)
			lift.setPercent(x + y);
		if(lift.getPosition() < 8000)
			lift.enableBrake();
//		int x = (int)(stick.getRawAxis(2) * 10);
//		int y = (int)(stick.getRawAxis(3) * 5);
//		lift.setPosition(lift.getPosition() + x - y);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}