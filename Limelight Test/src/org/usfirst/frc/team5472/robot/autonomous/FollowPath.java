package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.team5472.robot.pathfinder.Segment;

import edu.wpi.first.wpilibj.command.Command;

public class FollowPath extends Command{
	
//	private static boolean almostEqual(double a, double b) {
//		return Math.abs(a - b) <= 1e-4;
//	}
	
	private Segment[] left;
	private Segment[] right;
	private int currentIndex;
	private boolean finished;
	
//	private Encoder leftFollower;
//	private Encoder rightFollower;
	
	private DriveSubsystem drive;
	
	public FollowPath(Segment[] leftTrajectory, Segment[] rightTrajectory) {
		left = leftTrajectory;
		right = rightTrajectory;
		currentIndex = 0;
		finished = false;
		
//		leftFollower = new Encoder(new EncoderConfig(currentIndex, (int) Constants.LEFT_ENCODER_TICKS_PER_REV, Constants.WHEEL_DIAMETER,
//				Constants.ENCODER_FOLLOWER_P, Constants.ENCODER_FOLLOWER_I,
//				Constants.ENCODER_FOLLOWER_D, Constants.ENCODER_FOLLOWER_V,
//				Constants.ENCODER_FOLLOWER_A), left);
//		rightFollower = new Encoder(new EncoderConfig(currentIndex, (int) Constants.RIGHT_ENCODER_TICKS_PER_REV, Constants.WHEEL_DIAMETER,
//				Constants.ENCODER_FOLLOWER_P, Constants.ENCODER_FOLLOWER_I,
//				Constants.ENCODER_FOLLOWER_D, Constants.ENCODER_FOLLOWER_V,
//				Constants.ENCODER_FOLLOWER_A), right);
	}
	
	@Override
	public void initialize() {
		drive = Robot.driveSubsystem;
		drive.setControlMode(ControlMode.Velocity);
		drive.drive(0, 0);
		drive.resetEncoders();
		System.out.println("Starting path following");
	}
	
	@Override
	public void execute() {
		double leftDistance = drive.getLeftPosition();
		double rightDistance = drive.getRightPosition();
		if(leftDistance > left[currentIndex].position && rightDistance > right[currentIndex].position) {
			if(++currentIndex == left.length)
				finished = true;
			return;
		}
		
		drive.drive(left[currentIndex].velocity * Constants.LEFT_ENCODER_TICKS_PER_METER,
				right[currentIndex].velocity * Constants.RIGHT_ENCODER_TICKS_PER_METER);
		
//		System.out.println(leftOutput);
	}
	
	protected boolean isFinished() {
		return finished;
	}
}
