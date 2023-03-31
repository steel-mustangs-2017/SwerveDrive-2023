package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends CommandBase {
    private Intake intake;
    private BooleanSupplier intakeIn;
    private BooleanSupplier intakeOut;

    public IntakeCommand(Intake intake, BooleanSupplier intakeIn, BooleanSupplier intakeOut ){
        this.intake = intake;
        addRequirements(intake);

        this.intakeIn = intakeIn;
        this.intakeOut =intakeOut;
    }



    @Override
    public void execute(){
      

       
    }
}