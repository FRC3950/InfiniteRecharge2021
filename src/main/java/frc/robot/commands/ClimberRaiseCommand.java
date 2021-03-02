/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberRaiseCommand extends CommandBase {
  /**
   * Creates a new ClimbCommand.
   */
  private boolean finished = false;
  private final ClimberSubsystem m_climberSubsystem;

  public ClimberRaiseCommand(ClimberSubsystem climberSubsystem) {
    m_climberSubsystem = climberSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climberSubsystem.setLockGear(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    finished = m_climberSubsystem.raiseClimber();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climberSubsystem.setClimberMotor(0);  
    m_climberSubsystem.setLockGear(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
