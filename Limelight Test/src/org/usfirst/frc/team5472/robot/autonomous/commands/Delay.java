package org.usfirst.frc.team5472.robot.autonomous.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command{
	
	private boolean finished;
	private double time;
	
	/**
	 * Creates a delay for a specified number of seconds.
	 *
	 * @param time the delay length in seconds.
	 */
	public Delay(double time) {
		this.time = time;
	}
	
	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Command#execute()
	 */
	@Override
	public void execute() {
		Timer.delay(time);
		finished = true;
	}
	
	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Command#isFinished()
	 */
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
