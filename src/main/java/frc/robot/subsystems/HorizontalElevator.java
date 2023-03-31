package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HorizontalElevator extends SubsystemBase {
    public WPI_TalonFX HorElvator = new WPI_TalonFX(Constants.Elevator.HorizontalElevator);
    private DigitalInput level_0;
    private DigitalInput level_1;
    private DigitalInput level_2;
    int state = 1;
    double autospeed = 0;

    public HorizontalElevator(){
        HorElvator.configFactoryDefault();
        HorElvator.setNeutralMode(NeutralMode.Brake);   
        level_0 = new DigitalInput(5);
        level_1 = new DigitalInput(6);
        //level_2 = new DigitalInput(7);
        autospeed = 0;
        state = 1;

    }
   
    public void elevatorStop(){
        HorElvator.set(0);
        autospeed = 0;
    }
    public void elevatorUp(double speed){
        //elevatorMain.set(speed);
        //System.err.println("******************* level0: " + level_0.get() + "\n******************* level1: " + level_1.get() + "  speed " + speed + " autospeed " + autospeed + " state " + state);
        if(autospeed != 0){
            speed = autospeed;
        }
        /*if(level_2.get()  == false){ 
            elevatorStop();
            state = 3;
            
        }*/
        if(level_1.get() == false){
            elevatorStop();
            state = 2;
        }
        else if(level_0.get()  == false){ 
            elevatorStop();
            state = 1;
            
        }
        //This is retraction
        if(state != 1 && speed>0){
            HorElvator.set(speed);
            state = 4;
            
        }
        //This is extension
         else if(state !=2 && speed<0){
            HorElvator.set(speed);
            state = 5;
        }
        
        else{
          //  HorElvator.set(0);
        }
    
    }
    /*public void elevatorDown(double speed){
        HorElvator.set(-speed);
    }*/
    public void elevatorTop(){
        autospeed = .25;
        
    }
    public void elevatorBot(){
        autospeed = -.25;
    }
   /* public void elevatorMid(){
    if(state == 2){
        autospeed = -.95;
    } 
    if(state == 1){
        autospeed =.95;
    } 
    }*/
}
