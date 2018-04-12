package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReportIntakeLimit extends Command{
	
	private boolean finished;
	
	@Override
	public void initialize() {
		finished = false;
	}
	
	@Override
	public void execute() {
		SmartDashboard.putBoolean("Intake Limit Switch", Robot.controls.intakeLimit.get());
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
