package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Limelight;
import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class FlashLights extends Command{
	
	private boolean finished = false;
	private boolean initialState = false;
	private Limelight limelight;
	
	@Override
	public void initialize() {
		limelight = Robot.limelight;
		initialState = limelight.getLed();
	}
	
	@Override
	public void execute() {
		new Thread(() -> {
			for(int i = 0; i < 5; i++) {
				limelight.setLed(!initialState);
				Timer.delay(0.1);
				limelight.setLed(initialState);
				Timer.delay(0.1);
			}
			
		}).start();
		finished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return finished;
	}
}
