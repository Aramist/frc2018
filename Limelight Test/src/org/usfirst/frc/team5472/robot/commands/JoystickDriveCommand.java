package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Controls;
import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickDriveCommand extends Command{
	
	private boolean completed = false;
	private Controls controls = Robot.controls;
	
	public JoystickDriveCommand() {
		requires(Robot.driveSubsystem);
	}
	
	@Override
	public void initialize() {
	}
	
	@Override
	public void execute() {
		double y = -controls.getDriveVerticalAxis();
		double x = controls.getDriveHorizontalAxis() / 2;
		
		Robot.driveSubsystem.drive(y + x, y - x);
	}
	
	public boolean isFinished() {
		return this.completed;
	}

}
