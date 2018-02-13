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
		double y = -instance.getRawAxis(1);
		double x = instance.getRawAxis(0) / 2;
		
		Robot.driveSubsystem.drive(y + x, y - x);
	}
	
	public boolean isFinished() {
		return this.completed;
	}

}
