package frc.robot.commands.DriveTrainCommands;


import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;


import frc.robot.subsystems.Swerve;


    
public class Balance extends CommandBase {

        private final Swerve m_drivetrain;
        private double pitch;
        private double speed;
        private double threshold = 5;
        private double distance = 10;

 
   


    public Balance(Swerve drivetrain) {



         m_drivetrain = drivetrain;
        addRequirements(m_drivetrain);

    
    }

    
    @Override
    public void initialize() {
        System.err.println("\n WORK OR ILL DIE  ");
    }

    
    @Override
    public void execute() {
        pitch = m_drivetrain.getPitch();
        System.err.println("\n IF YOU DONT WORK IM GONNA DO BAD THINGS");
        System.err.println("\n PITCH -------" + pitch);
        for(int i = 0; i<3; i++){
            if(pitch < 0){
                new DriveAmount(m_drivetrain, distance, speed, true);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        
        return false;

    
    }
}
