package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HorizontalElevator;


public class HorizantalElevatorCommand extends CommandBase {
    private final HorizontalElevator HorizontalElevator;
    private Joystick HorizontalElevatorController;

public HorizantalElevatorCommand(HorizontalElevator HorizontalElevator, Joystick HorizontalElevatorController){
    this.HorizontalElevator = HorizontalElevator;
    this.HorizontalElevatorController = HorizontalElevatorController;

    addRequirements(HorizontalElevator);
}   

@Override
public void execute(){
    //double horizontalElevator = HorizontalElevatorController.getRawAxis(3);
    //HorizontalElevator.elevatorUp(horizontalElevator);
}
}
