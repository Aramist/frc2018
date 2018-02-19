package org.usfirst.frc.team5472.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.DataProvider;
import org.usfirst.frc.team5472.robot.commands.LiftDefault;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSubsystem extends Subsystem implements DataProvider{

	private TalonSRX liftMotor;
	private double setpoint;
	
	public LiftSubsystem() {
		liftMotor = new TalonSRX(Constants.LIFT_TALON_CAN);
		liftMotor.setNeutralMode(NeutralMode.Coast);
		liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		liftMotor.setInverted(false);
		liftMotor.setSensorPhase(true);
		
		liftMotor.configNominalOutputForward(0, 10);
		liftMotor.configNominalOutputReverse(0, 10);
		liftMotor.configPeakOutputForward(0.8, 10);
		liftMotor.configPeakOutputReverse(-0.2, 10);
		
		liftMotor.configForwardSoftLimitThreshold(33500, 10);
		liftMotor.configForwardSoftLimitEnable(true, 10);
		liftMotor.configReverseSoftLimitThreshold(0, 10);
		liftMotor.configReverseSoftLimitEnable(true, 10);
	
		liftMotor.config_kP(0, Constants.LIFT_PIDF_D, 10);
		liftMotor.config_kI(0, Constants.LIFT_PIDF_I, 10);
		liftMotor.config_kD(0, Constants.LIFT_PIDF_D, 10);
		liftMotor.config_kF(0, Constants.LIFT_PIDF_F, 10);
		liftMotor.config_IntegralZone(0, Constants.LIFT_PIDF_INTZONE, 10);
	}
	
	public double getSetpoint() {
		return setpoint;
	}

	public void setSetpoint(double value) {
		setpoint = value;
	}
	
	public void resetEncoder() {
		liftMotor.setSelectedSensorPosition(0, 0, 0);
	}

	public double getPosition() {
		return liftMotor.getSelectedSensorPosition(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LiftDefault());
	}
	
	public HashMap<String, double[]> getData(){
		HashMap<String, double[]> toReturn = new HashMap<>();
		toReturn.put("Lift Position", new double[] {getPosition()});
		toReturn.put("Lift Current", new double[] {liftMotor.getOutputCurrent()});
		toReturn.put("Output Percent", new double[] {liftMotor.getMotorOutputPercent()});
		return toReturn;
	}

}
