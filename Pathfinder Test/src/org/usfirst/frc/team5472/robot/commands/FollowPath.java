package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class FollowPath extends Command {

	private static final EncoderFollower leftFollower = new EncoderFollower();
	private static final EncoderFollower rightFollower = new EncoderFollower();

	static {
		leftFollower.configurePIDVA(Constants.PATH_P, Constants.PATH_I, 0.0, Constants.PATH_FFV, Constants.PATH_FFA);
		rightFollower.configurePIDVA(Constants.PATH_P, Constants.PATH_I, 0.0, Constants.PATH_FFV, Constants.PATH_FFA);
	}

	private boolean isRunning = true;

	private Trajectory left;
	private Trajectory right;

	private Drive drive;

	public FollowPath(Trajectory[] path) {
		requires(Robot.drive);

		left = path[0];
		right = path[1];
	}

	@Override
	protected void initialize() {
		leftFollower.configureEncoder(drive.getLeftRaw(), Constants.ENCODER_TICKS_PER_REV, Constants.WHEEL_DIAMETER);
		rightFollower.configureEncoder(drive.getRightRaw(), Constants.ENCODER_TICKS_PER_REV, Constants.WHEEL_DIAMETER);

		// This automatically resets the followers
		leftFollower.setTrajectory(left);
		rightFollower.setTrajectory(right);

		new Thread() {
			public void run() {
				try {
					while (isRunning) {
						double leftOutput = leftFollower.calculate(drive.getLeftRaw());
						double rightOutput = rightFollower.calculate(drive.getRightRaw());
						
						double heading = drive.getHeading();
						double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
						double headingComponent = Constants.PATH_HEADING_P * Pathfinder.boundHalfDegrees(desiredHeading - heading);
						
						drive.drive(leftOutput + headingComponent, rightOutput - headingComponent);

						Thread.sleep((long) (1000 * Constants.PATH_DT));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@Override
	protected void execute() {}

	@Override
	protected void end() {
		isRunning = false;
		drive.drive(0.0, 0.0);
		System.out.println("Finished following path.");
	}

	@Override
	protected void interrupted() {
		isRunning = false;
	}

	@Override
	protected boolean isFinished() {
		return leftFollower.isFinished() && rightFollower.isFinished();
	}
}
