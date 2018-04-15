/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;


public class Robot extends TimedRobot {
	
	public static Joystick joystick;
	public static Drive drive;
	public static Autonomous auto;
	
	@Override
	public void robotInit() {
		joystick = new Joystick(0);
		
		drive = new Drive();
		auto = new Autonomous();
	}
	
	@Override
	public void disabledInit() {}
	
	@Override
	public void disabledPeriodic() {}
	
	@Override
	public void teleopInit() {}
	
	@Override
	public void teleopPeriodic() {}
	
	@Override
	public void autonomousInit() {}
	
	@Override
	public void autonomousPeriodic() {}
}
