package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class Forward extends Command{
	
	private boolean finished;
	private double distance;
	
	private DriveSubsystem drive;
	private PIDController controller;
	
	public Forward(double distance) {
		this.distance = distance;
		this.finished = false;
		
		requires(Robot.driveSubsystem);
	}
	
	@Override
	public void initialize() {
		drive = Robot.driveSubsystem;
		controller = drive.drivePositionController;
		
		drive.resetEncoders();
		controller.reset();
		controller.setSetpoint(distance);
		controller.setAbsoluteTolerance(0.05); //5 cm
		controller.setInputRange(0, 10);
		controller.setOutputRange(0, Constants.DRIVE_AUTO_OUTPUT_LIMIT);
		controller.enable();
	}
	
	@Override
	public void end() {
		controller.disable();
		drive.drive(-0.1, -0.1);
	}
	
	@Override
	public boolean isFinished() {
		return controller.onTarget();
	}

}
