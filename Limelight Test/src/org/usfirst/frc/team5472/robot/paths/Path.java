package org.usfirst.frc.team5472.robot.paths;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Path {

	private static final double WHEELBASE_WIDTH = 0.76;
	private static final Trajectory.Config config = new Trajectory.Config(FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2, 60);
	
	private static java.nio.file.Path getWritablePath(String fileName){
		String filePath = "/home/lvuser/" + fileName + ".traj";
		return Paths.get(filePath);
	}
	
	private Trajectory generatedTrajectory;
	private File trajectoryFile;
	
	public Path(String name) {
		this.trajectoryFile = getWritablePath(name).toFile();
		if(trajectoryFile.exists()) {
			try {
				generatedTrajectory = Pathfinder.readFromFile(trajectoryFile);
			} catch (Exception e) {
				e.printStackTrace();
				generatedTrajectory = Pathfinder.generate(new Waypoint[] {}, config);
			}
		}
	}
	
	public Path(String name, Waypoint...waypoints) {
		this.generatedTrajectory = Pathfinder.generate( (Waypoint[]) Arrays.asList(waypoints).toArray(), config);
		this.trajectoryFile = getWritablePath(name).toFile();
		Pathfinder.writeToFile(trajectoryFile, generatedTrajectory);
	}
	
	public TankModifier toTankModifier() {
		return new TankModifier(generatedTrajectory).modify(WHEELBASE_WIDTH);
	}
}
