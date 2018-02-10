package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.commands.LiftDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {

	private TalonSRX liftMotor;

	public LiftSubsystem() {
		liftMotor = new TalonSRX(Constants.LIFT_TALON_CAN);
		liftMotor.setNeutralMode(NeutralMode.Brake);
		liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		liftMotor.setSensorPhase(true);
		liftMotor.config_kP(0, Constants.LIFT_PIDF_P, 10);
		liftMotor.config_kI(0, Constants.LIFT_PIDF_I, 10);
		liftMotor.config_kD(0, Constants.LIFT_PIDF_D, 10);
		liftMotor.config_kF(0, Constants.LIFT_PIDF_F, 10);
		liftMotor.config_IntegralZone(0, Constants.LIFT_PIDF_INTZONE, 10);
		liftMotor.configForwardSoftLimitThreshold(33500, 10);
		liftMotor.configForwardSoftLimitEnable(true, 10);
		liftMotor.configReverseSoftLimitThreshold(0, 10);
		liftMotor.configReverseSoftLimitEnable(true, 10);
	}

	public void setLiftPercent(double percent) {
		liftMotor.set(ControlMode.PercentOutput, percent);
	}

	public void stopLift() {
		setLiftPercent(0);
	}

	public void resetEncoder() {
		liftMotor.setSelectedSensorPosition(0, 0, 10);
	}

	public double getPosition() {
		return liftMotor.getSelectedSensorPosition(0);
	}

	public void setPosition(double position) {
		liftMotor.set(ControlMode.Position, position);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LiftDefault());
	}

}
