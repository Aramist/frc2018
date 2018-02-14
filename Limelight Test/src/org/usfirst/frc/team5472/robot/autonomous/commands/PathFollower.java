package org.usfirst.frc.team5472.robot.autonomous.commands;

import java.util.ArrayList;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.autonomous.FileReader;

import edu.wpi.first.wpilibj.command.Command;

public class PathFollower extends Command {
	private boolean completed;

	private ArrayList<String> commands;
	private ArrayList<String> arguments;

	public PathFollower() {
		requires(Robot.driveSubsystem);
		commands = FileReader.commands;
		arguments = FileReader.arguments;
	}

	@Override
	public void execute() {
		for (int i = 0; i < commands.size(); i++) {
			String arg = arguments.get(i);
			double val = Double.parseDouble(arg); // Only works for values
													// without L, W, or V values
			if (commands.get(i).equals("F"))
				new Forward(val);
			if (commands.get(i).equals("T"))
				new Turn(val);
		}
		completed = true;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return completed;
	}

}
