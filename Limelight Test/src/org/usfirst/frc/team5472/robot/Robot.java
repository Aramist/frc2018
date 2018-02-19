/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.autonomous.Autonomous;
import org.usfirst.frc.team5472.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.LedSubsystem;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {

	private Autonomous auto;

	public static Controls controls;
	public static DriveSubsystem drive;
	public static IntakeSubsystem intake;
	public static LiftSubsystem lift;
	public static LedSubsystem led;
	public static Limelight limelight;
	private static DataLogger logger;
	
	@Override
	public void robotInit() {
		drive = new DriveSubsystem();
		intake = new IntakeSubsystem();
		lift = new LiftSubsystem();
		led = new LedSubsystem();
		limelight = new Limelight();
		auto = new Autonomous();
		controls = new Controls();
		logger = new DataLogger();
	}

	@Override
	public void disabledInit() {
		auto.end();
		drive.resetEncoders();
		drive.resetHeading();
		lift.resetEncoder();
		logger.end();
	}

	@Override
	public void disabledPeriodic() {
		if (auto != null)
			auto.checkGameSpecificData();
	}

	@Override
	public void autonomousInit() {
		drive.resetEncoders();
		drive.resetHeading();
		lift.resetEncoder();
		logger.start();
		auto.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		logger.appendData(drive);
		logger.appendData(lift);
		logger.appendData(intake);
		logger.appendData(limelight);
		logger.appendData(led);
	}

	@Override
	public void teleopInit() {
		limelight.setLed(false);
		drive.resetEncoders();
		drive.resetHeading();
		lift.resetEncoder();
		drive.highGear();
		logger.start();
		auto.end();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		logger.appendData(drive);
		logger.appendData(lift);
		logger.appendData(intake);
		logger.appendData(limelight);
		logger.appendData(led);
	}
	
	@Override
	public void testInit() {
	}
	
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}
}