package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.autonomous.commands.Delay;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.LiftZero;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftHalf;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.IntakePushAuto;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RBORR extends CommandGroup {
	
	public RBORR() {
		addSequential(new RSCXR());
		
		addParallel(new RaiseLiftHalf());
		addSequential(new Delay(1));
		addSequential(new IntakeStop());
		addSequential(new Turn(135), 1);
		addSequential(new Forward(0.7), 1);
		addSequential(new IntakePushAuto());
		addSequential(new Delay(1));
		addSequential(new IntakeStop());
		addSequential(new Forward(-0.6), 1);
		addSequential(new LiftZero());
	}
}
