package org.usfirst.frc.team5472.robot.commands;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.Controls;
import org.usfirst.frc.team5472.robot.Robot;
import org.usfirst.frc.team5472.robot.subsystems.LiftSubsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftDefault extends Command {

	private LiftSubsystem lift;
	private Controls controls = Robot.controls;
	
	public LiftDefault() {
		requires(Robot.lift);
	}

	@Override
	public void initialize() {
		lift = Robot.lift;
		lift.enableBrake();
	}

	@Override
	public void execute() {
		if(DriverStation.getInstance().isAutonomous())
			return;
		
		
		double x = controls.getLiftUpAxis();
		double y = controls.getLiftDownAxis() * Constants.LIFT_REVERSE_OUTPUT_LIMIT;
		
			
		SmartDashboard.putNumber("Down Lift",controls.getLiftDownAxis() * Constants.LIFT_REVERSE_OUTPUT_LIMIT);
		if(Math.abs(y) > 0.1)
			lift.enableCoast();
		else
			lift.enableBrake();
		lift.setPercent(x + y);
	}
		
	

	@Override
	protected boolean isFinished() {
		return false;
	}
}