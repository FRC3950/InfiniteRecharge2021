// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallManipulatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class OuttakeBallCommand extends CommandBase {
  /** Creates a new OuttakeBallCommand. */
  private final IntakeSubsystem m_intakeSubsystem;
  private final BallManipulatorSubsystem m_ballManipulatorSubsystem;
  public OuttakeBallCommand(IntakeSubsystem intakeSubsystem, BallManipulatorSubsystem ballManipulatorSubsystem ) {
    m_intakeSubsystem = intakeSubsystem;
    m_ballManipulatorSubsystem = ballManipulatorSubsystem;
    

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeSubsystem.setIntakeMotor(-1.0);
    m_intakeSubsystem.setSingulatorMotor(-1.0);
    m_ballManipulatorSubsystem.setBallIndexerMotor(-1.0);
    m_ballManipulatorSubsystem.setConveyorMotor(1.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeSubsystem.setIntakeMotor(0);
    m_intakeSubsystem.setSingulatorMotor(0);
    m_ballManipulatorSubsystem.setBallIndexerMotor(0);
    m_ballManipulatorSubsystem.setConveyorMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
