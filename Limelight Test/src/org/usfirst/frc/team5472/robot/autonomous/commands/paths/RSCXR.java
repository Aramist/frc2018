package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.autonomous.commands.ApproachBox;
import org.usfirst.frc.team5472.robot.autonomous.commands.Delay;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.LiftZero;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftHalf;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftHigh;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.BoxPipeline;
import org.usfirst.frc.team5472.robot.commands.EnableVision;
import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.GripOpen;
import org.usfirst.frc.team5472.robot.commands.HighGear;
import org.usfirst.frc.team5472.robot.commands.IntakePull;
import org.usfirst.frc.team5472.robot.commands.IntakePullSlow;
import org.usfirst.frc.team5472.robot.commands.IntakePushAuto;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;
import org.usfirst.frc.team5472.robot.commands.LowGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RSCXR extends CommandGroup {

	public RSCXR() {
addParallel(new GripClose());
		
		addParallel(new RaiseLiftHalf(), 4);
		addSequential(new Forward(4.862), 4);
		addSequential(new Turn(19), 3);
		
		addSequential(new RaiseLiftHigh(), 2);
		addSequential(new LowGear());
		addSequential(new Forward(1.00), 1); // From 1.37
		addSequential(new IntakePushAuto()); // From IntakePushSlow()
		addSequential(new Delay(1));
		addSequential(new IntakeStop());
		
		addSequential(new Forward(-0.400), 0.7); // From -0.7 
		addSequential(new LiftZero(), 3);
		
		addSequential(new Turn(160), 3); // From 140
		addSequential(new HighGear());
		
		addParallel(new IntakePull());
		addSequential(new EnableVision());
		addSequential(new BoxPipeline());
		addParallel(new GripOpen());
		addSequential(new ApproachBox(), 3);
//		addSequential(new IntakeStop());
		addSequential(new GripClose());
		addSequential(new IntakePullSlow());
		addSequential(new Forward(-0.600), 0.6);
	}
}
