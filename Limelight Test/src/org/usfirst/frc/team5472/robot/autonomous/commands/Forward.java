package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.autonomous.BlackHole;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Forward extends Command{
	
	private double distance;
	private double speed;
	
	private double startingAngle;
	private double startingPosition;
	
	private DriveSubsystem drive;
	
	private PIDController driveController;
	private PIDController headingController;
	
	private PIDSource drivePositionSource;
	private PIDSource driveAngleSource;
	
	public Forward(double distance) {
		this(distance, Constants.DRIVE_AUTO_OUTPUT_LIMIT);
	}
	
	public Forward(double distance, double maxSpeed) {
		requires(Robot.drive);
		this.distance = distance;
		this.speed = Math.abs(maxSpeed);
		
		drivePositionSource = new PIDSource() {
				public double pidGet() {
					return (drive.getLeftPosition() + drive.getRightPosition()) / 2.0 - startingPosition;
				}
				public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}
				public void setPIDSourceType(PIDSourceType t) {}
		};
		
		driveAngleSource = new PIDSource() {
			public double pidGet() {
				return drive.getHeading() - startingAngle;
			}
			public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}
			public void setPIDSourceType(PIDSourceType t) {}
		};
		
		driveController = new PIDController(Constants.DRIVE_DISTANCE_P, Constants.DRIVE_DISTANCE_I, Constants.DRIVE_DISTANCE_D, 0, drivePositionSource, new BlackHole());
		headingController = new PIDController(Constants.DRIVE_MAINTAIN_HEADING_P, Constants.DRIVE_MAINTAIN_HEADING_I, Constants.DRIVE_MAINTAIN_HEADING_D, 0, driveAngleSource, new BlackHole());

		driveController.setAbsoluteTolerance(0.02);
		driveController.setInputRange(-10, 10);
		driveController.setOutputRange(-this.speed, this.speed);
		
		headingController.setInputRange(-10, 10);
		headingController.setOutputRange(-0.1, 0.1);
	}
	
	@Override
	public void initialize() {
		drive = Robot.drive;
		
		this.startingAngle = drive.getHeading();
		this.startingPosition = (drive.getLeftPosition() + drive.getRightPosition());
		

		driveController.setSetpoint(distance);
		headingController.setSetpoint(0);
		
		driveController.enable();
		headingController.enable();
	}
	
	@Override
	public void execute() {
		double driveOutput = driveController.get();
		double headingOutput = headingController.get();
		
		SmartDashboard.putNumber("Forward error", driveController.getError());
		
		drive.turn(driveOutput, -headingOutput);
	}
	
	@Override
	public void end() {
		driveController.disable();
		headingController.disable();
		driveController.free();
		headingController.free();
		
		drive.drive(-0.1, -0.1);
		Timer.delay(0.05);
		drive.drive(0.0, 0.0);
	}
	
	@Override
	public boolean isFinished() {
		return driveController.onTarget();
	}

}
