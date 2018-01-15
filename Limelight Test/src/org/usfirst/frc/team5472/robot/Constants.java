package org.usfirst.frc.team5472.robot;

public class Constants {
	public static final String AUTONOMOUS_CHOOSER_NAME = "Select an Autonomous Program: ";
	
	public static enum AutonomousProgram{

		DEFAULT("Drive Straight"), LIMELIGHT("Find the Switch (Limelight Vision)"), JEVOIS("Find the Switch (JeVois Vision)");
		
		private String title;
		private AutonomousProgram(String title) {
			this.title = title;
		}
		
		public String toString() {
			return this.title;
		}
		
		public String getName() {
			return this.title;
		}
		
	}
	
}
