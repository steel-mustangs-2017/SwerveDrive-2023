package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeOneMotor extends SubsystemBase{
    private final WPI_TalonFX MainIntake = new WPI_TalonFX(Constants.Intake.MainIntake);
    //private final WPI_TalonFX intakeRight = new WPI_TalonFX(Constants.Intake.intakeRight);
    private final double speed = 0.4;

public IntakeOneMotor(){
    MainIntake.configFactoryDefault();
   // intakeRight.configFactoryDefault();

    MainIntake.setInverted(true);
    //intakeRight.setInverted(false);
    
    MainIntake.setNeutralMode(NeutralMode.Brake);
   // intakeRight.setNeutralMode(NeutralMode.Brake);
}

public void runIntake() {
    MainIntake.set(-0.75);
}

public void reverseIntake() {
    MainIntake.set(0.35);
}
public void slowReverseIntake(){
    MainIntake.set(-0.35);
}

public void stopIntake(){
    MainIntake.set(0);
} 


}
