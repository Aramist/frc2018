package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.autonomous.commands.Delay;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftLow;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.IntakePushSlow;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CSWLX extends CommandGroup{
	
	public CSWLX() {
		addParallel(new GripClose());
		addSequential(new Forward(1.50 - Constants.ROBOT_LENGTH));
		addSequential(new Turn(45), 1);
		addParallel(new RaiseLiftLow());
		addSequential(new Forward(1.95));
		addSequential(new Turn(10), 1);
		addSequential(new Forward(0.5), 1);
		addSequential(new IntakePushSlow());
		addSequential(new Delay(1.0));
		addSequential(new IntakeStop());
		addSequential(new Forward(-0.6));
	}
	
}
