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
		
		double x = controls.getLiftUpAxis() * 50;
		double y = controls.getLiftDownAxis() * -30;
		
		lift.setSetpoint(lift.getSetpoint() + x + y);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}