package org.usfirst.frc.team5472.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;

public class PassAutoLine extends Command{
	
	private boolean completed = false;
	
	@Override
	public void initialize() {
		
	}
	
	@Override
	public void execute() {}
	
	@Override
	public void end() {}
	
	@Override
	public void interrupted() {}
	
	public boolean isFinished() {
		return completed;
	}
}
