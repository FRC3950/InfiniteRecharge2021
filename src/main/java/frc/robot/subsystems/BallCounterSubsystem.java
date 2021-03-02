/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallCounterSubsystem extends SubsystemBase {
  /**
   * Creates a new BallCounterSubsystem.
   */
  public DigitalInput entrySensor = new DigitalInput(0); //sensor at singulator. May need to change channel
  public DigitalInput initialConveyorSensor = new DigitalInput(1); //sensor right after singulator. May need to change channel
  public DigitalInput endConveyorSensor = new DigitalInput(2);
  public DigitalInput indexerSensor = new DigitalInput(3);
  //sensor right before indexer. May need to change channel
  private int ballCount; // initial ball sensor and indexer ball sensor contribute to ball count
  private int ballsInConveyer;
  private boolean previousEntryValue;
  private boolean previousIndexerValue;


  public BallCounterSubsystem() {
    ballCount = 0;
  }

  //Gets a value for the ball sensors every 50 ms
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
      if (previousEntryValue == true && entrySensor.get() == false) {
        ballCount++;
      }
      if (previousIndexerValue && !indexerSensor.get()) {
        ballCount--;
      }
      previousEntryValue = entrySensor.get();
      previousIndexerValue = indexerSensor.get();
      
  }
  
  //returns the entry ball sensors value
  public int getEntrySensorValue(){
    return (entrySensor.get() ? 1 : 0);
  }

  //returns the initial conveyor sensors value
  public int getInitialConveyorSensorValue(){
    return (initialConveyorSensor.get() ? 1 : 0) ;
  }

  //returns the end conveyor sensors value
  public int getEndConveyorSensorValue(){
    return (endConveyorSensor.get() ? 1 : 0);
  }

  //returns the end conveyor sensors value
  public int getIndexerSensorValue(){
    return (indexerSensor.get() ? 1 : 0);
  }

  //Gets the values of the four sensors and makes their values into a string
  public String getSensorValues(){
    final String sensorValues = "" + getEntrySensorValue() + getInitialConveyorSensorValue() + getEndConveyorSensorValue() + getIndexerSensorValue(); 
    return sensorValues;
  }

  //Determines how many balls are inside the robot by comparing the current sensor value to the previous sensor value with a 50 ms delay
  // public int getBallsInRobot(){
  //   if(getSensorValues() == "1111"){
  //     ballCount = 5;
  //   }else if(getSensorValues() == "0000"){
  //     ballCount = 0;
  //   }
  //   return ballCount;
  // }

  //0 = all off, 1 = intake on, 2 = conveyor and intake on, 3 = all on
  public int getMotorsBasedOnBalls(String sensorValues){

    if(sensorValues.equals("0000")){
      return 0;
    }else if(sensorValues.equals("1110") || sensorValues.equals("1100") || sensorValues.equals("1010") || sensorValues.equals("1000") || sensorValues.equals("0110") || sensorValues.equals("0100") || sensorValues.equals("0110")|| sensorValues.equals("0010")){
      return 2;
    }else if(sensorValues.equals("1111") || sensorValues.equals("1101") || sensorValues.equals("1011") || sensorValues.equals("1001")|| sensorValues.equals("0111") || sensorValues.equals("0101") || sensorValues.equals("0011")|| sensorValues.equals("0001")){
      return 3;
    }
    return 3;

    //NOT FOR COMP 2021
    // if(sensorValues.equals("0000")){
    //   return 0;
    // }else if(sensorValues.equals("1110") || sensorValues.equals("1100") || sensorValues.equals("1010") || sensorValues.equals("1000") || sensorValues.equals("0110") || sensorValues.equals("0100") || sensorValues.equals("0110")|| sensorValues.equals("0010")){
    //   return 2;
    // }else if(sensorValues.equals("1111") || sensorValues.equals("1101") || sensorValues.equals("1011") || sensorValues.equals("1001")|| sensorValues.equals("0111") || sensorValues.equals("0101") || sensorValues.equals("0011") || sensorValues.equals("0001")){
    //   return 3;
    // }
    // return 3;
  }

  //Determines if there is a ball in the indexer 
  public boolean isBallInIndexer(){
    return indexerSensor.get();
  }

  //Determines how many balls are in the conveyer belt 
  public int ballsInConveyer(){
    ballsInConveyer = ballCount;
    if(isBallInIndexer()){
      ballsInConveyer--;
    }
    return ballsInConveyer;
  }

  //Resets the ball count to zero
  public void resetBallCount(){
    ballCount = 0;
  }

  public int ballCounter(char initialEnterValue, char initalExitValue){
    int count = 0;
    char currentEnterValue = getSensorValues().charAt(1);
    char currentExitValue = getSensorValues().charAt(3);
    if(currentEnterValue == '1' && initialEnterValue == '0'){
      count++;
    }
    if(currentExitValue == '0' && initalExitValue == '1'){
      count--;
    }
    return count;
  }
}
