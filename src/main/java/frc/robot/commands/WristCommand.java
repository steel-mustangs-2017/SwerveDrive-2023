package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wrist;

public class WristCommand extends CommandBase{
    private final Wrist Wrist;
    private Joystick WristController;
  //  private boolean auto = false;


public WristCommand(Wrist wrist, Joystick WristController ){

    this.Wrist = wrist;
    this.WristController = WristController;
   // this.auto = autotf;
    addRequirements(wrist);

}
@Override
public void execute(){
   // System.err.println("********************* get_state= " + Wrist.getState());
    /*if(WristController.getRawButton(3) && Wrist.getState() == 1){
        Wrist.wristTop();
    }
    else if(WristController.getRawButton(4) && Wrist.getState() == 2){
        Wrist.wristDown();
    }*/
    /*if(auto == true){
        Wrist.wristUp(-180);
    }
    else{*/
    double wrist = -WristController.getPOV(0);
    Wrist.wristUp(wrist);
    //}
}

}
