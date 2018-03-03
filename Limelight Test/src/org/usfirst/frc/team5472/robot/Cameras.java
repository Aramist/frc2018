package org.usfirst.frc.team5472.robot;

import edu.wpi.first.wpilibj.CameraServer;

public class Cameras {
	
	public Cameras() {
		CameraServer.getInstance().startAutomaticCapture(0);
	}
	
}
