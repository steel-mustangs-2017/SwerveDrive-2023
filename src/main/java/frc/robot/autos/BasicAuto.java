

package frc.robot.autos;
import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Swerve;
import frc.robot.Constants;
import frc.robot.commands.DriveTrainCommands.*;

//import frc.robot.subsystems.wrist;
//import frc.robot.subsystems.leds;
    
/**
 *
 */
public class BasicAuto extends SequentialCommandGroup {

    
  

    public BasicAuto(Swerve s_Swerve){
        TrajectoryConfig config =
        new TrajectoryConfig(
                Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            .setKinematics(Constants.Swerve.swerveKinematics);

            Trajectory exampleTrajectory = PathPlanner.loadPath("Balance Blue", new PathConstraints(0, 0)); 
            addCommands(
       // new InstantCommand(() -> s_Swerve.resetOdometry(exampleTrajectory.getInitialPose())),
        new DriveAmount(s_Swerve,45,.2, false),
        new Balance(s_Swerve)
      
        );
    }

    @Override
    public boolean runsWhenDisabled() {
     return false;
    }
}