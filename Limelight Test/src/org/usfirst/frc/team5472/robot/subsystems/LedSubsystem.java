package org.usfirst.frc.team5472.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.DataProvider;

import edu.wpi.first.wpilibj.DigitalOutput;

public class LedSubsystem implements DataProvider{
	
	/**
	 * An Enum created to hold all possible colors that can be displayed
	 */
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
	private LedColor currentColor;
	
	/**
	 * Instantiates the Led Subsystem and the DigitalInputs used.
	 */
	public LedSubsystem() {
		currentColor = LedColor.OFF;
		red = new DigitalOutput(Constants.LED_RED_DIO);
		green = new DigitalOutput(Constants.LED_GREEN_DIO);
		blue = new DigitalOutput(Constants.LED_BLUE_DIO);
	}
	
	/**
	 * Sets the color of the LED strip.
	 *
	 * @param color the color to be used.
	 */
	public void setColor(LedColor color) {
		currentColor = color;
		red.set(color.getRed());
		green.set(color.getGreen());
		blue.set(color.getBlue());
	}
	
	/**
	 * Gets the current color of the LED strip.
	 *
	 * @return the color of the LED strip.
	 */
	public LedColor getColor() {
		return currentColor;
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team5472.robot.DataProvider#getData()
	 */
	public HashMap<String, double[]> getData(){
		HashMap<String, double[]> toReturn = new HashMap<>();
		toReturn.put("LED Color", new double[] {
				currentColor.getRed() ? 255 : 0,
				currentColor.getGreen() ? 255 : 0,
				currentColor.getBlue() ? 255 : 0
		});
		return toReturn;
	}
}