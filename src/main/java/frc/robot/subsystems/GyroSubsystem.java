// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.analog.adis16470.frc.ADIS16470_IMU;
import frc.robot.Robot;

public class GyroSubsystem extends SubsystemBase {
  /** Creates a new GyroSubsytem. */
 //private static final ADIS16470_IMU m_imu = new ADIS16470_IMU();   //null pointer 


  public double getYawAngle(){
    double yawAngle = Robot.m_imu.getAngle();
    System.out.println(yawAngle);
    return yawAngle;
  }
  public void resetAngle(){
    Robot.m_imu.reset();
  }

  public GyroSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
