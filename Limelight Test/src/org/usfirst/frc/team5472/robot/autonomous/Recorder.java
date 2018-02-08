package org.usfirst.frc.team5472.robot.autonomous;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.Timer;

public class Recorder {
	
	protected static class Reading{
		public double left;
		public double right;
		
		public Reading(double l, double r) {
			left = l;
			right = r;
		}
	}
	
	private boolean enabled = false;
	private DriveSubsystem drive;
	private ArrayList<Reading> velocityData;
	private Thread readerThread;
	
	public Recorder() {
		drive = Robot.driveSubsystem;
		velocityData = new ArrayList<Reading>();
	}
	
	protected Recorder(ArrayList<Reading> velocityReadings) {
		drive = Robot.driveSubsystem;
		this.velocityData = velocityReadings;
	}
	
	public void start() {
		Runnable readerTask = () -> {
			while (enabled) {
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
			//then write each Reading. Left followed by right.
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
		int length = 0;
		ArrayList<Reading> readings = new ArrayList<Reading>();
		try {
			File file = Paths.get("/home/lvuser/paths/" + name + ".path").toFile();
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			length = dis.readInt();
			for(int i = 0; i < length; i++)
				readings.add(new Reading(dis.readDouble(), dis.readDouble()));
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Recorder(readings);
	}
	
	protected ArrayList<Reading> getVelocityReadings(){
		return velocityData;
	}
}
