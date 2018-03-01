package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Cameras;

import edu.wpi.first.wpilibj.command.Command;

public class SwitchCamera extends Command{
	
	private boolean finished;
	private Cameras cameras;
	
	@Override
	public void execute() {
		int index = cameras.getEnabledCamera();
		index = ((index == 1) ? 0 : 1);
		cameras.setEnabledCamera(index);
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
}
