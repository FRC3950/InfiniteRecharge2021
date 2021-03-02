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
import frc.robot.subsystems.IntakeSubsystem;

public class BallOverrideCommand extends CommandBase {
  /**
   * Creates a new BallOverrideCommand.
   */

  public BallManipulatorSubsystem m_ballManipulatorSubsystem;
  public BallCounterSubsystem m_ballCounterSubsystem;
  public IntakeSubsystem m_intakeSubsystem;
  
  public BallOverrideCommand(BallManipulatorSubsystem ballManipulatorSubsystem, BallCounterSubsystem ballCounterSubsystem, IntakeSubsystem intakeSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ballManipulatorSubsystem = ballManipulatorSubsystem;
    m_ballCounterSubsystem = ballCounterSubsystem;
    m_intakeSubsystem = intakeSubsystem;
    addRequirements(ballManipulatorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ballManipulatorSubsystem.setConveyorMotor(.5);
    m_ballManipulatorSubsystem.setBallIndexerMotor(-1);
    m_intakeSubsystem.setSingulatorMotor(-1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ballManipulatorSubsystem.setConveyorMotor(0);
    m_ballManipulatorSubsystem.setBallIndexerMotor(0);
    m_intakeSubsystem.setSingulatorMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
