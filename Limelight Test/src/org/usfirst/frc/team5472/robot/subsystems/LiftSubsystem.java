package org.usfirst.frc.team5472.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team5472.robot.Constants;
import org.usfirst.frc.team5472.robot.DataProvider;
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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSubsystem extends Subsystem implements DataProvider{

	private TalonSRX leftLiftMotor;
	private TalonSRX rightLiftMotor;
	
	private PIDController positionController;
	private PIDSource positionSource;
	private PIDOutput positionOutput;
	
	public LiftSubsystem() {
		
		leftLiftMotor = new TalonSRX(Constants.LIFT_TALON_CAN_LEFT);
		leftLiftMotor.setNeutralMode(NeutralMode.Brake);
		leftLiftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		leftLiftMotor.setInverted(true);
		leftLiftMotor.setSensorPhase(true);
		leftLiftMotor.configPeakOutputForward(1.0, 10);
		leftLiftMotor.configPeakOutputReverse(-0.5, 10);
		leftLiftMotor.configForwardSoftLimitThreshold(35000, 10);
		leftLiftMotor.configForwardSoftLimitEnable(true, 10);
		leftLiftMotor.configReverseSoftLimitThreshold(0, 10);
		leftLiftMotor.configReverseSoftLimitEnable(true, 10);
		
		rightLiftMotor = new TalonSRX(Constants.LIFT_TALON_CAN_RIGHT);
		rightLiftMotor.setNeutralMode(NeutralMode.Brake);
		rightLiftMotor.setInverted(false);
		rightLiftMotor.configPeakOutputForward(1.0, 10);
		leftLiftMotor.configPeakOutputReverse(-0.5, 10);
		rightLiftMotor.configPeakOutputReverse(Constants.LIFT_REVERSE_OUTPUT_LIMIT, 10);
		
		
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
		leftLiftMotor.set(ControlMode.PercentOutput, percent);
		rightLiftMotor.set(ControlMode.PercentOutput, percent);
		SmartDashboard.putNumber("Motor Percent Output", percent);
		SmartDashboard.putNumber("Left Lift Motor Current", leftLiftMotor.getOutputCurrent());
		SmartDashboard.putNumber("Right Lift Motor Current", rightLiftMotor.getOutputCurrent());
	}
	
	public double getPercentOutput() {
		return leftLiftMotor.getMotorOutputPercent();
	}

	public void hold() {
		setPercent(0.1);
	}

	public void resetEncoder() {
		leftLiftMotor.setSelectedSensorPosition(0, 0, 0);
	}

	public double getPosition() {
		return leftLiftMotor.getSelectedSensorPosition(0);
	}
	
	public boolean onTarget() {
		return positionController.onTarget();
	}

	public void setSetpoint(double i) {
		positionController.setSetpoint(i);
		
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
	
	public void enableClosedLoop() {
		positionController.enable();
	}
	
	public void disableClosedLoop() {
		positionController.disable();
	}
	
	public void enableBrake() {
		leftLiftMotor.setNeutralMode(NeutralMode.Brake);
		rightLiftMotor.setNeutralMode(NeutralMode.Brake);
	}
	
	public void enableCoast() {
		leftLiftMotor.setNeutralMode(NeutralMode.Coast);
		rightLiftMotor.setNeutralMode(NeutralMode.Coast);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new LiftDefault());
	}
	
	public HashMap<String, double[]> getData(){
		HashMap<String, double[]> toReturn = new HashMap<>();
		toReturn.put("Lift Position", new double[] {getPosition()});
		toReturn.put("Lift Current 1", new double[] {leftLiftMotor.getOutputCurrent()});
		toReturn.put("Lift Output Percent 1", new double[] {leftLiftMotor.getMotorOutputPercent()});
		toReturn.put("Lift Position", new double[] {getPosition()});
		toReturn.put("Lift Current 2", new double[] {rightLiftMotor.getOutputCurrent()});
		toReturn.put("Lift Output Percent 2", new double[] {rightLiftMotor.getMotorOutputPercent()});
		return toReturn;
	}
	
	

}
