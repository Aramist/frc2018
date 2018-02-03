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
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
	
	/*
	 * Limelight experiment:
	 * Distance of 1.397 meters, tx = 0.18, ty = 5.4: ta = 1.82
	 * Distance of 0.787 meters, tv = 0
	 */
	
	private Autonomous auto;
//	private JeVois jevois;
	
	public static Controls controls;
	public static DriveSubsystem driveSubsystem;
	public static IntakeSubsystem intakeSubsystem;
	public static LiftSubsystem liftSubsystem;
	public static Limelight limelight;

	@Override
	public void robotInit() {
		controls = new Controls();
		auto = new Autonomous();
		driveSubsystem = new DriveSubsystem();
		intakeSubsystem = new IntakeSubsystem();
		liftSubsystem = new LiftSubsystem();
		limelight = new Limelight();
//		jevois = new JeVois();
	}
	
	@Override
	public void disabledInit() {
		auto.end();
	}
	
	@Override
	public void disabledPeriodic() {
		if(auto != null)
			auto.checkGameSpecificData();
	}

	@Override
	public void autonomousInit() {
		driveSubsystem.resetEncoders();
		auto.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		limelight.reportDebugInformation();
	}

	@Override
	public void teleopInit() {
		limelight.setLed(false);
		driveSubsystem.resetEncoders();
		driveSubsystem.resetHeading();
		liftSubsystem.resetEncoder();
		auto.end();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.err.println(liftSubsystem.getPosition());
	}
	
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}
}