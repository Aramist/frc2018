package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.BoxPipeline;
import org.usfirst.frc.team5472.robot.commands.DisableVision;
import org.usfirst.frc.team5472.robot.commands.EnableVision;
import org.usfirst.frc.team5472.robot.commands.SwitchPipeline;
import org.usfirst.frc.team5472.robot.commands.ToggleLights;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Controls {
	
	private Joystick stick = new Joystick(0);
	
	//TEMPORARY
	private JoystickButton toggleLights = new JoystickButton(stick, 2);
	private JoystickButton enableVision = new JoystickButton(stick, 10);
	private JoystickButton disableVision = new JoystickButton(stick, 9);
	private JoystickButton switchPipeline = new JoystickButton(stick, 11);
	private JoystickButton boxPipeline = new JoystickButton(stick, 12);
	
	public Controls() {
		toggleLights.whenReleased(new ToggleLights());
		enableVision.whenReleased(new EnableVision());
		disableVision.whenReleased(new DisableVision());
		switchPipeline.whenReleased(new SwitchPipeline());
		boxPipeline.whenReleased(new BoxPipeline());
		
		SmartDashboard.putData("Toggle Lights", new ToggleLights());
	}
	
	public Joystick getJoystick() {
		return stick;
	}
}
