package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public class balancesecond extends CommandBase{
   /*  public CommandBase autoBalance() {
        return Commands.race(
            Commands.sequence(
              Commands.run(
                ()->this.autodrive(2/DriveConstants.kMaxSpeedMetersPerSecond,
                              0,0,true,true),this).until(()->Math.abs(this.getRobotPitch())>=14.3),
              Commands.run(
                ()->this.autodrive(0.3/DriveConstants.kMaxSpeedMetersPerSecond,
                              0,0,true,true),this).until(()->Math.abs(this.getRobotPitch())<=12.5),
              Commands.run(this::setX,this)),
            Commands.waitSeconds(15));
          // Commands.run(
          //   ()->this.drive(0,0,0,true,true),this));
      }*/
}
