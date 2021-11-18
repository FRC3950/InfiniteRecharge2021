/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
  /**
   * Creates a new DrivetrainSubystem.
   */
  private final WPI_TalonFX m_frontLeft = new WPI_TalonFX(15);
  private final WPI_TalonFX m_frontRight = new WPI_TalonFX(1);
  private final WPI_TalonFX m_backLeft = new WPI_TalonFX(14);
  private final WPI_TalonFX m_backRight = new WPI_TalonFX(0);
  private final DoubleSolenoid m_shiftGearSolenoid = new DoubleSolenoid(2,3);
  
  
  DifferentialDrive m_drive;

  private final Joystick driveStick = new Joystick(0);

  public DrivetrainSubsystem() {

    // final SpeedControllerGroup left = new SpeedControllerGroup(m_frontLeft, m_backLeft);
    // final SpeedControllerGroup right = new SpeedControllerGroup(m_frontRight, m_backRight);
    // m_drive = new DifferentialDrive(left, right);

    m_frontLeft.follow(m_backLeft);
    m_frontRight.follow(m_backRight);
    m_drive = new DifferentialDrive(m_backLeft, m_backRight);

    m_drive.setSafetyEnabled(false);    

    m_frontLeft.setNeutralMode(NeutralMode.Brake);
    m_frontRight.setNeutralMode(NeutralMode.Brake);
    m_backLeft.setNeutralMode(NeutralMode.Brake);
    m_backRight.setNeutralMode(NeutralMode.Brake);
    m_drive.arcadeDrive(driveStick.getY(), driveStick.getTwist());

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //Drives the robot based on joystick values
  public void drive(double y, double twist){
    // if (y < .05){ //May need to change the deadzone
    //   y = 0;
    // }
    y = y * y * y;
    twist = twist * twist * twist;
    m_drive.arcadeDrive(-y, twist);
    //System.out.println("twist"+ twist);
    //System.out.println("y"+ y);
  }

  //Sets the motor to a certain speed without the joystick 
  //Used for Autonomous Code
  public void motorSpeed(double speed){
    m_drive.arcadeDrive(speed,0);
  }
  public void motorAngle(double speed, double angle){
    m_drive.arcadeDrive(speed,angle);
  }

  //Makes sure that if the drivetrain motors ever exceed 55 Amps the gear will automatically shift to low gear
  public void overrideShiftGear(){
    if(m_frontLeft.getStatorCurrent() >= 55){
      m_shiftGearSolenoid.set(DoubleSolenoid.Value.kReverse);
      // check to see if double solenoid should be kReverse or kForward
    }
  }

  //Allows the driver to switch between high and low gear
  public void shiftGear(){ 
    if(m_shiftGearSolenoid.get() == DoubleSolenoid.Value.kReverse){
      m_shiftGearSolenoid.set(DoubleSolenoid.Value.kForward);
    } else{
    m_shiftGearSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
  }

  //Gets the position of one of the drivetrain motors from the encoder
  public double getEncoderCount(){
    return m_backLeft.getSelectedSensorPosition();
  }

  public void resetEncoderCount(){
    m_backLeft.setSelectedSensorPosition(0);
  }

  //true direction = positive speed, false direction = negative speed
  public boolean autoDriveToBall(int desiredEncoderCount, boolean direction){
    double speed;
    resetEncoderCount();
    double encoderCount = getEncoderCount();
    if(direction){
      speed = .5;
      while(encoderCount < desiredEncoderCount){
        motorSpeed(speed);
        encoderCount = getEncoderCount();
      }
    }else{
      speed = -1;
      while(encoderCount > desiredEncoderCount){
        motorSpeed(speed);
        encoderCount = getEncoderCount();
      }
    }
    drive(speed, 0);
    System.out.println("auto drive");
    return true;
  }

  //   if(fieldPosition == 3){
  //     while(ballCount < 4 && ballInIntake == 1){ 
  //       drive(.5, 0); //NEED TO FIX POSITIVE OR NEGATIVE AND SPEED 
  //     }
  //     resetEncoderCount();
  //     if(ballCount == 4 && encoderCount < 4000){ //Need to fix encoder count when testing
  //       drive(-.5,0); //NEED TO FIX POSITIVE OR NEGATIVE AND SPEED 
  //     } else{
  //       drive(0,0);
  //     }
      
  //   }
  //   if(fieldPosition == 2){
  //     //Add code for when robot is directly in front of power port at start
  //   }
  //   if(fieldPosition == 1){
  //     //Add code for when robot is near loading bay at start
  //   }
  // }
}

