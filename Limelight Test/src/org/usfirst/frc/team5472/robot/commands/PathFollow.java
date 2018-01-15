package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.paths.Path;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.followers.EncoderFollower;

public class PathFollow extends Command {
	
	private static final double WHEEL_DIAMETER = 0.10; //meters
	private static final double MAX_VELOCITY = 1.7;
	private static final int TICKS_PER_REVOLUTION_LEFT = 7847;
	private static final int TICKS_PER_REVOLUTION_RIGHT = 7709;
	
	private DriveSubsystem instance;
	
	private Path pathToFollow;
	
	private EncoderFollower left;
	private EncoderFollower right;
	
	public PathFollow(Path toFollow) {
		pathToFollow = toFollow;
		left = new EncoderFollower(pathToFollow.toTankModifier().getLeftTrajectory());
		right = new EncoderFollower(pathToFollow.toTankModifier().getRightTrajectory());
		left.configureEncoder(0, TICKS_PER_REVOLUTION_LEFT, WHEEL_DIAMETER);
		right.configureEncoder(0, TICKS_PER_REVOLUTION_RIGHT, WHEEL_DIAMETER);
		left.configurePIDVA(1.0, 0.0, 0.0, 1.0 / MAX_VELOCITY, 0.0);
		right.configurePIDVA(1.0, 0.0, 0.0, 1.0 / MAX_VELOCITY, 0.0);
	}
	
	@Override
	public void initialize() {
		this.instance = Robot.driveSubsystem;
		instance.resetEncoders();
	}
	
	@Override
	public void execute() {
		double l = left.calculate(instance.getRawLeft());
		double r = right.calculate(instance.getRawRight());
		
		double angle = instance.getHeading();
		double desiredAngle = Pathfinder.r2d(left.getHeading());
		
		double deltaT = Pathfinder.boundHalfDegrees(desiredAngle - angle);
		
		double turn = 0.8 * (-1.0 / 80.0) * deltaT;
		
		instance.drive(l - turn, r + turn);
	}
	
	@Override
	protected boolean isFinished() {
		return left.isFinished() && right.isFinished();
	}

}
