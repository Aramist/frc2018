package org.usfirst.frc.team5472.robot.autonomous.commands;

import org.usfirst.frc.team5472.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * A Command used to report the state of the intake's limit switch when it is triggered.
 */
public class ReportIntakeLimit extends Command{
	
	private boolean finished;
	
	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Command#initialize()
	 */
	@Override
	public void initialize() {
		finished = false;
	}
	
	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Command#execute()
	 */
	@Override
	public void execute() {
		SmartDashboard.putBoolean("Intake Limit Switch", Robot.controls.intakeLimit.get());
		finished = true;
	}
	
	/* (non-Javadoc)
	 * @see edu.wpi.first.wpilibj.command.Command#isFinished()
	 */
	@Override
	protected boolean isFinished() {
		return finished;
	}
	
}
