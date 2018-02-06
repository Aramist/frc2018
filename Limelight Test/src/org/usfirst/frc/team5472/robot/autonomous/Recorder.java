package org.usfirst.frc.team5472.robot.autonomous;

public class Recorder {
	
//	private static class Reading{
//		public double left;
//		public double right;
//		
//		public Reading(double l, double r) {
//			left = l;
//			right = r;
//		}
//	}
//	
//	private boolean enabled = false;
//	private DriveSubsystem drive;
//	private ArrayList<Reading> positionData;
//	private ArrayList<Reading> velocityData;
//	private Thread readerThread;
//	
//	public Recorder() {
//		drive = Robot.driveSubsystem;
//		positionData = new ArrayList<Reading>();
//		velocityData = new ArrayList<Reading>();
//	}
//	
//	public void start() {
//		Runnable readerTask = () -> {
//			while (enabled) {
//				positionData.add(new Reading(drive.getLeftPosition(), drive.getRightPosition()));
//				velocityData.add(new Reading(drive.getLeftVelocity(), drive.getRightVelocity()));
//				Timer.delay(0.05);
//			}
//		};
//		readerThread = new Thread(readerTask);
//		enabled = true;
//		readerThread.start();
//	}
//	
//	public void stop() {
//		enabled = false;
//		Timer.delay(0.1); //Wait for the thread to stop
//	}
//	
//	private static byte[] doubleToBytes(double d) {
//		long bits = Double.doubleToLongBits(d);
//		byte[] buffer = new byte[8];
//		
//		buffer[0] = (byte) ((bits >> 56) & 0xFF);
//		buffer[1] = (byte) ((bits >> 48) & 0xFF);
//		buffer[2] = (byte) ((bits >> 40) & 0xFF);
//		buffer[3] = (byte) ((bits >> 32) & 0xFF);
//		
//		buffer[4] = (byte) ((bits >> 24) & 0xFF);
//		buffer[5] = (byte) ((bits >> 16) & 0xFF);
//		buffer[6] = (byte) ((bits >> 8) & 0xFF);
//		buffer[7] = (byte) (bits & 0xFF);
//		
//		return buffer;
//	}
//	
//	private static byte[] intToBytes(int i) {
//		byte[] buffer = new byte[4];
//		
//		buffer[0] = (byte) ((Integer.divideUnsigned(i, 24) & 0xFF);
//		buffer[1] = (byte) ((Integer.divideUnsigned(i, 16) & 0xFF);
//		buffer[2] = (byte) ((Integer.divideUnsigned(i, 8) & 0xFF);
//		buffer[3] = (byte) (i & 0xFF);
//		return buffer;
//	}
//	
//	public static int bytesToInt(byte[] bytes) {
//		return (bytes[0] << 24) | (bytes[1] << 16)
//				| (bytes[2] << 8) | bytes[3];
//	}
//	public void save(String name) {
//		String directoryPathString = "/home/lvuser/paths/";
//		Paths.get(directoryPathString).toFile().mkdirs();
//		Path filePath = Paths.get(directoryPathString + name + ".path");
//		try {
//		FileOutputStream fos = new FileOutputStream(filePath.toFile());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
