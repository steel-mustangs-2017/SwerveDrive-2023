package frc.robot.commands.DriveTrainCommands;


import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;


import frc.robot.subsystems.Swerve;


    
public class Balance extends CommandBase {

        private final Swerve m_drivetrain;
        private double pitch;
        private double speed = 0.15;
        private double threshold = 5;
        private double amount = 0;
        private double distance = 1;

 
   


    public Balance(Swerve drivetrain) {



         m_drivetrain = drivetrain;
        addRequirements(m_drivetrain);

    
    }

    
    @Override
    public void initialize() {
        m_drivetrain.resetEncoders();
        new DriveAmount(m_drivetrain, 0, 0, true);
        System.err.println("\n WORK OR ILL DIE  ");
    }

    
    @Override
    public void execute() {
        pitch = m_drivetrain.getPitch();
        System.err.println("\n IF YOU DONT WORK IM GONNA DO BAD THINGS");
        System.err.println("\n PITCH _________________" + pitch);
            if(pitch < 0 && pitch >-.05){
                m_drivetrain.autodrive(new Translation2d(0, 0), 0, false, false);
                m_drivetrain.brakeSwerve();
                System.err.println("\n this is my last straw");
            }
            else if(pitch < -1 ){
                System.err.print("\n \nHIHJIAOH duigwuGD7gwaugfdd WEENRO");
                m_drivetrain.autodrive(new Translation2d(distance, speed), 0, false, false);
        }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        System.err.println("\n FINISHHIEHSIDN ");
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        
        return false;

    
    }
}
