/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallManipulatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class BUTTONShootBallCommand extends CommandBase {
  /**
   * Creates a new BUTTONShootBallCommand.
   */
  public BallManipulatorSubsystem m_ballManipulatorSubsystem;
  public ShooterSubsystem m_shooterSubsystem;
  public IntakeSubsystem m_intakeSubsystem;
  public LimelightSubsystem m_limelightSubsystem;
  public boolean finished;

  public BUTTONShootBallCommand(BallManipulatorSubsystem ballManipulatorSubsystem, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, LimelightSubsystem limelightSubsystem){
    m_ballManipulatorSubsystem = ballManipulatorSubsystem;
    m_shooterSubsystem = shooterSubsystem;
    m_intakeSubsystem = intakeSubsystem;
    m_limelightSubsystem = limelightSubsystem;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    double setSpeed = m_limelightSubsystem.getVerticalAngle() * 295 + 30719;
    //System.out.println("SET" + setSpeed + "   ACTUAL" + m_shooterSubsystem.getVelocityFromEncoder());
    m_shooterSubsystem.setShooterMotor(setSpeed);  
  // m_shooterSubsystem.setShooterMotor(33000); //disable this for vision

    //*insert if statement here to ensure the shooter motor is at a close enough speed to the set speed, and the turret is centered on target)
    // if(current speed is close enough to the set speed AND the turret is centered close enough to 0 degrees)
    if(m_shooterSubsystem.getVelocityFromEncoder() < -1 * setSpeed - 1400 && (m_limelightSubsystem.getAngle() > -1 || m_limelightSubsystem.getAngle() < 1)){
      m_intakeSubsystem.setSingulatorMotor(.4);
      m_ballManipulatorSubsystem.setConveyorMotor(-1);
      m_ballManipulatorSubsystem.setBallIndexerMotor(1); 
      m_intakeSubsystem.setIntakeMotor(1); 
    }
    //System.out.println("SET" + setSpeed + "   ACTUAL" + m_shooterSubsystem.getVelocityFromEncoder());
    // if(m_ballCounterSubsystem.getBallCount() == 0){
    //   finished = true;
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ballManipulatorSubsystem.setConveyorMotor(0);
    m_ballManipulatorSubsystem.setBallIndexerMotor(0);
    m_shooterSubsystem.setShooterMotor(0);
    m_intakeSubsystem.setSingulatorMotor(0);
    m_intakeSubsystem.setIntakeMotor(0);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
