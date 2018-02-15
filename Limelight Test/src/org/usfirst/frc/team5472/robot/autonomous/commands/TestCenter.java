package org.usfirst.frc.team5472.robot.autonomous.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCenter extends CommandGroup{
	
	public TestCenter() {
		addSequential(new Forward(0.5));
		addSequential(new Turn(-90));
	}
	
}
