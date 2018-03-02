package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Constants;
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
		double x = controls.getLiftUpAxis();
		double y = controls.getLiftDownAxis() * Constants.LIFT_REVERSE_OUTPUT_LIMIT;
		
				
		lift.setPercent(x + y);
		if(lift.getPosition() < 8000)
			lift.enableBrake();
	}
		
	

	@Override
	protected boolean isFinished() {
		return false;
	}
}