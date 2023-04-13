package frc.robot.commands.DriveTrainCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Swerve;
import frc.robot.Constants;


public class DriveAmount extends CommandBase {

    double amount;
    double speed;
    Swerve m_Drivetrain;
    boolean IfForward;

    public DriveAmount(Swerve s_Swerve, double amount, double speed, boolean IfForward){
        this.amount = amount;
        this.m_Drivetrain = s_Swerve;
        this.IfForward = IfForward;
        this.speed = speed;
        addRequirements(s_Swerve);
    }

    @Override
    public void initialize(){
        m_Drivetrain.resetEncoders();
        System.out.println("DRIVE AMOUNT INIT");
    }

    @Override
    public void execute(){
        System.out.println("Aligning wheels?");
        
        System.out.println(m_Drivetrain.getAverageEncoderValue());
        /* 
        if(IfForward){
            m_Drivetrain.drive(new Translation2d(0.25, 0), 0, false, true);
        }
        else{
            m_Drivetrain.drive(new Translation2d(0, 0.25), 0, false, true);
        }
        */
        m_Drivetrain.autodrive(
                new Translation2d(speed, 0).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                true,
                false);
        
    }
    
    @Override
    public void end(boolean interrupted) {
        System.out.println("ended");
        m_Drivetrain.autodrive(new Translation2d(0, 0), 0, false, false);
    }

    @Override
    public boolean isFinished() {
     if (m_Drivetrain.getAverageEncoderValue() > amount){
        System.out.println("\n DRIVE AMOUNT DONE" + amount);
        return true;
      }
    else{
        return false;


        }
    }

}