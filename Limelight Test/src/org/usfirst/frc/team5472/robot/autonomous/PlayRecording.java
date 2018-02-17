package org.usfirst.frc.team5472.robot.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.autonomous.Recorder.Segment;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PlayRecording extends Command {

	private ArrayList<Segment> segments;
	private boolean reversed = false;
	private boolean finished;
	
	private DriveSubsystem drive;
	
	public PlayRecording(Recorder record) {
		drive = Robot.drive;
		this.segments = record.getSegments();
		drive.setControlMode(ControlMode.PercentOutput);
		finished = false;
	}
	
	public PlayRecording(Recorder record, boolean reversed) {
		this(record);
		this.reversed = reversed;
	}
	
	@Override
	public void initialize() {
		this.drive = Robot.drive;
		drive.setControlMode(ControlMode.PercentOutput);
	}
	
	@Override
	public void execute() {
		if(!reversed)
			new Thread(() -> {
				int index = 0;
				int length = segments.size();
				while(!isCanceled() && index < length) {
					Segment current = segments.get(index++);
					System.out.println("Left Velocity: " + current.velocity.left);
					System.out.println("Left Position: " + current.position.left);
					double left = calculateLeft(current.position.left, current.velocity.left);
					double right = calculateRight(current.position.right, current.velocity.right);
					drive.drive(left, right);
					Timer.delay(Recorder.dt);
				}
				finished = true;
			}).start();
		else
			new Thread(() -> {
				int index = 0;
				int length = segments.size();
				while(!isCanceled() && index < length) {
					Segment current = segments.get(index);
					double left = calculateLeft(-current.position.left, -current.velocity.left);
					double right = calculateRight(-current.position.right, -current.velocity.right);
					drive.drive(left, right);
					Timer.delay(Recorder.dt);
				}
				finished = true;
			}).start();
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
	
	private double previousLeftError = 0;
	private double calculateLeft(double position, double velocity) {
		double distanceTraveled = drive.getLeftPosition();
		double error = position - distanceTraveled;
		double p = Constants.DRIVE_FOLLOWER_P;
		double d = Constants.DRIVE_FOLLOWER_D;
		double v = Constants.DRIVE_FOLLOWER_V;
		double dt = Recorder.dt;
		double calculated = p * error 
				+ d * ((error - previousLeftError) / dt)
				+ v * velocity;
		previousLeftError = error;
		return calculated;
	}
	
	private double previousRightError = 0;
	private double calculateRight(double position, double velocity) {
		double distanceTraveled = drive.getRightPosition();
		double error = position - distanceTraveled;
		double p = Constants.DRIVE_FOLLOWER_P;
		double d = Constants.DRIVE_FOLLOWER_D;
		double v = Constants.DRIVE_FOLLOWER_V;
		double dt = Recorder.dt;
		double calculated = p * error 
				+ d * ((error - previousRightError) / dt)
				+ v * velocity;
		previousRightError = error;
		return calculated;
	}
}
