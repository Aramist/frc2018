package org.usfirst.frc.team5472.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.buttons.Button;

public class LimitSwitch extends Button{
	
	private DigitalInput di;
	private boolean normallyOpen;
	
	public LimitSwitch(int dioPort, boolean normallyOpen) {
		di = new DigitalInput(dioPort);
		this.normallyOpen = normallyOpen;
	}
	
	public LimitSwitch(int dioPort) {
		this(dioPort, true);
	}
	
	@Override
	public boolean get() {
		return !(di.get() ^ normallyOpen);
		//false true -> false
		//false false -> true
		//true  true -> true
		//true  false -> false
	}
	
}