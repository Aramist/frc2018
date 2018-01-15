package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Constants.AutonomousProgram;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	private SendableChooser<AutonomousProgram> autoSelector = new SendableChooser<>();
	private String gameSpecificData = "";
	
	public Autonomous() {
		autoSelector.addDefault(AutonomousProgram.DEFAULT.getName(), AutonomousProgram.DEFAULT);
		autoSelector.addObject(AutonomousProgram.LIMELIGHT.getName(), AutonomousProgram.LIMELIGHT);
		autoSelector.addObject(AutonomousProgram.JEVOIS.getName(), AutonomousProgram.JEVOIS);
		SmartDashboard.putData(Constants.AUTONOMOUS_CHOOSER_NAME, autoSelector);
	}
	
	public void start() {
		switch(autoSelector.getSelected()){
		case DEFAULT:
			break;
		case LIMELIGHT:
			break;
		case JEVOIS:
			break;
		}
	}
	
	public void checkGameSpecificData() {
		if(!this.gameSpecificData.equals(""))
			return;
		String gameSpecificData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameSpecificData != null)
			this.gameSpecificData = gameSpecificData;
	}
}
