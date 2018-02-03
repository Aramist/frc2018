package org.usfirst.frc5472.TrackerTest.subsystems;

import org.usfirst.frc5472.TrackerTest.commands.LiftCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {
	private WPI_TalonSRX lifter = new WPI_TalonSRX(5);

	public void climb(double value) {
		lifter.set(value);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new LiftCommand());
	}
}