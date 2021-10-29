/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new IntakeSubsystem.
   */

  private final WPI_TalonFX m_intakeMotor;
  private final WPI_TalonSRX m_singulatorMotor;
  private final DoubleSolenoid m_intakeSolenoid = new DoubleSolenoid(1,0);

  public IntakeSubsystem() {

    m_intakeMotor = new WPI_TalonFX(4);
    m_singulatorMotor = new WPI_TalonSRX(6);


    m_singulatorMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //Sets the motor for the intake rollers to a certain speed
  public void setIntakeMotor(final double speed) {
    m_intakeMotor.set(speed);
    // System.out.println("Motor");
  }

  // Sets the motor for the mecanum intake wheels to a certain speed
  // Should it be negative?
  public void setSingulatorMotor(final double speed) {
    m_singulatorMotor.set(speed);
  }

  // Allows the driver to raise or lower the intake with a button
  public void changeIntakePosition() {
    if(m_intakeSolenoid.get() == DoubleSolenoid.Value.kReverse){
      m_intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    } else{
      m_intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
  }

  // Sets the intake to a position based on a boolean value
  public void setIntakePosition(boolean upOrDown) {
    if (upOrDown == true) {
      m_intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    } else {
      m_intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
  }

  // Checks whether the intake is in the raised position or the lowered position
  public boolean intakePosition() {
    if (m_intakeSolenoid.get() == DoubleSolenoid.Value.kReverse) {
      return true;
    } else {
      return false;
    }
  }

  // if there are too many balls in the robot it will reverse the motors to get
  // rid of balls
  public void reverseMotors() {
    m_singulatorMotor.set(-.5);
    m_intakeMotor.set(-.5);
  }

  // Turns off the ball intake when the conveyor is full and intake sensor is lit
  public boolean intakeBalls(final int ballCount, final int ballInIntake, final boolean intakePosition) {
    if(ballCount == 4 && ballInIntake == 1){
      return true;
    } else if(!intakePosition){
      return true;
    } else{
      setIntakeMotor(.75);
      setSingulatorMotor(.5); 
      return false;  
    }
  }

  public void autoBallIntakeMotors(int motors, boolean intakePosition){
    if((motors == 1 || motors == 2 || motors == 3) ){
      setIntakeMotor(.9);
      setSingulatorMotor(1);
    }else{
      setIntakeMotor(0);
      setSingulatorMotor(0);
    }
  }
}