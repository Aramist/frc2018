package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.autonomous.commands.Delay;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.LiftZero;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftHalf;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftHigh;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.IntakePushSlow;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;
import org.usfirst.frc.team5472.robot.commands.LowGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LSCXL extends CommandGroup {

	public LSCXL() {
		addParallel(new GripClose());
		
		addParallel(new RaiseLiftHalf(), 4);
		addSequential(new Forward(4.862), 4);
		addSequential(new Turn(-19), 3);
		
		addSequential(new RaiseLiftHigh(), 4);
		addSequential(new LowGear());
		addSequential(new Forward(1.500), 2);
		addSequential(new IntakePushSlow());
		addSequential(new Delay(1));
		addSequential(new IntakeStop());
		
		addSequential(new Forward(-0.700), 1);
		addSequential(new LiftZero(), 3);
	}
}
