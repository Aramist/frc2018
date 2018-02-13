package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team5472.robot.pathfinder.Segment;
import com.team5472.robot.pathfinder.followers.Encoder;
import com.team5472.robot.pathfinder.followers.EncoderConfig;

import edu.wpi.first.wpilibj.command.Command;

public class FollowPath extends Command{
	
//	private static boolean almostEqual(double a, double b) {
//		return Math.abs(a - b) <= 1e-4;
//	}
	
	private Segment[] left;
	private Segment[] right;
	private int currentIndex;
	
	private Encoder leftFollower;
	private Encoder rightFollower;
	
	
	private DriveSubsystem drive;
	
	public FollowPath(Segment[] leftTrajectory, Segment[] rightTrajectory) {
		left = leftTrajectory;
		right = rightTrajectory;
		currentIndex = 0;
		
		leftFollower = new Encoder(new EncoderConfig(currentIndex, (int) Constants.LEFT_ENCODER_TICKS_PER_REV, Constants.WHEEL_DIAMETER,
				Constants.ENCODER_FOLLOWER_P, Constants.ENCODER_FOLLOWER_I,
				Constants.ENCODER_FOLLOWER_D, Constants.ENCODER_FOLLOWER_V,
				Constants.ENCODER_FOLLOWER_A), left);
		rightFollower = new Encoder(new EncoderConfig(currentIndex, (int) Constants.RIGHT_ENCODER_TICKS_PER_REV, Constants.WHEEL_DIAMETER,
				Constants.ENCODER_FOLLOWER_P, Constants.ENCODER_FOLLOWER_I,
				Constants.ENCODER_FOLLOWER_D, Constants.ENCODER_FOLLOWER_V,
				Constants.ENCODER_FOLLOWER_A), right);
	}
	
	@Override
	public void initialize() {
		drive = Robot.driveSubsystem;
		drive.setControlMode(ControlMode.PercentOutput);
		drive.drive(0, 0);
		drive.resetEncoders();
	}
	
	@Override
	public void execute() {
		double leftOutput = leftFollower.calculate(drive.getLeftRaw());
		double rightOutput = rightFollower.calculate(drive.getRightRaw());
		drive.drive(leftOutput, rightOutput);
	}
	
	@Override
	public void end() {
		drive.drive(-0.1, -0.1);
	}
	
	protected boolean isFinished() {
		return leftFollower.isFinished();
	}
}
