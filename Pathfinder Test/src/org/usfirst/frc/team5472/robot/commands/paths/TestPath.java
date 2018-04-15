package org.usfirst.frc.team5472.robot.commands.paths;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.commands.FollowPath;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class TestPath extends CommandGroup{
	
	public TestPath() {
		Waypoint[] points = new Waypoint[] {
				new Waypoint(-1, -0.5, Pathfinder.d2r(-45)),
				new Waypoint(-1, -1, 0),
				new Waypoint(0, 0, 0)
		};
		addSequential(new FollowPath(Robot.auto.makePath(points)));
	}
	
}
