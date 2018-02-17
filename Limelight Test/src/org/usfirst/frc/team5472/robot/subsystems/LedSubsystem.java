package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Constants;

import edu.wpi.first.wpilibj.DigitalOutput;

public class LedSubsystem {
	
	public static enum LedColor{
		RED(true, false, false), GREEN(false, true, false), BLUE(false, false, true),
		YELLOW(true, true, false), PURPLE(true, false, true), LIGHT_BLUE(false, true, true),
		WHITE(true, true, true), OFF(false, false, false);
		
		private boolean red; //I think these should be relays
		private boolean green;
		private boolean blue;
		
		private LedColor(boolean r, boolean g, boolean b) {
			red = r;
			green = g;
			blue = b;
		}
		
		public boolean getRed() {return red;}
		public boolean getGreen() {return green;}
		public boolean getBlue() {return blue;}
	}
	
	private DigitalOutput red, green, blue;
	
	public LedSubsystem() {
		red = new DigitalOutput(Constants.LED_RED_DIO);
		green = new DigitalOutput(Constants.LED_GREEN_DIO);
		blue = new DigitalOutput(Constants.LED_BLUE_DIO);
	}
	
	public void setColor(LedColor color) {
		red.set(color.getRed());
		green.set(color.getGreen());
		blue.set(color.getBlue());
	}
	
}
