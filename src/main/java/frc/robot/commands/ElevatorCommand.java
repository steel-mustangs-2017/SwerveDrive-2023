package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HorizontalElevator;
import frc.robot.RobotContainer;
public class ElevatorCommand extends CommandBase {
   private final Elevator Elevator; 
   private Joystick elevatorController;
   private final HorizontalElevator HorizontalElevator;
   private Joystick HorizontalElevatorController;

   

public ElevatorCommand(Elevator elevator, HorizontalElevator horizontalElevator, Joystick elevatorController, Joystick HorizontalElevatorController){

   this.Elevator = elevator;
   this.elevatorController = elevatorController;
   this.HorizontalElevator = horizontalElevator;
   this.HorizontalElevatorController = HorizontalElevatorController;

   
   addRequirements(elevator);
   addRequirements(HorizontalElevator);
}

@Override
public void execute(){
   
  /*  if(elevatorController.getRawButtonPressed(1)){
      
      System.err.println("elevator button 1");
     //Elevator.elevatorTop();
      HorizontalElevator.elevatorBot();
   }
   else if(elevatorController.getRawButtonPressed(2)){
      
      System.err.println("elevator button 2"  );
      Elevator.elevatorBot();
      HorizontalElevator.elevatorTop();
   }
   /*else if(elevatorController.getRawButtonPressed(3)){
     // Elevator.elevatorMid();
      HorizontalElevator.elevatorMid();
   }*/

  //double elevatorMain = -elevatorController.getRawAxis(1);
  //Elevator.elevatorUp(elevatorMain);
   //double horizontalElevator = -HorizontalElevatorController.getRawAxis(4);
   //HorizontalElevator.elevatorUp(horizontalElevator);
}
   



}
