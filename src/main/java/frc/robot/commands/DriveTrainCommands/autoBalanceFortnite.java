package frc.robot.commands.DriveTrainCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class autoBalanceFortnite extends CommandBase {
    
    double speed;
    Swerve m_Swerve;
    double pitch;
    double p;
    double amount;

    public autoBalanceFortnite(Swerve s_Swerve, double speed, double pitch,double amount){
        this.speed = speed;
        this.m_Swerve = s_Swerve;
        this.pitch = pitch;
        this.amount = amount;
        addRequirements(s_Swerve);
        p = .05;

    }

    public autoBalanceFortnite(Swerve s_Swerve, double speed, double amount) {
    }

    @Override
    public void initialize(){
    m_Swerve.resetEncoders();
    }

    @Override
    public void execute(){
        pitch = m_Swerve.getPitch();
        System.err.print("\n _______ PITCH " + pitch);
        if(!isDocked()){
            m_Swerve.autodrive(                
            new Translation2d(speed, 0).times(Constants.Swerve.maxSpeed),
            0 * Constants.Swerve.maxAngularVelocity,
            false,
            true);
            System.out.println("\n___________________________________Not In Pitch Mode");
        }
        else{
            m_Swerve.autodrive(
                    new Translation2d(speed * p * pitch,  0).times(Constants.Swerve.maxSpeed),
                    0 * Constants.Swerve.maxAngularVelocity,
                    false,
                    true);
                    System.out.println("\n______________________________In Pitch Mode");
            }
    }

    @Override
    public void end(boolean interrupted){
        m_Swerve.autodrive(new Translation2d(0, 0), 0, false, false);
    }
    @Override 
    public boolean isFinished() {
        return isDocked();
    }

    public boolean isDocked(){
        if (m_Swerve.getAverageEncoderValue() > amount){
            System.out.println("DRIVE AMOUNT DONE");
            m_Swerve.brakeSwerve();
            return true;
          }
        else{
            return false;
            }    
    }
}
