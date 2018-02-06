package org.usfirst.frc.team5472.robot.autonomous;

import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class LiftCommand extends Command {

	private boolean finished = false;
	private int position;
	private LiftSubsystem lift;

	public LiftCommand(int position) {
		this.position = position;
		requires(Robot.liftSubsystem);
	}

	@Override
	public void initialize() {
		this.lift = Robot.liftSubsystem;
	}

	@Override
	public void execute() {
		lift.setPosition(position);
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}
}
