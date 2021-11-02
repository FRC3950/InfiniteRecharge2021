/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class BallManipulatorSubsystem extends SubsystemBase {
  /**
   * Creates a new BallManipulatorSubsystem.
   */

  private final WPI_TalonSRX m_conveyorMotor;
  private final WPI_TalonSRX m_indexerMotor;

  public BallManipulatorSubsystem() {

    m_conveyorMotor = new WPI_TalonSRX(5);
    m_indexerMotor = new WPI_TalonSRX(10);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //Sets the conveyer belt to a certain speed
  public void setConveyorMotor(double speed, int ballsInConveyer, boolean ballsInIndexer){
    if(ballsInConveyer > 0 && ballsInIndexer == false){
      m_conveyorMotor.set(speed);
    } else{
      m_conveyorMotor.set(0);
    }
  }

  //Sets the conveyor motor to a desired speed
  public void setConveyorMotor(double speed){
    m_conveyorMotor.set(speed);
  }
  public double getConveyorMotor(){
    return m_conveyorMotor.get();
  }

  //Sets the part of the robot that moves the ball from the conveyer belt to the shooter to a certain speed
  public void setBallIndexerMotor(double speed){
    m_indexerMotor.set(speed);
  }
  public double getBallIndexerMotor(){
    return m_indexerMotor.get();
  }

  // public void autoBallManipulatorMotors(int motors){
  //   if(motors == 0 || motors == 1){
  //     setBallIndexerMotor(0);
  //     setConveyorMotor(0);
  //   }else if(motors == 2){
  //     setBallIndexerMotor(0);
  //     setConveyorMotor(-1);
  //   }else if(motors == 3){
  //     setBallIndexerMotor(1);
  //     setConveyorMotor(-1);
  //   }
  //   if(Robot.ballCount == 4){
  //     setConveyorMotor(0);
  //   }
  // }

  //If there is a ball in the robot, try to get one ball into the indexer
  public void putBallInShooter(boolean ballInIndexer, double speed, int ballCount){
  if(ballInIndexer == false && ballCount > 0 ){
      setBallIndexerMotor(speed);
    }
  }

  //Used when there is one ball in the robot
  public String oneBall(String values){
    if(values.charAt(3) == '1'){
      m_indexerMotor.set(0);
      m_conveyorMotor.set(0);
      return "off";
    } else{
      m_indexerMotor.set(.5);
      m_conveyorMotor.set(.5);
      return "both on";
    }
  }

  //Used when there are two balls in the robot
  public String twoBalls(String values){
    String results = "2";
    if(values.charAt(3) == '1'){
      m_indexerMotor.set(0);
      results += "indexer off, ";
    } else if(values.charAt(3) == '0'){
      m_indexerMotor.set(.5);
      results += "indexer on, ";

    }
    if(values.charAt(2) == '1'){
      m_conveyorMotor.set(0);
      results += "conveyor off";
    } else if(values.charAt(2) == '0'){
      m_conveyorMotor.set(.5);
      results += "conveyor on";
    }
    return results;
    
  }

  //Used when there are three balls in the robot
  public String threeBalls(String values){
    String results = "3";
    if(values.charAt(3) == '1'){
      m_indexerMotor.set(0);
      results += "indexer off, ";      
    } else{
      m_indexerMotor.set(.5);
      results += "indexer on, ";
    }
    if(values.charAt(2) == '1' && values.charAt(1) == '0'){
      m_conveyorMotor.set(0);
      results += "conveyor off";    
    } else{
      m_conveyorMotor.set(.5);
      results += "conveyor on";
    }    
    return results;
  }

    //Used when four are three balls in the robot
    public String fourBalls(String values){
      String results = "";
      if(values.charAt(3) == '1'){  
        m_indexerMotor.set(0);
        results += "indexer off, ";      
      } else{
        m_indexerMotor.set(.5);
        results += "indexer on, ";      
      }
      if(values.charAt(2) == '1' && values.charAt(1) == '1'){
        m_conveyorMotor.set(0);
        results += "conveyor off";    
      } else{
        m_conveyorMotor.set(.5);
        results += "conveyor on";    
      }    
      return results;
    }
    
  //takes the sensor values from the four manipulator sensors and runs a command based on the ball count 
  public String manipulate(int numberOfBalls, String sensorValues){
    switch(numberOfBalls){
      case 1: 
        return oneBall(sensorValues);
      case 2:
        return twoBalls(sensorValues);        
      case 3:
        return threeBalls(sensorValues);
      case 4:
        return fourBalls(sensorValues);
      default:
        return "Error";
    }

  }

  //Override if a ball gets stuck in the conveyor or indexer by reversing the motors
  public void reverseMotors(){
    m_conveyorMotor.set(-.5);
    m_indexerMotor.set(-.5);
  }

}
