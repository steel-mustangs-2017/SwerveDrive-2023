package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import frc.robot.Constants;
import frc.robot.RobotContainer;
public class Elevator extends SubsystemBase{
  /* */  public WPI_TalonFX elevatorMain = new WPI_TalonFX(Constants.Elevator.elevatorMotor);
    private DigitalInput level_0;
    private DigitalInput level_1;
    private DigitalInput level_2;
    int state = 1;
    double autospeed = 0;

    public Elevator(){
        elevatorMain.configFactoryDefault();
        elevatorMain.setNeutralMode(NeutralMode.Brake);   
        level_0 = new DigitalInput(0);
        level_1 = new DigitalInput(1);
        level_2 = new DigitalInput(2);
        autospeed = 0;
        state = 1;

    }
    public void elevatorStop(){
        elevatorMain.set(0);
        autospeed = 0;
    }
    public void elevatorUp(double speed){
        //elevatorMain.set(speed);
        //System.err.println("*******************" + level_2.get()+ "speed " + speed + " autospeed " + autospeed + " state " + state);
        if(autospeed != 0){
            speed = autospeed;
        }
        if(level_2.get()  == false){ 
            elevatorStop();
            state = 3;
            
        }
        if(level_1.get() == false){
            elevatorStop();
            state = 2;
        }
        else if(level_0.get()  == false){ 
            elevatorStop();
            state = 1;
            
        }
        if(state != 1 && speed<0){
            elevatorMain.set(speed);
            state = 4;
    
        }
         else if(state !=3 && speed>0){
            elevatorMain.set(speed);
            state = 5;
        }
        
        else{
           elevatorMain.set(0);
        }
    
    }
    public void elevatorDown(double speed){
        elevatorMain.set(-speed);
    }
    public void elevatorTop(){
        autospeed = .95;
        
    }
    public void elevatorBot(){
        autospeed = -.95;
    }
    public void elevatorMid(){
    if(state == 3){
        autospeed = -.95;
    } 
    if(state == 1){
        autospeed =.95;
    } 
    }
    
    
    
}
