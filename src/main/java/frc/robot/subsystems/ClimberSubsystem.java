/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
  /**
   * Creates a new ClimberSubsystem.
   */
  private final WPI_TalonFX m_climberMotor;
  private final WPI_TalonFX m_climberMotorFollower;
  private final DoubleSolenoid m_climberSolenoid;
  private final DigitalInput m_climberLimitSwitch;
  
  public ClimberSubsystem() {
    
    m_climberMotor = new WPI_TalonFX(13);
    m_climberMotorFollower = new WPI_TalonFX(12);
    
    m_climberSolenoid = new DoubleSolenoid(4,5);

    m_climberLimitSwitch = new DigitalInput(7);
    m_climberMotorFollower.follow(m_climberMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //Switches the position of the gear from whatever position it is to the opposite position
  public void toggleLockGear(){
    if (m_climberSolenoid.get() == DoubleSolenoid.Value.kForward){
      m_climberSolenoid.set(DoubleSolenoid.Value.kReverse);
    } else{
      m_climberSolenoid.set(DoubleSolenoid.Value.kForward);
    }
  }

  //Sets the gear to whatever position you choose based off of a boolean value the user sets
  public void setLockGear(boolean s) {
    m_climberSolenoid.set(s ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse); //One line if statement
  }

  //Sets the motor for the climber to a certain  speed
  public void setClimberMotor(double speed){
    m_climberMotor.set(speed);
  }
  
  //Sets the value of the climber motor encoder to zero
  public void resetEncoderValue(){
    m_climberMotor.setSelectedSensorPosition(0);
  }
  
  //Checks the climber motor encoder value to see if the climber is fully extended
  public boolean raiseClimber(){
    if(m_climberLimitSwitch.get() && m_climberMotor.getSelectedSensorPosition() != 0){
      resetEncoderValue();
    }
    boolean climberAtTop = m_climberMotor.getSelectedSensorPosition() >= 18000; //NEED TO FIX WHEN WE TEST CLIMBER
    if(climberAtTop){ 
      setClimberMotor(0);
      return true;
    }else{
      setClimberMotor(-1);
      return false;
    }
  }
  
  //Checks to see if the climber is at the bottom position by seeing if the climber limit switch is set off
  //Safety check to make sure that if the climber is at the bottom it turns the motor off
  public boolean lowerClimber(){
    //boolean climberAtBottom = m_climberLimitSwitch.get();
    boolean climberAtBottom = false;
    if(climberAtBottom){
      setClimberMotor(0);
      return true;
    }else{
      setClimberMotor(1);
      return false;
    }
    
  }
}
