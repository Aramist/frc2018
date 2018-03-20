package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.autonomous.commands.Delay;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.LiftZero;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftHalf;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftHigh;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.IntakePushSlow;
import org.usfirst.frc.team5472.robot.commands.LiftStop;
import org.usfirst.frc.team5472.robot.commands.LowGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LSCXR extends CommandGroup {

	public LSCXR() {
		addParallel(new GripClose());

		addParallel(new RaiseLiftHalf(), 3);
		addSequential(new Forward(5.15), 6);
		addSequential(new Turn(-90), 2);
		addSequential(new Forward(4.6), 6);
		addSequential(new Turn(0), 2);
		addParallel(new LowGear());
		addSequential(new RaiseLiftHigh(), 4);
		addSequential(new Forward(0.8), 2);
		addSequential(new IntakePushSlow());
		addSequential(new Delay(1));
		addSequential(new LiftStop());
		addSequential(new Forward(-0.7));
		addSequential(new LiftZero(), 3);
	}
}
