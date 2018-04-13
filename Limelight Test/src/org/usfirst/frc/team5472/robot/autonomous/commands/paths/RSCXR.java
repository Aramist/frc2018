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

	/**
	 * Autonomous path:
	 *   - Starting Position: Right
	 *   - Goal: Scale
	 *   - Ownership:
	 *     - Switch: Any
	 *     - Scale:  Right
	 */
	public RSCXR() {
		addParallel(new GripClose());
		
		addParallel(new RaiseLiftHalf(), 4);
		addSequential(new Forward(4.862), 3);

		addSequential(new LowGear());
		addSequential(new Turn(25), 2); // From 19
		
		addSequential(new RaiseLiftHigh(), 1.5);
		addSequential(new Forward(1.25), 1); // From 1.00
		addSequential(new IntakePushAuto()); // From IntakePushSlow()
		addSequential(new Delay(1));
		addSequential(new IntakeStop());
		
		addSequential(new Forward(-0.400), 0.4); // From -0.7 
		addParallel(new LiftZero(), 2);
		
		addSequential(new Turn(160), 1.5); // From 140
		addSequential(new HighGear());
		
		addParallel(new IntakePull());
		addSequential(new EnableVision());
		addSequential(new BoxPipeline());
		addParallel(new GripOpen());
		addSequential(new ApproachBox(), 1.5);
//		addSequential(new IntakeStop());
		addSequential(new GripClose());
		addSequential(new IntakePullSlow());
		addSequential(new Forward(-0.600), 0.5);
	}
}
