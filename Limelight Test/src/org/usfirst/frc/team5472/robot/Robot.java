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

	private Autonomous auto;

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
	}

	@Override
	public void disabledInit() {
		auto.end();
		driveSubsystem.resetEncoders();
		driveSubsystem.resetHeading();
		liftSubsystem.resetEncoder();
	}

	@Override
	public void disabledPeriodic() {
		if (auto != null)
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

		System.out.println("Left: " + driveSubsystem.getLeftRaw());
		System.out.println("Right: " + driveSubsystem.getRightRaw());
	}

	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}
}