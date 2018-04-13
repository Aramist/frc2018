package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCommand extends CommandGroup {

	/**
	 * This autonomous path exists purely for testing purposes and should not be mentioned in competition.
	 */
	public TestCommand() {
		addSequential(new Forward(100.0, 0.4)); // 100 meters
//		addSequential(new LowGear());
//		addSequential(new Turn(90));
//		addSequential(new Delay(2));
//		addSequential(new Turn(-90));
//		addSequential(new Delay(2));
//		addSequential(new Turn(0));
	}
}
