package frc.robot.commands.DriveTrainCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Settings;
//import frc.robot.Constants.Swerve;
import frc.robot.subsystems.Swerve;

public class AutoBalanceDrake extends CommandBase{

    private final Swerve Swerve;
    private final PIDController fowardController;

    private final double kP = 0.1;
    private final double kI = 0.1;
    private final double kD = 0.1;

    private final double maxOutput = 0.5;
    private double currentAngle;
    private double error; 
    private double output;

    public AutoBalanceDrake(Swerve swerve){

        this.Swerve = swerve;
        addRequirements(swerve);
        
        fowardController = new PIDController(kP, kI, kD);
        fowardController.setTolerance(2.0);
    }

    @Override
    public void initialize(){
        fowardController.setSetpoint(0);
    }

    @Override
    public void execute(){
        System.err.print("\n Current angle = " + currentAngle );
        output = MathUtil.clamp(fowardController.calculate(currentAngle, Settings.Balance_Goal_Angle), -currentAngle, currentAngle);
        System.err.print("\nTrying to drive with output: " + output);
        Swerve.autodrive(new Translation2d(output,0), 0, true, true);
    }

    @Override
    public void end(boolean interrupted){
        System.out.println("ended");
        Swerve.brakeSwerve();
    }
    @Override
    public boolean isFinished(){
        return fowardController.atSetpoint();
    }
}
