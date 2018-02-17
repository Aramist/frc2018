package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command{
	
	private PIDController turnController;
	private double setpoint;
	
	private DriveSubsystem drive;
	
	public Turn(double target) {
		setpoint = target;
		drive = Robot.drive;
		turnController = drive.turnAngleController;
	}
	
	@Override
	public void initialize() {
		turnController.reset();
		turnController.setSetpoint(setpoint);
		turnController.setInputRange(-360, 360);
		turnController.setOutputRange(-0.7, 0.7);
		turnController.setAbsoluteTolerance(0.5);
		turnController.enable();
	}

	@Override
	public boolean isFinished() {
		return turnController.onTarget();
	}
	
	@Override
	public void end() {
		turnController.reset();
	}
}
