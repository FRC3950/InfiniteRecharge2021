// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveCommandJoysticks extends CommandBase {
  /** Creates a new DriveCommandJoysticks. */
  private final DrivetrainSubsystem m_drivetrain;
  private final Joystick drivestick1;
  private final Joystick drivestick2;

  public DriveCommandJoysticks(DrivetrainSubsystem subsystem, Joystick joy1, Joystick joy2) {
    // Use addRequirements() here to declare subsystem dependencies.

    drivestick1 = joy1;
    drivestick2 = joy2;
    m_drivetrain = subsystem;

    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_drivetrain.dualJoystickArcadeDrive(drivestick1.getY(), drivestick2.getX());

  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
