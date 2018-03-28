package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.autonomous.commands.ApproachBox;
import org.usfirst.frc.team5472.robot.autonomous.commands.Delay;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.LiftZero;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftLow;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.BoxPipeline;
import org.usfirst.frc.team5472.robot.commands.EnableVision;
import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.GripOpen;
import org.usfirst.frc.team5472.robot.commands.IntakePull;
import org.usfirst.frc.team5472.robot.commands.IntakePullAuto;
import org.usfirst.frc.team5472.robot.commands.IntakePushAuto;
import org.usfirst.frc.team5472.robot.commands.IntakePushSlow;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CSWRX extends CommandGroup{
	
	public CSWRX() {
		addParallel(new GripClose());
		addSequential(new Forward(1.50 - Constants.ROBOT_LENGTH));
		addSequential(new Turn(-45), 1);
		addParallel(new RaiseLiftLow());
		addSequential(new Forward(1.65));
		addSequential(new Turn(-10), 1);
		addSequential(new Forward(0.5), 1);
		addSequential(new IntakePushSlow());
		addSequential(new Delay(1.0));
		addSequential(new IntakeStop());
		addSequential(new Forward(-1.6), 3);
		
		addSequential(new Turn(35), 2);
		addSequential(new LiftZero(), 3);
		addParallel(new GripOpen());
		addParallel(new IntakePull());
		addSequential(new EnableVision());
		addSequential(new BoxPipeline());
		addSequential(new ApproachBox(), 5);
		addSequential(new GripClose());
		addSequential(new IntakePullAuto());
		addSequential(new Forward(-0.8));
		addSequential(new IntakeStop());
		
		addSequential(new Turn(0), 2);
		addSequential(new Forward(0.8), 1);
		addSequential(new RaiseLiftLow(), 3);
		addSequential(new IntakePushAuto());
		addSequential(new Delay(1));
		addSequential(new IntakeStop());
	}
	
}
