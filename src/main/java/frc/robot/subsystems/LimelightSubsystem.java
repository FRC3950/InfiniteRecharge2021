/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance; 

public class LimelightSubsystem extends SubsystemBase {
  /**
   * Creates a new LimelightSubsystem.
   */
  NetworkTable table;

  public LimelightSubsystem() {
    
    table = NetworkTableInstance.getDefault().getTable("limelight");
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  
  // public void getSkew(){ 
  //   NetworkTableEntry ts = table.getEntry("ts");
  //   double s = ts.getDouble(0);
  //   System.out.println("Skew: " + s);
  // }

  //Uses a formula and the values from the limelight to calculate the distance the robot is from the target
  //Will be used to create a formula to calculate the speed the ball needs to travel based on distance
  public double calculateDistance(){
    //NEED To SET VALUES FOR THESE
    double mountingAngle = 23.5;
    double cameraHeight = 22.875;
    double targetHeight = 98.25;
    double angleToTarget = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    //System.out.println(NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0));
    double distance = ((targetHeight - cameraHeight) / Math.tan(Math.PI/180*(mountingAngle + angleToTarget)));
    return distance;
  }
  
  //Gets the horizontal offset of the robot from the target to be used to turn the turret when shooting
  public double getAngle(){
    double horizontalOffset =  NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    return horizontalOffset;
  } 
  public double getVerticalAngle(){
    double verticalOffset = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    return verticalOffset;
  } 
  public boolean getTarget(){
    if(table.getEntry("tv").getDouble(0) == 1){
      return true;
    }else{
      return false;
    }
  }
}
