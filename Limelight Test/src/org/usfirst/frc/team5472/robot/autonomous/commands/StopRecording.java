package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.autonomous.Recorder;

import edu.wpi.first.wpilibj.command.Command;

public class StopRecording extends Command{

	private boolean finished;
	private Recorder recorder;
	
	public StopRecording(Recorder recorder) {
		this.recorder = recorder;
	}
	
	@Override
	public void execute() {
		recorder.stop();
		recorder.save("autotest");
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
