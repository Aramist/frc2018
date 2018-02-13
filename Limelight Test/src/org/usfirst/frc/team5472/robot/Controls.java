package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.GripToggle;
import org.usfirst.frc.team5472.robot.commands.HighGear;
import org.usfirst.frc.team5472.robot.commands.IntakePull;
import org.usfirst.frc.team5472.robot.commands.IntakePush;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;
import org.usfirst.frc.team5472.robot.commands.LiftStop;
import org.usfirst.frc.team5472.robot.commands.LowGear;
import org.usfirst.frc.team5472.robot.commands.ShiftGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {

	private Joystick stick = new Joystick(0);
//	private Joystick xbox = new Joystick(1);

//	private JoystickButton toggleLights = new JoystickButton(stick, 2);
//	private JoystickButton enableVision = new JoystickButton(stick, 10);
//	private JoystickButton disableVision = new JoystickButton(stick, 9);
//	private JoystickButton switchPipeline = new JoystickButton(stick, 11);
//	private JoystickButton boxPipeline = new JoystickButton(stick, 12);
//	private JoystickButton flashLights = new JoystickButton(stick, 6);
	private JoystickButton shiftGear = new JoystickButton(stick, 3);

	private JoystickButton intakeIn = new JoystickButton(stick, 5);
	private JoystickButton intakeOut = new JoystickButton(stick, 6);
	private JoystickButton toggleGrip = new JoystickButton(stick, 1);
	
	private JoystickButton highButton = new JoystickButton(stick, 4);
	
	public LimitSwitch highLimit = new LimitSwitch(0);

	
	public Controls() {
//		toggleLights.whenReleased(new ToggleLights());
//		enableVision.whenReleased(new EnableVision());
//		disableVision.whenReleased(new DisableVision());
//		switchPipeline.whenReleased(new SwitchPipeline());
//		boxPipeline.whenReleased(new BoxPipeline());
		shiftGear.whenPressed(new ShiftGear());
		shiftGear.whenReleased(new ShiftGear());
		highButton.whenPressed(new HighGear());
		
		intakeIn.whenPressed(new IntakePull());
		intakeIn.whenReleased(new IntakeStop());
		intakeOut.whenPressed(new IntakePush());
		intakeOut.whenReleased(new IntakeStop());

		toggleGrip.whenPressed(new GripToggle());
		
		highLimit.whenPressed(new LowGear());
		highLimit.whileActive(new LiftStop());
	}

	public Joystick getJoystick() {
		return stick;
	}
}
