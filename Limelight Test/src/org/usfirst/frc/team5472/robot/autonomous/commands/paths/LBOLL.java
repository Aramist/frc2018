package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.autonomous.commands.ApproachBox;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.LiftZero;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.BoxPipeline;
import org.usfirst.frc.team5472.robot.commands.EnableVision;
import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.HighGear;
import org.usfirst.frc.team5472.robot.commands.IntakePull;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LBOLL extends CommandGroup{
	
	public LBOLL() {
		addSequential(new LSCXL());
		
		addSequential(new Turn(-140), 3);
		addSequential(new HighGear());
		
		addSequential(new LiftZero(), 3);
		addParallel(new IntakePull());
		addSequential(new EnableVision());
		addSequential(new BoxPipeline());
		addSequential(new ApproachBox(), 3);
		addSequential(new IntakeStop());
		addSequential(new GripClose());
		addSequential(new Forward(-0.600));
	}
	
}
