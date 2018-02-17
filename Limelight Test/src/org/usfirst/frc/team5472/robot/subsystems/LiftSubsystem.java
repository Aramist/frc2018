package org.usfirst.frc.team5472.robot.subsystems;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.commands.LiftDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem {

	private TalonSRX liftMotor;
	
	private PIDController positionController;
	private PIDSource positionSource;
	private PIDOutput positionOutput;
	
	public LiftSubsystem() {
		liftMotor = new TalonSRX(Constants.LIFT_TALON_CAN);
		liftMotor.setNeutralMode(NeutralMode.Brake);
		liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
		liftMotor.setSensorPhase(true);
		liftMotor.configPeakOutputForward(0.8, 10);
		liftMotor.configPeakOutputReverse(-0.2, 10);
		liftMotor.configForwardSoftLimitThreshold(33500, 10);
		liftMotor.configForwardSoftLimitEnable(true, 10);
		liftMotor.configReverseSoftLimitThreshold(0, 10);
		liftMotor.configReverseSoftLimitEnable(true, 10);
		
		positionOutput = (double output) -> {
			setPercent(output);
		};
		
		positionSource = new PIDSource() {
			public double pidGet() {
				return getPosition();
			}
			public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}
			public void setPIDSourceType(PIDSourceType t) {}
		};
		
		positionController = new PIDController(Constants.LIFT_PIDF_P, Constants.LIFT_PIDF_I, Constants.LIFT_PIDF_D, Constants.LIFT_PIDF_F, positionSource, positionOutput);
		positionController.setSetpoint(0.0);
		positionController.setInputRange(0, 34000);
		positionController.setOutputRange(0.0, 1.0);
		positionController.setAbsoluteTolerance(50);
	}

	public void setPercent(double percent) {
		liftMotor.set(ControlMode.PercentOutput, percent); 
	}

	public void stopLift() {
		setPercent(0);
	}

	public void resetEncoder() {
		liftMotor.setSelectedSensorPosition(0, 0, 0);
	}

	public double getPosition() {
		return liftMotor.getSelectedSensorPosition(0);
	}
	
	public boolean onTarget() {
		return positionController.onTarget();
	}

	public void setSetpoint(double i) {
		positionController.setSetpoint(i);
		positionController.enable();
	}
	
	public void addSetpoint(double d) {
		setSetpoint(getSetpoint() + d);
	}
	
	public double getSetpoint() {
		return positionController.getSetpoint();
	}
	
	public double getError() {
		return positionController.getError();
	}
	
	public void enable() {
		positionController.enable();
	}
	
	public void disable() {
		positionController.disable();
	}
	
	public void enableBrake() {
		liftMotor.setNeutralMode(NeutralMode.Brake);
	}
	
	public void enableCoast() {
		liftMotor.setNeutralMode(NeutralMode.Coast);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LiftDefault());
	}

}
