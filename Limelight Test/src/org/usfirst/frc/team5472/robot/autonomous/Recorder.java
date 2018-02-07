package org.usfirst.frc.team5472.robot.autonomous;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;

public class Recorder {
	
	private static class Reading{
		public double left;
		public double right;
		
		public Reading(double l, double r) {
			left = l;
			right = r;
		}
	}
	
	private boolean enabled = false;
	private DriveSubsystem drive;
//	private ArrayList<Reading> positionData;
	private ArrayList<Reading> velocityData;
	private Thread readerThread;
	
	public Recorder() {
		drive = Robot.driveSubsystem;
//		positionData = new ArrayList<Reading>(/);
		velocityData = new ArrayList<Reading>();
	}
	
	public void start() {
		Runnable readerTask = () -> {
			while (enabled) {
//				positionData.add(new Reading(drive.getLeftPosition(), drive.getRightPosition()));
				velocityData.add(new Reading(drive.getLeftVelocity(), drive.getRightVelocity()));
				Timer.delay(0.05);
			}
		};
		readerThread = new Thread(readerTask);
		enabled = true;
		readerThread.start();
	}
	
	public void stop() {
		enabled = false;
		Timer.delay(0.1); //Wait for the thread to stop
	}

	public void save(String name) {
		String directoryPathString = "/home/lvuser/paths/";
		Paths.get(directoryPathString).toFile().mkdirs();
		Path filePath = Paths.get(directoryPathString + name + ".path");
		try {
			FileOutputStream fos = new FileOutputStream(filePath.toFile());
			DataOutputStream dos = new DataOutputStream(fos);
			//First write the path length
			//then write each Reading
			dos.writeInt(velocityData.size());
			for(Reading r : velocityData) {
				dos.writeDouble(r.left);
				dos.writeDouble(r.right);
			}
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Recorder load(String name) {
		return null; //TODO
	}
}
