package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.autonomous.commands.PassAutoLine;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {

	private static enum StartingPosition{
		CENTER("center"), LEFT("left"), RIGHT("right");
		
		private String name;
		
		private StartingPosition(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	private static enum Plan{
		SWITCH("Switch only"), SCALE("Scale only"), BOTH("Switch and Scale");
		
		private String name;
		
		private Plan(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	private SendableChooser<StartingPosition> starting = new SendableChooser<>();
	private SendableChooser<Plan> plan = new SendableChooser<>();
	
	private Command selectedCommand = null;
	private String gameSpecificData = "";

	public Autonomous() {
		starting.addDefault(StartingPosition.CENTER.toString(), StartingPosition.CENTER);
		starting.addObject(StartingPosition.LEFT.toString(), StartingPosition.LEFT);
		starting.addObject(StartingPosition.RIGHT.toString(), StartingPosition.RIGHT);
		
		plan.addDefault(Plan.BOTH.toString(), Plan.BOTH);
		plan.addObject(Plan.SWITCH.toString(), Plan.SWITCH);
		plan.addObject(Plan.SCALE.toString(), Plan.SCALE);
		
		SmartDashboard.putData(starting);
		SmartDashboard.putData(plan);
	}

	public void start() {
		selectedCommand = new PassAutoLine();
		selectedCommand.start();
//		StartingPosition startPos = starting.getSelected();
//		Plan thePlan = plan.getSelected();
//		switch(startPos) {
//		case CENTER:
//			startingCenter(thePlan);
//		case LEFT:
//			startingLeft(thePlan);
//		case RIGHT:
//			startingRight(thePlan);
//		}
	}
	
	public void startingCenter(Plan thePlan) {}
	public void startingLeft(Plan thePlan) {}
	public void startingRight(Plan thePlan) {}

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
}
