package org.usfirst.frc.team5472.robot.autonomous;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Constants.AutonomousProgram;

import com.team5472.robot.pathfinder.from_c.IO;
import com.team5472.robot.pathfinder.from_c.Pathfinder;
import com.team5472.robot.pathfinder.from_c.Segment;
import com.team5472.robot.pathfinder.from_c.Trajectory.Config;
import com.team5472.robot.pathfinder.from_c.Waypoint;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {

	private SendableChooser<AutonomousProgram> autoSelector = new SendableChooser<>();
	private Command selectedCommand = null;
	private String gameSpecificData = "";

	public Autonomous() {
		autoSelector.addDefault(AutonomousProgram.DEFAULT.getName(), AutonomousProgram.DEFAULT);
		autoSelector.addObject(AutonomousProgram.LIMELIGHT.getName(), AutonomousProgram.LIMELIGHT);
		autoSelector.addObject(AutonomousProgram.JEVOIS.getName(), AutonomousProgram.JEVOIS);
		SmartDashboard.putData(Constants.AUTONOMOUS_CHOOSER_NAME, autoSelector);
	}

	public void start() {
		switch (autoSelector.getSelected()) {
			case DEFAULT:
				selectedCommand = new EatBox();
				selectedCommand.start();
			case LIMELIGHT:
				break;
			case JEVOIS:
				break;
		}
	}

	public void end() {
		if (selectedCommand != null)
			selectedCommand.cancel();
	}

	public void checkGameSpecificData() {
		if (!this.gameSpecificData.equals(""))
			return;
		String gameSpecificData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameSpecificData != null)
			this.gameSpecificData = gameSpecificData;
	}

	public Segment[] getPathFromFile(String pathName) throws Exception {
		Path filePath = Paths.get("/home/lvuser/trajectories/" + pathName + ".traj");
		if (!filePath.toFile().exists()) {
			throw new Exception("File does not exist"); // TODO: use a less
														// generic exception
														// class
		}
		return IO.deserialize(filePath);
	}

	public void writePathToFile(String pathName, Segment[] trajectory) {
		Path filePath = Paths.get("/home/lvuser/trajectories/" + pathName + ".traj");
		IO.serialize(filePath, trajectory);
	}

	public Segment[] generatePath(String pathName, Waypoint[] waypoints, Config config) {
		Segment[] trajectory = Pathfinder.generate(waypoints, config);
		writePathToFile(pathName, trajectory);
		return trajectory;
	}
}
