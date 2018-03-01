package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.autonomous.commands.Delay;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftHigh;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftLow;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.IntakePush;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LSCXL extends CommandGroup {

	public LSCXL() {
		addParallel(new GripClose());
		
		addParallel(new RaiseLiftLow(), 4);
		addSequential(new Forward(7.61), 3);
		addSequential(new Turn(-90));
		addSequential(new RaiseLiftHigh(), 4);
		addSequential(new Forward(0.5));
		addSequential(new IntakePush());
		addSequential(new Delay(1));
		addSequential(new IntakeStop());
	}
}
