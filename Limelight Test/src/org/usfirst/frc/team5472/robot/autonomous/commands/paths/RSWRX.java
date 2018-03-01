package org.usfirst.frc.team5472.robot.autonomous.commands.paths;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.autonomous.commands.Forward;
import org.usfirst.frc.team5472.robot.autonomous.commands.RaiseLiftLow;
import org.usfirst.frc.team5472.robot.autonomous.commands.Turn;
import org.usfirst.frc.team5472.robot.commands.GripClose;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RSWRX extends CommandGroup {

	public RSWRX() {
		addParallel(new GripClose());
		
		addParallel(new RaiseLiftLow(), 4);
		addSequential(new Forward(4.01));
		addSequential(new Turn(90));
		addSequential(new Forward(Constants.V_CONSTANT));
	}
}
