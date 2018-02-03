package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.commands.FollowPath;

import com.team5472.robot.pathfinder.from_c.Pathfinder;
import com.team5472.robot.pathfinder.from_c.Segment;
import com.team5472.robot.pathfinder.from_c.Waypoint;
import com.team5472.robot.pathfinder.from_c.modifiers.TankModifier;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PassAutoLine extends CommandGroup{
	
	private Segment[] left;
	private Segment[] right;
	
	public PassAutoLine() {
		Waypoint[] points = {
				new Waypoint(0, 0, 90),
				new Waypoint(0, 1, 90)
		};
		Segment[] trajectory = Pathfinder.generate(points, Constants.TRAJECTORY_CONFIG);
		TankModifier modifier = new TankModifier(trajectory);
		modifier.modify(Constants.ROBOT_WHEELBASE_WIDTH);
		left = modifier.getLeft();
		right = modifier.getRight();
		addSequential(new FollowPath(left, right));
	}
}
