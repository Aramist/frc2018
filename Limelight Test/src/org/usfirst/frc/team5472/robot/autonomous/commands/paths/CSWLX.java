package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.autonomous.commands.Delay;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftLow;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.GripOpen;
import org.usfirst.frc.team5472.robot.commands.IntakePush;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CSWLX extends CommandGroup{
	
	public CSWLX() {
		addParallel(new GripOpen());
		addSequential(new Forward(1.78 - Constants.ROBOT_LENGTH));
		addSequential(new Turn(-90));
		addParallel(new RaiseLiftLow());
		addSequential(new Forward(2.0));
		addSequential(new Turn(0));
		addSequential(new Forward(1.77));
		addSequential(new IntakePush());
		addSequential(new Delay(1.0));
		addSequential(new IntakeStop());
	}
	
}
