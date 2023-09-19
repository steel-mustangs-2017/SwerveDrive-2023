package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.Intake;
import frc.robot.subsystems.IntakeOneMotor;
import frc.robot.subsystems.Wrist;

public class WristCommand extends CommandBase {
	private final Wrist Wrist;
	private Joystick WristController;
	private final IntakeOneMotor intake;
	// private boolean auto = false;

	public WristCommand(Wrist wrist, Joystick WristController,IntakeOneMotor intake) {

		this.Wrist = wrist;
		this.WristController = WristController;
		this.intake = intake;
		

		// this.auto = autotf;
		addRequirements(wrist);

	}

	@Override
	public void execute() {
		// System.err.println("********************* get_state= " + Wrist.getState());
		
		/*if(WristController.getRawButton(3)){
			Wrist.wristTop();
		} 
		else if(WristController.getRawButton(4) && Wrist.getState() == 2){ 
			Wrist.wristDown();
		}*/
		 
		
		
		 
		double wrist = -WristController.getPOV(0);
		Wrist.wristUp(wrist);
		
	}

	private void WaitCommand(int i) {
	}

}
