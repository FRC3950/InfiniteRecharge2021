/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallCounterSubsystem;
import frc.robot.subsystems.BallManipulatorSubsystem;

public class BallHorizontalManipulatorCommand extends CommandBase {
  /**
   * Creates a new BallHorizontalManipulator.
   */

  public BallManipulatorSubsystem m_ballManipulatorSubsystem; 
  BallCounterSubsystem m_ballCounterSubsystem;
  boolean ballInIndexer;
  int ballsInRobot;
  String sensorValues;

  public BallHorizontalManipulatorCommand(BallManipulatorSubsystem ballManipulatorSubsystem) {
    m_ballManipulatorSubsystem = ballManipulatorSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(ballManipulatorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // double speed = .5;
    // int ballsInConveyer = m_ballCounterSubsystem.ballsInConveyer();
    // ballInIndexer = m_ballCounterSubsystem.isBallInIndexer();
    // m_ballManipulatorSubsystem.setConveyorMotor(speed, ballsInConveyer, ballInIndexer);
    // m_ballCounterSubsystem.isBallInIndexer();
    ballsInRobot = m_ballCounterSubsystem.ballsInConveyer();
    sensorValues = m_ballCounterSubsystem.getSensorValues();
    m_ballManipulatorSubsystem.manipulate(ballsInRobot, sensorValues);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ballManipulatorSubsystem.setConveyorMotor(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
