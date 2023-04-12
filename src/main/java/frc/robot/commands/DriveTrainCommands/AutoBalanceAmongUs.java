package frc.robot.commands.DriveTrainCommands;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Swerve;

/*public class AutoBalanceAmongUs extends CommandBase  {
    private final Swerve _Swerve;
    private  PIDController balanceController;

    private final double kP = 0.008;
    private final double kI = 0;
    private final double kD = 0;
    
    Timer _timer;

    public AutoBalanceAmongUs(Swerve swerve){
        this._Swerve = swerve;
        addRequirements(swerve);

    }

    @Override
    public void initialize(){
        _timer = new Timer();

        //_Swerve.setDrivesMode(NeutralMode.brake);

        balanceController = new PIDController(kP, kI, kD);
        balanceController.setP(kP);
        balanceController.setI(kI);
        balanceController.setD(kD);
        balanceController.setTolerance(1.25);
    }

    @Override
    public void execute(){
        _timer.start();

    }*/
//}
