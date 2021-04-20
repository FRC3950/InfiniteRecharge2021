// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BallManipulatorSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GSCPlanBCommandGroup extends SequentialCommandGroup {
  /** Creates a new GSCPlanACommandGroup. */
  public GSCPlanBCommandGroup(DrivetrainSubsystem drivetrainSubsystem, BallManipulatorSubsystem ballManipulatorSubsystem, 
  IntakeSubsystem intakeSubsystem, GyroSubsystem gyroSubsystem) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new GSCPlanBDriveCommand(drivetrainSubsystem, gyroSubsystem, intakeSubsystem, ballManipulatorSubsystem),
      new GSCPlanBDriveCommand2(drivetrainSubsystem, gyroSubsystem, intakeSubsystem, ballManipulatorSubsystem),
      new GSCPlanBDriveCommand3(drivetrainSubsystem, gyroSubsystem, intakeSubsystem, ballManipulatorSubsystem),
      new GSCPlanBDriveCommand4(drivetrainSubsystem, gyroSubsystem, intakeSubsystem, ballManipulatorSubsystem),
      new GSCPlanBDriveCommand5(drivetrainSubsystem, gyroSubsystem, intakeSubsystem, ballManipulatorSubsystem),
      new GSCPlanBDriveCommand6(drivetrainSubsystem, gyroSubsystem, intakeSubsystem, ballManipulatorSubsystem),
      new GSCPlanBDriveCommand7(drivetrainSubsystem, gyroSubsystem, intakeSubsystem, ballManipulatorSubsystem)
    );
    
  }
}
