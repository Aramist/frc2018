package org.usfirst.frc.team5472.robot;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Autonomous {
	
	public Autonomous() {
		// idk
	}
	
	public void start() {
		//Run test path
		
	}
	
	public Trajectory[] makePath(Waypoint[] points) {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.PATH_DT, 2.00, 2.00, 60.0);
		
		Trajectory generalPath = Pathfinder.generate(points,  config);
		TankModifier mod = new TankModifier(generalPath).modify(Constants.ROBOT_WHEELBASE_WIDTH);
		
		return new Trajectory[] {mod.getLeftTrajectory(), mod.getRightTrajectory()};
	}
	
	public Trajectory[] makePath(Waypoint[] points, double maxVelocity, double maxAccel) {
		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.PATH_DT, maxVelocity, maxAccel, 60.0);
		
		Trajectory generalPath = Pathfinder.generate(points,  config);
		TankModifier mod = new TankModifier(generalPath).modify(Constants.ROBOT_WHEELBASE_WIDTH);
		
		return new Trajectory[] {mod.getLeftTrajectory(), mod.getRightTrajectory()};
	}
}
