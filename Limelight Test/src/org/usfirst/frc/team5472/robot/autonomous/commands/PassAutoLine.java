package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.IntakePush;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PassAutoLine extends CommandGroup {

	public PassAutoLine() {
		addParallel(new GripClose());
		
		addParallel(new RaiseLiftLow(), 4);
		addSequential(new Forward(4.826), 3);
		addSequential(new Turn(-19), 3);
		
		addParallel(new RaiseLiftHigh(), 4);
		addSequential(new Forward(1.800), 4);
		addSequential(new IntakePush());
		addSequential(new Delay(0.5));
		addSequential(new IntakeStop());
		
		addSequential(new Forward(-0.700), 2);
		addSequential(new Turn(-150), 3);
		
		addSequential(new LiftZero(), 2);
		addSequential(new CollectBox(), 5);
	}
}
