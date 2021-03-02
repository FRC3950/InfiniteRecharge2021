/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallCounterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDriveToBallCommand extends CommandBase {
  /**
   * Creates a new AutoPickUpBallCommand.
   */
  private final DrivetrainSubsystem m_drivetrainSubsystem;
  private final BallCounterSubsystem m_ballCounterSubsystem;
  public AutoDriveToBallCommand(DrivetrainSubsystem drivetrainSubsystem, BallCounterSubsystem ballCounterSubsystem, int fieldPosition){
    m_drivetrainSubsystem = drivetrainSubsystem;
    m_ballCounterSubsystem = ballCounterSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrainSubsystem);    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_drivetrainSubsystem.autoDriveToBall(Robot.ourFieldPosition, m_ballCounterSubsystem.ballCounter(m_ballCounterSubsystem.getSensorValues().charAt(1),  m_ballCounterSubsystem.getSensorValues().charAt(3)), m_ballCounterSubsystem.getEntrySensorValue());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
