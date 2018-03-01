package org.usfirst.frc.team5472.robot;

import java.util.ArrayList;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;

public class Cameras {
	
	private MjpegServer server;
	private int enabledCamera;
	private ArrayList<UsbCamera> cameraList = new ArrayList<>();
	
	public Cameras() {
		server = new MjpegServer("Camera Server", 2000);
		
		cameraList.add(new UsbCamera("left", 0));
		cameraList.add(new UsbCamera("right", 1));
		cameraList.get(0).setResolution(160, 120);
		cameraList.get(1).setResolution(160, 120);
		
		setEnabledCamera(0);
		enabledCamera = 0;
		System.out.println("Camera Server IP: " + server.getListenAddress());
	}

	public void setEnabledCamera(int index) {
		index = index < 0 ? 0 : (index > cameraList.size() ? cameraList.size() : index);
		enabledCamera = index;
		server.setSource(cameraList.get(index));
	}
	
	public int getEnabledCamera() {
		return enabledCamera;
	}
}
