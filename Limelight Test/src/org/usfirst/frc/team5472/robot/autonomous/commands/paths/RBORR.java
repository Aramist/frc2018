package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RBORR extends CommandGroup {
	
	public RBORR() {
		addSequential(new RSCXR());
	}
}
