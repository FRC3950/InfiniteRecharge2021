// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallCounterSubsystem;
import frc.robot.subsystems.BallManipulatorSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class GSCPlanADriveCommand7 extends CommandBase {
  /** Creates a new GSCPlanADriveCommand. */
  private final DrivetrainSubsystem m_drivetrainSubsystem;
  private final GyroSubsystem m_gyroSubsystem;
  public final IntakeSubsystem m_intakeSubsystem;
  public final BallManipulatorSubsystem m_ballManipulatorSubsystem;
  Boolean redOrBlue;
  //redOrBlue true = red, false = blue
  double encoderCount;
  int ballCount;
  Boolean finished = false;
  int count;

  public GSCPlanADriveCommand7(DrivetrainSubsystem drivetrainSubsystem, GyroSubsystem gyroSubsystem, IntakeSubsystem intakeSubsystem, BallManipulatorSubsystem ballManipulatorSubsystem) {

    m_drivetrainSubsystem = drivetrainSubsystem;
    m_gyroSubsystem = gyroSubsystem;
    m_intakeSubsystem = intakeSubsystem;
    m_ballManipulatorSubsystem = ballManipulatorSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrainSubsystem.resetEncoderCount();
    encoderCount = m_drivetrainSubsystem.getEncoderCount();
    if(Robot.ballCount != 0){
      redOrBlue = false;
      System.out.println("red");
    }else{
      redOrBlue = true;        
      System.out.println("blue");
    }
    count = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeSubsystem.setIntakeMotor(1);
    m_intakeSubsystem.setSingulatorMotor(1);
    encoderCount = m_drivetrainSubsystem.getEncoderCount();
    if(encoderCount < 450000){
      m_drivetrainSubsystem.motorSpeed(0.7);
    }else{
      finished = true;
    }


  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeSubsystem.setIntakeMotor(0);
    m_intakeSubsystem.setSingulatorMotor(0);
    m_ballManipulatorSubsystem.setConveyorMotor(0);
    m_drivetrainSubsystem.motorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
