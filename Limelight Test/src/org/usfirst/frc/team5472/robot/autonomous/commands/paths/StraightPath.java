package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import java.io.File;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;


public class StraightPath extends Command {

	EncoderFollower rightEncoder;
	EncoderFollower leftEncoder;
	TimerTask encoderTask;
	
	public StraightPath()
	{
		File leftFilePath = Paths.get("/home/lvuser/TurningTest/turningpath_left_Jaci.csv").toFile();
		Trajectory leftTrajectory = Pathfinder.readFromCSV(leftFilePath);
		File rightFilePath = Paths.get("/home/lvuser/TurningTest/turningpath_right_Jaci.csv").toFile();
		Trajectory rightTrajectory = Pathfinder.readFromCSV(rightFilePath);
		
//		Robot.drive.resetEncoders();
		
		rightEncoder = new EncoderFollower();
		rightEncoder.configurePIDVA(1, 0, 0, 0, 0);
		rightEncoder.configureEncoder(0, Constants.TICKS_PER_REV, Constants.WHEEL_DIAMETER);
		rightEncoder.setTrajectory(rightTrajectory);
		
		leftEncoder = new EncoderFollower();
		leftEncoder.configurePIDVA(1, 0, 0, 0, 0);
		leftEncoder.configureEncoder(0, Constants.TICKS_PER_REV, Constants.WHEEL_DIAMETER);
		leftEncoder.setTrajectory(leftTrajectory);
		
		encoderTask = new TimerTask() {
			public void run() {
				double leftOutput = leftEncoder.calculate(Robot.drive.getLeftRaw());
				double rightOutput = rightEncoder.calculate(Robot.drive.getRightRaw());
				SmartDashboard.putNumber("Left Output", leftOutput);
				SmartDashboard.putNumber("Right Output", rightOutput);
				Robot.drive.drive(leftOutput, rightOutput);
				
				if(isFinished()) {
					Robot.drive.drive(0, 0);
					this.cancel();
				}
			}
		};
	}
	
	public void end()
	{
		encoderTask.cancel();
		Robot.drive.drive(0, 0);
	}
	
	public void initialize()
	{
		Robot.drive.resetEncoders();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(encoderTask, 0, 50L);
	}
	
	protected boolean isFinished() {
		return leftEncoder.isFinished() && rightEncoder.isFinished();
	}
}
