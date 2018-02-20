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
		
		public String toString() {
			return "{" + left + ", " + right + "}";
		}
	}
	
	protected static class Segment{
		public Reading position;
		public Reading velocity;
		
		public Segment() {}
		public Segment(Reading p, Reading v) {
			position = p;
			velocity = v;
		}
		
		public String toString() {
			return position.toString() + " " + velocity.toString();
		}
	}
	
	protected static final double dt = 0.05;
	
	private boolean enabled = false;
	private DriveSubsystem drive;
	private ArrayList<Segment> data;
	private Thread readerThread;
	
	public Recorder() {
		drive = Robot.drive;
		data = new ArrayList<>();
	}
	
	protected Recorder(ArrayList<Segment> segments) {
		drive = Robot.drive;
		data = segments;
	}
	
	public void start() {
		Runnable readerTask = () -> {
			while (enabled) {
				data.add(new Segment(new Reading(drive.getLeftPosition(), drive.getRightPosition()),
						 			 new Reading(drive.getLeftVelocity(), drive.getRightVelocity())));
				Timer.delay(dt);
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
			//then write each Reading. Position followed by Velocity, Left followed by Right
			dos.writeInt(data.size());
			for(Segment seg : data) {
				dos.writeDouble(seg.position.left);
				dos.writeDouble(seg.position.right);
				dos.writeDouble(seg.velocity.left);
				dos.writeDouble(seg.velocity.right);
			}
			dos.close();
			System.out.println("Saved " + data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Recorder load(String name) {
		int length = 0;
		ArrayList<Segment> segments = new ArrayList<>();
		try {
			File file = Paths.get("/home/lvuser/paths/" + name + ".path").toFile();
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			length = dis.readInt();
			for(int i = 0; i < length; i++)
				segments.add(new Segment(
						new Reading(dis.readDouble(), dis.readDouble()),
						new Reading(dis.readDouble(), dis.readDouble())));
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Recorder(segments);
	}
	
	protected ArrayList<Segment> getSegments(){
		return data;
	}
	
	public double[][][] toTalonTrajectory(){
		int size = data.size();
		double[][] left = new double[size][3];
		double[][] right = new double[size][3];
		Segment current;
		
		for(int i = 0; i < size; i++) {
			current = data.get(i);
			left[i] = new double[] {current.position.left, current.velocity.left, dt * 1000};
			right[i] = new double[] {current.position.right, current.velocity.right, dt * 1000};
		}
		
		return new double[][][] {left, right};
	}
}
