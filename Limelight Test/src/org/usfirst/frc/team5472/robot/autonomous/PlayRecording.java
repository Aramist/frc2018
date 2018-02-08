package org.usfirst.frc.team5472.robot.autonomous;

import java.util.ArrayList;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.autonomous.Recorder.Reading;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PlayRecording extends Command {
	
	private ArrayList<Reading> velocityData;
	private boolean finished;
	
	private DriveSubsystem drive;
	
	public PlayRecording(Recorder record) {
		this.velocityData = record.getVelocityReadings();
		finished = false;
	}
	
	@Override
	public void initialize() {
		this.drive = Robot.driveSubsystem;
	}
	
	@Override
	public void execute() {
		new Thread(() -> {
			int index = 0;
			int length = velocityData.size();
			while(!isCanceled() && index < length) {
				Reading currentVelocity = velocityData.get(index);
				drive.drive(currentVelocity.left, currentVelocity.right);
				Timer.delay(0.05);
			}
			finished = true;
		}).start();
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
