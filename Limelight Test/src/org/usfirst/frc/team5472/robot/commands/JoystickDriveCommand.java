package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickDriveCommand extends Command{
	
	private boolean completed = false;
	private Joystick instance;
	
	public JoystickDriveCommand() {
		requires(Robot.driveSubsystem);
	}
	
	@Override
	public void initialize() {
		this.instance = Robot.controls.getJoystick();
	}
	
	@Override
	public void execute() {
		double y = -instance.getY();
		double x = instance.getTwist() / 4;
		
		double left = y + x;
		double right = y - x;
		Robot.driveSubsystem.drive(left, right);
	}
	
	public boolean isFinished() {
		return this.completed;
	}

}
