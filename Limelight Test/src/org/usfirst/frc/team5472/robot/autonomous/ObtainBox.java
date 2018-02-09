package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.commands.BoxPipeline;
import org.usfirst.frc.team5472.robot.commands.EnableVision;
import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.GripOpen;
import org.usfirst.frc.team5472.robot.commands.IntakePull;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ObtainBox extends CommandGroup
{
	/*
	 * Goes through the motions of obtaining possession of a power cube
	 * 
	 */
	
	public ObtainBox() {
		addSequential(new EnableVision());
		addSequential(new BoxPipeline());
		addParallel(new GripOpen());
		addSequential(new LookForBox());
		addSequential(new ApproachBox());
		addSequential(new IntakePull());
		addSequential(new GripClose());
		addSequential(new Delay(0.5));
		addSequential(new IntakeStop());
	}
	
}
