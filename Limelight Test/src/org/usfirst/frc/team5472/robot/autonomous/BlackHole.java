package org.usfirst.frc.team5472.robot.autonomous;

import edu.wpi.first.wpilibj.PIDOutput;

public class BlackHole implements PIDOutput{

	/**
	 * Takes in PID output and does nothing with it.
	 */
	public BlackHole() {}
	
	@Override
	public void pidWrite(double output) {
		// Does nothing
	}

}
