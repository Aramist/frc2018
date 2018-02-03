package org.usfirst.frc5472.TrackerTest;

import edu.wpi.first.wpilibj.SerialPort;

public class JeVois {

	private SerialPort port;

	public JeVois() {
		port = new SerialPort(115200, SerialPort.Port.kUSB);
	}

	public String read() {
		if (port.getBytesReceived() == 0) {
			return "";
		}

		return new String(port.read(port.getBytesReceived()));
	}

	public void write(String toWrite) {
		port.writeString(toWrite);

	}

	public void parse() {

	}
//comment
}
