package frc.robot.autos;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Wrist;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory.PathPlannerState;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;

public class AutonomousModeDrive1Meter extends SequentialCommandGroup {

	public AutonomousModeDrive1Meter(Swerve s_Swerve, frc.robot.subsystems.Intake i_Intake, Wrist w_Wrist) {
		//TODO 1. Check PathConstrains (different than in json file, 2. Why sample is 30 seconds?, 3. Is Balance BLue correct json path?
		System.out.println("2945: Executing AutonomousModeDrive1Meter ...");
		String pathFileName = "Forward1Meter";
		Trajectory exampleTrajectory = PathPlanner.loadPath(pathFileName, new PathConstraints(1, 1.5));
		PathPlannerState exampleState = (PathPlannerState) exampleTrajectory.sample(30);
		System.out.println(exampleState.velocityMetersPerSecond);
		
		var thetaController = new ProfiledPIDController(Constants.AutoConstants.kPThetaController, 0, 0,
				Constants.AutoConstants.kThetaControllerConstraints);
		thetaController.enableContinuousInput(-Math.PI, Math.PI);

		SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(exampleTrajectory,
				s_Swerve::getPose, Constants.Swerve.swerveKinematics,
				new PIDController(Constants.AutoConstants.kPXController, 0, 0),
				new PIDController(Constants.AutoConstants.kPYController, 0, 0), thetaController,
				s_Swerve::setModuleStates, s_Swerve);

		addCommands(				
				new InstantCommand(() -> s_Swerve.resetOdometry(exampleTrajectory.getInitialPose())),
				swerveControllerCommand);
	}
}
