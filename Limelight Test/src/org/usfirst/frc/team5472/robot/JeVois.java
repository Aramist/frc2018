 package org.usfirst.frc.team5472.robot;

import edu.wpi.first.wpilibj.SerialPort;

public class JeVois {
	
	private SerialPort port;
	
	public JeVois() {
		port = new SerialPort(9600, SerialPort.Port.kUSB);
	}
	
	public String read() {
		if(port.getBytesReceived() == 0)
			return "";
		return new String(port.read(port.getBytesReceived()));
	}
	
	public void write(String toWrite) {	
		port.writeString(toWrite);
	}
	
	public void parse() {
		
		

		String[] end = new String[6];
		String out = read();
		int i = 0;
		for(String a:out.split(","))
		{		
			end[i] = a.split(":")[1];
			i++;
		}		
		
				
		
	}
}
