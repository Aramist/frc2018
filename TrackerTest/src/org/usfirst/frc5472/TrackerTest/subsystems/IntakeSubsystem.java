package org.usfirst.frc5472.TrackerTest.subsystems;

import org.usfirst.frc5472.TrackerTest.commands.FeedCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
	private DoubleSolenoid grab = new DoubleSolenoid(0, 1);
	private VictorSP left = new VictorSP(1);
	private VictorSP right = new VictorSP(2);
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
