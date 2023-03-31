package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick manipulator = new Joystick(1);
    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton speedThrottle = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
        
    /*Manipulator Buttons */
    public final JoystickButton intakeIn = new JoystickButton(manipulator, XboxController.Button.kRightBumper.value);
    private final JoystickButton intakeOut = new JoystickButton(manipulator, XboxController.Button.kLeftBumper.value);
    public final JoystickButton intakeSlow = new JoystickButton(manipulator, XboxController.Button.kA.value);
    //private final JoystickButton elevatorLvl1 = new JoystickButton(manipulator, XboxController.Button.kA.value);
    //private final JoystickButton elevatorLvl2 = new JoystickButton(manipulator, XboxController.Button.kB.value);
    //private final JoystickButton elevatorLvl3 = new JoystickButton(manipulator, XboxController.Button.kX.value);
    private final Swerve s_Swerve = new Swerve();
    private final Intake i_Intake = new Intake();
  //  private final Elevator e_Elevator = new Elevator();
    public final Wrist w_Wrist = new Wrist();
   // private final HorizontalElevator h_Elevator = new HorizontalElevator();
    /*Manipulator Controls */
    //private final int elevatorMain = XboxController.Axis.kLeftY.value;
   

    /*robot subsystems */
   // private final ElevatorCommand elevatorCommand = new ElevatorCommand(e_Elevator, h_Elevator, manipulator, manipulator);
    private final WristCommand wristCommand = new WristCommand(w_Wrist, manipulator);
  //  private final WristCommandAuto wristCommandAuto = new WristCommandAuto(w_Wrist, manipulator);
    //private final HorizantalElevatorCommand horizantalElevatorCommand = new HorizantalElevatorCommand(h_Elevator, manipulator);
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean(),
                () -> speedThrottle.getAsBoolean()
            ) 
        );
        
       

        // Configure the button bindings
        configureButtonBindings();
        configureDefaultCommands();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

        /* Manipulator Butoons */
        intakeIn.onTrue(new InstantCommand(() -> i_Intake.runIntake()));
        intakeOut.onTrue(new InstantCommand(() -> i_Intake.reverseIntake()));
        intakeIn.onFalse(new InstantCommand(() -> i_Intake.stopIntake()));
        intakeOut.onFalse(new InstantCommand(() -> i_Intake.stopIntake()));
        intakeSlow.onTrue(new InstantCommand(() ->i_Intake.slowReverseIntake()));
        intakeSlow.onFalse(new InstantCommand(() -> i_Intake.stopIntake()));
    }
    private void configureDefaultCommands(){
       // e_Elevator.setDefaultCommand(elevatorCommand);
        w_Wrist.setDefaultCommand(wristCommand);
      //  h_Elevator.setDefaultCommand(elevatorCommand);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
 public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new Red_Balance(s_Swerve, i_Intake , w_Wrist);

      }

}
