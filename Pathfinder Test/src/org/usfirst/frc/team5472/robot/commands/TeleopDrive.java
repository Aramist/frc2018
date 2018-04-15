package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command{

	private Joystick stick = Robot.joystick;
	
	public TeleopDrive() {
		requires(Robot.drive);
	}
	
	@Override
	public void initialize() {
	}
	
	@Override
	public void execute() {
		if(!DriverStation.getInstance().isAutonomous()) {
			double y = -stick.getY();
			double x = stick.getX() / 2;
			
			y = Math.abs(y) < 0.15 ? 0 : y;
			x = Math.abs(x) < 0.05 ? 0 : x;
			
			Robot.drive.drive(y + x, y - x);
		}
	}
	
	public boolean isFinished() {
		return false;
	}
}
