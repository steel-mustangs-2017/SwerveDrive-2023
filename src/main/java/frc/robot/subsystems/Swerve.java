package frc.robot.subsystems;

import frc.robot.SwerveModule;
import frc.robot.Constants;


import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.sensors.Pigeon2;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Swerve extends SubsystemBase {
    public SwerveDriveOdometry swerveOdometry;
    public SwerveModule[] mSwerveMods;
    public Pigeon2 gyro;

    public Swerve() {
        gyro = new Pigeon2(Constants.Swerve.pigeonID);
        gyro.configFactoryDefault();
        zeroGyro();

        mSwerveMods = new SwerveModule[] {
            new SwerveModule(0, Constants.Swerve.Mod0.constants),
            new SwerveModule(1, Constants.Swerve.Mod1.constants),
            new SwerveModule(2, Constants.Swerve.Mod2.constants),
            new SwerveModule(3, Constants.Swerve.Mod3.constants)
        };

        /* By pausing init for a second before setting module offsets, we avoid a bug with inverting motors.
         * See https://github.com/Team364/BaseFalconSwerve/issues/8 for more info.
         */
        Timer.delay(1.0);
        resetModulesToAbsolute();

        swerveOdometry = new SwerveDriveOdometry(Constants.Swerve.swerveKinematics, getYaw(), getModulePositions());
    }
   // Assuming this method is part of a drivetrain subsystem that provides the necessary methods
   public double deadband(double val) {

    if (Math.abs(val) > 0.03) {
        return (val);
    } else {
        return (0);
    }
}

   public void autodrive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {

    SwerveModuleState[] swerveModuleStates = Constants.Swerve.swerveKinematics.toSwerveModuleStates(
            fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
                    deadband(translation.getX()),
                    deadband(translation.getY()),
                    rotation,
                    getYaw())
                    : new ChassisSpeeds(
                            deadband(translation.getX()),
                            deadband(translation.getY()),
                            rotation));
    SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.maxSpeed);

    for (SwerveModule mod : mSwerveMods) {
        mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
    }
} 

    /* Used by SwerveControllerCommand in Auto */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.Swerve.maxSpeed);
        
        for(SwerveModule mod : mSwerveMods){
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
        }
    }    

    public Pose2d getPose() {
        return swerveOdometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose) {
        swerveOdometry.resetPosition(getYaw(), getModulePositions(), pose);
    }

    public SwerveModuleState[] getModuleStates(){
        SwerveModuleState[] states = new SwerveModuleState[4];
        for(SwerveModule mod : mSwerveMods){
            states[mod.moduleNumber] = mod.getState();
        }
        return states;
    }

    public SwerveModulePosition[] getModulePositions(){
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for(SwerveModule mod : mSwerveMods){
            positions[mod.moduleNumber] = mod.getPosition();
        }
        return positions;
    }

    public void zeroGyro(){
        gyro.setYaw(0);
    }
    public void brakeSwerve(){
        SwerveModule module0 = mSwerveMods[0];
        SwerveModule module1 = mSwerveMods[1];
        SwerveModule module2 = mSwerveMods[2];
        SwerveModule module3 = mSwerveMods[3];
        module0.setNeutralMode(NeutralMode.Brake);
        module1.setNeutralMode(NeutralMode.Brake);
        module2.setNeutralMode(NeutralMode.Brake);
        module3.setNeutralMode(NeutralMode.Brake);  
    }
    public void speedThrottle(boolean  hiLow){
        if(hiLow == true){
            Constants.Swerve.maxSpeed = Constants.Swerve.maxSpeed/2;
           // System.out.println("Max Speed; " + Constants.Swerve.maxSpeed); 
            Constants.Swerve.maxAngularVelocity = Constants.Swerve.maxAngularVelocity/2;
           // System.out.println("Max Spin;" + Constants.Swerve.maxAngularVelocity);
            
        }
        else{
            Constants.Swerve.maxSpeed = Constants.Swerve.maxSpeed*2;
            Constants.Swerve.maxAngularVelocity = Constants.Swerve.maxAngularVelocity*2; 
           // System.out.println("Max Speed; " + Constants.Swerve.maxSpeed);
           // System.out.println("Max Spin;" + Constants.Swerve.maxAngularVelocity);    
        }
        
    }

    public Rotation2d getYaw() {
        return (Constants.Swerve.invertGyro) ? Rotation2d.fromDegrees(360 - gyro.getYaw()) : Rotation2d.fromDegrees(gyro.getYaw());
    }

    public void resetModulesToAbsolute(){
        for(SwerveModule mod : mSwerveMods){
            mod.resetToAbsolute();
        }
    }
    public double getPitch() {
        return gyro.getRoll();//pigeon orientation
    }

    public ChassisSpeeds getButton(Translation2d translation, double rotation, boolean fieldRelative,boolean halfSpeed){
        if(fieldRelative && halfSpeed){
           return ChassisSpeeds.fromFieldRelativeSpeeds(
                (translation.getX()/3), 
                (translation.getY()/3),
                (rotation/3), 
                getYaw()
                );
        }
    else if(!fieldRelative && halfSpeed){
            return new ChassisSpeeds(
                (translation.getX()/3), 
                (translation.getY()/3),
                (rotation/3)
            );
        }
    else if(fieldRelative && !halfSpeed){
        return ChassisSpeeds.fromFieldRelativeSpeeds(
                        translation.getX(), 
                        translation.getY(), 
                        rotation, 
                        getYaw()
                    );
        }
    else{
        return new ChassisSpeeds(
                        translation.getX(), 
                        translation.getY(), 
                        rotation);
        }
    }
    public void resetEncoders() {
        SwerveModule module0 = mSwerveMods[0];
        SwerveModule module1 = mSwerveMods[1];
        SwerveModule module2 = mSwerveMods[2];
        SwerveModule module3 = mSwerveMods[3];
        for(int i = 0; i<10;i++){
            module0.resetDriveEncoder();
            module1.resetDriveEncoder();
            module2.resetDriveEncoder();
            module3.resetDriveEncoder();
        }
    }
    public double getAverageEncoderValue() {
        SwerveModule module0 = mSwerveMods[0];
        SwerveModule module1 = mSwerveMods[1];
        SwerveModule module2 = mSwerveMods[2];
        SwerveModule module3 = mSwerveMods[3];
        return (((Math.abs(module0.getWheelPosition()) +
            Math.abs(module1.getWheelPosition()) +
            Math.abs(module2.getWheelPosition()) +
            Math.abs(module3.getWheelPosition())// Do the math to make this into inches
        ) / 4) / (1350)); //TODO Do this math
        // 1280 ticks per revolution (2048 ticks per rev x 6.8 Gear Ratio / 2pi x3.5
                      // inches per rev)
    }
     public void setDrivesMode(NeutralMode idleMode) {
        SwerveModule module0 = mSwerveMods[0];
        SwerveModule module1 = mSwerveMods[1];
        SwerveModule module2 = mSwerveMods[2];
        SwerveModule module3 = mSwerveMods[3];
		module0.setDriveMode(idleMode);
		module1.setDriveMode(idleMode);
		module2.setDriveMode(idleMode);
		module3.setDriveMode(idleMode);
	}

    

    @Override
    public void periodic(){
        swerveOdometry.update(getYaw(), getModulePositions());  

        for(SwerveModule mod : mSwerveMods){
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Cancoder", mod.getCanCoder().getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated", mod.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);    
        }
    }
    
    //public void drive(Translation2d times, double d, boolean b, boolean c) {
   // }
   public void drive(Translation2d translation, double rotation, boolean fieldRelative,boolean halfSpeed, boolean isOpenLoop) {
    SwerveModuleState[] swerveModuleStates =
        Constants.Swerve.swerveKinematics.toSwerveModuleStates(getButton(translation, rotation, fieldRelative, halfSpeed));

           
    SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.maxSpeed);

    for(SwerveModule mod : mSwerveMods){
        mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
    }
}    

}