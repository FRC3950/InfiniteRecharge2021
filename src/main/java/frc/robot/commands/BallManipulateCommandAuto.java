/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallCounterSubsystem;
import frc.robot.subsystems.BallManipulatorSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class BallManipulateCommandAuto extends CommandBase {
  /**
   * Creates a new BallManipulateCommand.
   */
  public IntakeSubsystem m_intakeSubsystem;
  public BallManipulatorSubsystem m_ballManipulatorSubsystem;
  public BallCounterSubsystem m_ballCounterSubsystem;
  public DrivetrainSubsystem m_drivetrainSubsystem;

  int ballCount;
  String sensors;
  int motors;
  String startValues;
  Boolean finished;
  double encoderCount;

  public BallManipulateCommandAuto(IntakeSubsystem intakeSubsystem, BallManipulatorSubsystem ballManipulatorSubsystem, BallCounterSubsystem ballCounterSubsystem, DrivetrainSubsystem drivetrainSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intakeSubsystem = intakeSubsystem;
    m_ballManipulatorSubsystem = ballManipulatorSubsystem;
    m_ballCounterSubsystem = ballCounterSubsystem;
    m_drivetrainSubsystem = drivetrainSubsystem;
    // addRequirements(ballManipulatorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("ball manipulate");
    startValues = "2222";
    finished = false;
    m_drivetrainSubsystem.resetEncoderCount();
    encoderCount = m_drivetrainSubsystem.getEncoderCount();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
 //2021 Challenge
    // ballCount = Robot.ballCount;
    // System.out.println(ballCount);
    // sensors = m_ballCounterSubsystem.getSensorValues();
    // System.out.println(sensors);
    // motors = m_ballCounterSubsystem.getMotorsBasedOnBalls(sensors);
    // //SmartDashboard.putString("test" ,m_ballManipulatorSubsystem.manipulate(ballCount, sensors));
    // //SmartDashboard.putString("motors", "" + motors);
    // //System.out.println(motors);
    // if(sensors.charAt(0)== 0 && sensors.charAt(1)== 0 && sensors.charAt(2)== 0){
    //   m_intakeSubsystem.setIntakeMotor(0);
    //   m_intakeSubsystem.setSingulatorMotor(0);
    // }else{
    //   m_intakeSubsystem.autoBallIntakeMotors(motors, m_intakeSubsystem.intakePosition());
    // }
    // if(sensors.charAt(2) == '0' && (sensors.charAt(1)== '0' || ballCount == 2)){
    //   m_ballManipulatorSubsystem.setConveyorMotor(0);
    // }else{
    //   m_ballManipulatorSubsystem.setConveyorMotor(-1);
    // }


  //NOT FOR CHALLENGES 2021

  while(encoderCount < 800000){
    m_drivetrainSubsystem.motorSpeed(1);
    encoderCount = m_drivetrainSubsystem.getEncoderCount();
    //System.out.println("execute");
    sensors = m_ballCounterSubsystem.getSensorValues();
    //System.out.println(sensors);
    // motors = m_ballCounterSubsystem.getMotorsBasedOnBalls(sensors);
    //SmartDashboard.putString("test" ,m_ballManipulatorSubsystem.manipulate(ballCount, sensors));
    //SmartDashboard.putString("motors", "" + motors);
    if(sensors.charAt(0)== '0' && sensors.charAt(1)== '0' && sensors.charAt(2)== '0' && sensors.charAt(3)== '0'){
      m_intakeSubsystem.setIntakeMotor(0);
      m_intakeSubsystem.setSingulatorMotor(0);
    }else{
      m_intakeSubsystem.setIntakeMotor(.9);
      m_intakeSubsystem.setSingulatorMotor(1);
      //System.out.println("intke");
    }
    if(sensors.charAt(2)== '0' && sensors.charAt(3)== '0'){
      m_ballManipulatorSubsystem.setConveyorMotor(0);
    }else{
      m_ballManipulatorSubsystem.setConveyorMotor(-1);
      //System.out.println("convey");

    }
    if(sensors.charAt(3)== '0'){
      m_ballManipulatorSubsystem.setBallIndexerMotor(0);
    }else{
      m_ballManipulatorSubsystem.setBallIndexerMotor(1);
      //System.out.println("index");

    }
    //System.out.println("execute2");
  }
  finished = true;

  

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("end");
    m_intakeSubsystem.setIntakeMotor(0);
    m_intakeSubsystem.setSingulatorMotor(0);
    m_ballManipulatorSubsystem.setBallIndexerMotor(0);
    m_ballManipulatorSubsystem.setConveyorMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("finisjed");
    return finished;
  }
}
