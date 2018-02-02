package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Limelight;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ApproachBox extends Command{
	
	/*
	 * Approaches a box given the box is within the camera's fov
	 */
	
	private boolean finished = false;
	private Limelight limelight;
	private DriveSubsystem drive;
	private double targetStamp;
	private double targetLost;
	
	public ApproachBox() {
		requires(Robot.driveSubsystem);
	}
	
	@Override
	public void initialize() {
		drive = Robot.driveSubsystem;
		limelight = Robot.limelight;
		drive.setControlMode(ControlMode.PercentOutput);
		drive.resetEncoders();
		drive.drive(0,  0);
		
		targetStamp = 0.0;
		targetLost = 0.0;
	}
	
	@Override
	public void execute() {
		if(!limelight.isConnected() && this.timeSinceInitialized() > 1.0) {
			finished = true;
			System.err.println("No connection to Limelight Camera");
			return;
			//Limelight is off and more than a second has passed. Give up.
		} else if ((this.timeSinceInitialized() > 1.0) && (targetStamp == 0.0) && (Timer.getFPGATimestamp() - targetLost > 0.2)) {
			finished = true;
			System.err.println("time" + this.timeSinceInitialized() + " target " + limelight.targetExists());
			return;
			//No target and more than a second has passed. Give up.
		}
		if(!limelight.targetExists()) {
			targetStamp = 0.0;
			if(targetLost == 0.0)
				targetLost = Timer.getFPGATimestamp();
			System.err.println("Target Lost");
			return;
		}
		if(limelight.targetExists()) {
			targetLost = 0.0;
			if(targetStamp == 0.0)
				targetStamp = Timer.getFPGATimestamp();
			if(limelight.getTargetArea() > Constants.LIMELIGHT_TARGET_AREA_THRESHOLD) {
				finished = true;
			}
			double error = limelight.getHorizontalAngle();
			double pGain = error * Constants.LIMELIGHT_APPROACH_BOX_KP;
			drive.drive(0.4 + pGain, 0.4 - pGain);
		}
	}
	
	@Override
	public void end() {
		Timer.delay(0.1);
		drive.drive(0.0, 0.0);
	}
	
	protected boolean isFinished() {
		return finished;
	}
}
