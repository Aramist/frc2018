package org.usfirst.frc5472.TrackerTest.subsystems;

import org.usfirst.frc5472.TrackerTest.commands.FeedCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
	private DoubleSolenoid grab = new DoubleSolenoid(1, 2);
	private WPI_TalonSRX left = new WPI_TalonSRX(7);
	private WPI_TalonSRX right = new WPI_TalonSRX(8);
	public static String solenoidState = "Closed";

	public void changeState() {
		Value current = grab.get();
		if (current == Value.kForward)
			grab.set(Value.kReverse);
		else
			grab.set(Value.kForward);
		if (current == Value.kReverse)
			solenoidState = "Closed";
		else
			solenoidState = "Open";
	}

	public void feedIn() {
		left.set(0.6);
		right.set(-0.6);
	}

	public void feedOut() {
		left.set(-0.6);
		right.set(0.6);
	}

	public void stop() {
		left.set(0);
		right.set(0);
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new FeedCommand());
	}
}
