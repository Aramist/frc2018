package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LBOLL extends CommandGroup{
	
	public LBOLL() {
		addSequential(new LSCXL());
	}
	
}
