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
	public static DriveSubsystem drive;
	public static IntakeSubsystem intake;
	public static LiftSubsystem lift;
	public static Limelight limelight;

	@Override
	public void robotInit() {
		drive = new DriveSubsystem();
		intake = new IntakeSubsystem();
		lift = new LiftSubsystem();
		limelight = new Limelight();
		auto = new Autonomous();
		controls = new Controls();
	}

	@Override
	public void disabledInit() {
		auto.end();
		drive.resetEncoders();
		drive.resetHeading();
		lift.resetEncoder();
		lift.setSetpoint(0);
		lift.disable();
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
		auto.start();
		lift.setSetpoint(0);
		lift.enable();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		limelight.setLed(false);
		drive.resetEncoders();
		drive.resetHeading();
		lift.resetEncoder();
		drive.highGear();
		auto.end();
		lift.setSetpoint(0);
		lift.disable();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void testInit() {
	}
	
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}
}