package org.usfirst.frc.team5472.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.DataProvider;
import org.usfirst.frc.team5472.robot.LimitSwitch;
import org.usfirst.frc.team5472.robot.commands.LiftDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSubsystem extends Subsystem implements DataProvider{

	private TalonSRX liftUpMotor1;
	private TalonSRX liftUpMotor2;
	
	private PIDController positionController;
	private PIDSource positionSource;
	private PIDOutput positionOutput;
	
	public LiftSubsystem() {
		
		liftUpMotor1 = new TalonSRX(Constants.LIFT_TALON_CAN1);
		liftUpMotor1.setNeutralMode(NeutralMode.Brake);
		liftUpMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		liftUpMotor1.setInverted(true); //Inverted from practice
		liftUpMotor1.setSensorPhase(false); //Inverted from practice
		liftUpMotor1.configPeakOutputForward(1.0, 10);
		liftUpMotor1.configPeakOutputReverse(-0.3, 10);
		liftUpMotor1.configForwardSoftLimitThreshold(35000, 10);
		liftUpMotor1.configForwardSoftLimitEnable(true, 10);
		liftUpMotor1.configReverseSoftLimitThreshold(0, 10);
		liftUpMotor1.configReverseSoftLimitEnable(true, 10);
		liftUpMotor2 = new TalonSRX(Constants.LIFT_TALON_CAN2);
		liftUpMotor2.setNeutralMode(NeutralMode.Brake);
		liftUpMotor2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		liftUpMotor2.setInverted(false); //Inverted from practice
		liftUpMotor2.setSensorPhase(true); //Inverted from practice
		liftUpMotor2.configPeakOutputForward(1.0, 10);
		liftUpMotor2.configPeakOutputReverse(-0.3, 10);
		liftUpMotor2.configForwardSoftLimitThreshold(35000, 10);
		liftUpMotor2.configForwardSoftLimitEnable(true, 10);
		liftUpMotor2.configReverseSoftLimitThreshold(0, 10);
		liftUpMotor2.configReverseSoftLimitEnable(true, 10);
		
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
		positionController.setOutputRange(-0.0, 1.0);
		positionController.setAbsoluteTolerance(50);
	}

	public void setPercent(double percent) {
		liftUpMotor1.set(ControlMode.PercentOutput, percent);
		liftUpMotor2.set(ControlMode.PercentOutput, percent);
		SmartDashboard.putNumber("Motar Porcents", percent);
		SmartDashboard.putNumber("Motor Current 1", liftUpMotor1.getOutputCurrent());
		SmartDashboard.putNumber("Motor Current 2", liftUpMotor2.getOutputCurrent());
	}

	public void hold() {
		setPercent(0.1);
	}

	public void resetEncoder() {
		liftUpMotor1.setSelectedSensorPosition(0, 0, 0);
		liftUpMotor2.setSelectedSensorPosition(0, 0, 0);
	}

	public double getPosition() {
		return (liftUpMotor1.getSelectedSensorPosition(0) + liftUpMotor2.getSelectedSensorPosition(0))/2;
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
		liftUpMotor1.setNeutralMode(NeutralMode.Brake);
	}
	
	public void enableCoast() {
		liftUpMotor1.setNeutralMode(NeutralMode.Coast);
		liftUpMotor2.setNeutralMode(NeutralMode.Coast);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LiftDefault());
	}
	
	public HashMap<String, double[]> getData(){
		HashMap<String, double[]> toReturn = new HashMap<>();
		toReturn.put("Lift Position", new double[] {getPosition()});
		toReturn.put("Lift Current 1", new double[] {liftUpMotor1.getOutputCurrent()});
		toReturn.put("Lift Output Percent 1", new double[] {liftUpMotor1.getMotorOutputPercent()});
		toReturn.put("Lift Position", new double[] {getPosition()});
		toReturn.put("Lift Current 2", new double[] {liftUpMotor2.getOutputCurrent()});
		toReturn.put("Lift Output Percent 2", new double[] {liftUpMotor2.getMotorOutputPercent()});
		return toReturn;
	}
	
	

}
