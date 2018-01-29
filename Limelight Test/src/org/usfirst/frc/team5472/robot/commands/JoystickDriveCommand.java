package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickDriveCommand extends Command{
	
	private boolean completed = false;
	private Joystick instance;
	private JoystickButton shiftGear;
	
	public JoystickDriveCommand() {
		requires(Robot.driveSubsystem);
	}
	
	@Override
	public void initialize() {
		this.instance = Robot.controls.getJoystick();
		shiftGear = new JoystickButton(instance, 6);
	}
	
	@Override
	public void execute() {
		double y = instance.getY();
		double x = instance.getTwist() / 4;
		
		double left = y - x;
		double right = y + x;
		Robot.driveSubsystem.drive(left, right);
	}
	
	public boolean isFinished() {
		return this.completed;
	}

}
