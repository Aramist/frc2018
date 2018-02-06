package org.usfirst.frc.team5472.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PassAutoLine extends CommandGroup {

	// private Segment[] left;
	// private Segment[] right;

	public PassAutoLine() {
		// Waypoint[] points = {
		// new Waypoint(0, 0, 90),
		// new Waypoint(0, 1, 90)
		// };
		// Segment[] trajectory = Pathfinder.generate(points,
		// Constants.TRAJECTORY_CONFIG);
		// TankModifier modifier = new TankModifier(trajectory);
		// modifier.modify(Constants.ROBOT_WHEELBASE_WIDTH);
		// left = modifier.getLeft();
		// right = modifier.getRight();
		// addSequential(new FollowPath(left, right));
		addSequential(new LiftCommand(500));
	}
}
