/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {
  /**
   * Creates a new TurretSubsystem.
   */
  private final WPI_TalonSRX m_turretMotor;
  private final DigitalInput m_leftPosition = new DigitalInput(4);
  private final DigitalInput m_rightPosition = new DigitalInput(5);
 

/** Hey guys whats up hally here yall are dumb haha bye */
  public TurretSubsystem() {

    m_turretMotor = new WPI_TalonSRX(11);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //Sets the turret motor to a certain velocity
  public void turretSpin(double speed){
    m_turretMotor.set(speed);
    if(m_leftPosition.get() == false){
      m_turretMotor.set(0);
    } 
    if (m_rightPosition.get() == false){
      m_turretMotor.set(0);
      }
  }

  //Makes sure that if the turret begins to turn to far it will stop based off limit switches
  public void turretLimit(){
  if(m_leftPosition.get() == true){
    m_turretMotor.set(0);
  } if (m_rightPosition.get() == true){
    m_turretMotor.set(0);
    }
  }

  //Sets the robot to move a certain direction based off the horizontal offset of the shooter from the target 
  // and has it turn until the offset is within 1 degree to either side  of the target
  public void setRelativeAngle(double offset){
    if (offset > 1){
      m_turretMotor.set(.15);
    } else if(offset < -1){
      m_turretMotor.set(-.15);
    }else{
      m_turretMotor.set(0);
    }
  }
  public boolean getLeftLimit(){
    return m_leftPosition.get();
  }
  public boolean getRightLimit(){
    return m_rightPosition.get();
  }
}
