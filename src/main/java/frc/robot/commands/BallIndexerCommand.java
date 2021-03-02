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

public class BallIndexerCommand extends CommandBase {
  /**
   * Creates a new BallVerticalManipulatorCommand.
   */
  public BallManipulatorSubsystem m_ballManipulatorSubsystem;
  BallCounterSubsystem m_ballCounterSubsystem;

  public BallIndexerCommand(BallManipulatorSubsystem ballManipulatorSubsystem) {
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
    // boolean ballInIndexer = m_ballCounterSubsystem.isBallInIndexer();
    // double speed = .5;
    // int ballCount = m_ballCounterSubsystem.ballsInRobot();
    // m_ballManipulatorSubsystem.putBallInShooter(ballInIndexer, speed, ballCount);
    // System.out.println(m_ballCounterSubsystem.getEndConveyorSensorValue());
    m_ballCounterSubsystem.getEndConveyorSensorValue();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ballManipulatorSubsystem.setBallIndexerMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
