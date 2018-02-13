package org.usfirst.frc.team5472.robot.autonomous.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command{
	
	private boolean finished;
	private double time;
	
	public Delay(double time) {
		this.time = time;
	}
	
	@Override
	public void execute() {
		//TODO: Run a test to see if commands execute in main thread
		Timer.delay(time);
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
