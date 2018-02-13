package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command{
	
	private boolean finished;
	private double target;
	
	private DriveSubsystem drive;
	
	public Turn(double target) {
		this.target = target;
		this.finished = false;
		requires(Robot.driveSubsystem);
	}
	
	@Override
	public void initialize() {
		this.drive = Robot.driveSubsystem;
	}
	
	@Override
	public void execute() {
		double error = target - drive.getHeading();
		double output = DriveSubsystem.truncate(error * Constants.DRIVE_AUTO_TURN_KP, 0.5);
		drive.drive(-output, output);
		finished = error < 0.5;
	}
	
	@Override
	public boolean isFinished() {
		return finished;
	}
}
