package org.usfirst.frc.team5472.robot;

import org.usfirst.frc.team5472.robot.commands.GripToggle;
import org.usfirst.frc.team5472.robot.commands.HighGear;
import org.usfirst.frc.team5472.robot.commands.IntakePull;
import org.usfirst.frc.team5472.robot.commands.IntakePush;
import org.usfirst.frc.team5472.robot.commands.IntakeStop;
import org.usfirst.frc.team5472.robot.commands.LiftCoast;
import org.usfirst.frc.team5472.robot.commands.LiftStop;
import org.usfirst.frc.team5472.robot.commands.ShiftGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {

	private Joystick playerOne = new Joystick(0);
	private Joystick playerTwo = new Joystick(1);

	private JoystickButton shiftGear = new JoystickButton(playerOne, 3);

	private JoystickButton intakeIn = new JoystickButton(playerOne, 5);
	private JoystickButton intakeOut = new JoystickButton(playerOne, 6);
	private JoystickButton toggleGrip = new JoystickButton(playerOne, 1);
	
	private JoystickButton highButton = new JoystickButton(playerOne, 4);
	
	private JoystickButton liftDown = new JoystickButton(playerTwo, 1);
	
	public LimitSwitch highLimit = new LimitSwitch(0);

	
	public Controls() {
		shiftGear.whenPressed(new ShiftGear());
		shiftGear.whenReleased(new ShiftGear());
		highButton.whenPressed(new HighGear());
		
		intakeIn.whenPressed(new IntakePull());
		intakeIn.whenReleased(new IntakeStop());
		intakeOut.whenPressed(new IntakePush());
		intakeOut.whenReleased(new IntakeStop());

		toggleGrip.whenPressed(new GripToggle());
		
		highLimit.whileActive(new LiftStop());

		liftDown.whenPressed(new LiftCoast());
	}

	public Joystick getPlayerOne() {
		return playerOne;
	}
	
	public Joystick getPlayerTwo() {
		return playerTwo;
	}
	
	public double getLiftUpAxis() {
		return playerTwo.getRawAxis(3);
	}
	
	public double getLiftDownAxis() {
		return playerTwo.getRawAxis(2);
	}
	
	public double getDriveVerticalAxis() {
		return playerOne.getRawAxis(1);
	}
	
	public double getDriveHorizontalAxis() {
		return playerOne.getRawAxis(0);
	}
}
