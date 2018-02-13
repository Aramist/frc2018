package org.usfirst.frc.team5472.robot.autonomous.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCenter extends CommandGroup{
	
	public TestCenter() {
		addSequential(new Forward(1.0));
		addSequential(new Turn(-90));
	}
	
}
