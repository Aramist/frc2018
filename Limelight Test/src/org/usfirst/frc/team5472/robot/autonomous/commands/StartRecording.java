package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.autonomous.Recorder;

import edu.wpi.first.wpilibj.command.Command;

public class StartRecording extends Command{

	private boolean finished;
	private Recorder recorder;
	
	public StartRecording(Recorder recorder) {
		this.recorder = recorder;
	}
	
	@Override
	public void execute() {
		recorder.start();
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
