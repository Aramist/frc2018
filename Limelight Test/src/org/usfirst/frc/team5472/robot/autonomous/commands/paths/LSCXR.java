package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.autonomous.commands.Delay;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftHigh;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftLow;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.GripClose;
import org.usfirst.frc.team5472.robot.commands.IntakePush;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LSCXR extends CommandGroup {

	public LSCXR() {
		addParallel(new GripClose());
		
		addSequential(new Forward(5.81 - Constants.ROBOT_LENGTH));
		addSequential(new Turn(-90));
		addParallel(new RaiseLiftLow(), 3);
		addSequential(new Forward(3.90 + Constants.ROBOT_WIDTH + 2 * Constants.V_CONSTANT));
		addSequential(new Turn(-180));
		addParallel(new RaiseLiftHigh(), 3);
		addSequential(new Forward(1.78 + Constants.ROBOT_LENGTH));
		addSequential(new IntakePush());
		addSequential(new Delay(1));
		addSequential(new IntakeStop());
	}
}
