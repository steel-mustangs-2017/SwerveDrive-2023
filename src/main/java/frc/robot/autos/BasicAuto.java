// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: SequentialCommandGroup.

package frc.robot.autos;
import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveTrainCommands.AutoBalanceDrake;
import frc.robot.commands.DriveTrainCommands.DriveAmount;
import frc.robot.commands.DriveTrainCommands.DriveAmountAndDriveUntilBalanced;
import frc.robot.commands.DriveTrainCommands.DriveUntilBalanced;
import frc.robot.commands.DriveTrainCommands.autoBalanceFortnite;
//import frc.robot.commands.DrivetrainCommands.StrafeAmount;
//import frc.robot.commands.IntakeCommands.IntakeCube;
//import frc.robot.commands.IntakeCommands.IntakeMotorGo;
//import frc.robot.commands.SmartCommands.ClimbPoseBack;
//import frc.robot.commands.SmartCommands.CollectFloorPoseBack;
//import frc.robot.commands.SmartCommands.DriveAmountUntilBalancedAndArmClimbParallel;
//import frc.robot.commands.SmartCommands.HomePose;
//import frc.robot.commands.SmartCommands.Scores.ScoreHighCubePose;
//import frc.robot.subsystems.elevator;
//import frc.robot.subsystems.intake;
//import frc.robot.subsystems.arm;
import frc.robot.subsystems.Swerve;
import frc.robot.Constants;
import frc.robot.commands.autoBalance;

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
            
        
        //new ScoreHighCubePose(intake, wrist, arm, elevator),
        //new IntakeMotorGo(intake, -0.2).withTimeout(.3),
        //new HomePose(elevator, intake, wrist, arm),
        //new ClimbPoseBack(elevator, intake, wrist, arm),
       // new DriveAmount(s_Swerve,45,.2, false),
        ///new autoBalanceFortnite(s_Swerve,),
       new Balance(s_Swerve)
       // new CollectFloorPoseBack(elevator, intake, wrist, arm, leds)
      
       // new Balance(drivetrain)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
    
        return false;

    }
}