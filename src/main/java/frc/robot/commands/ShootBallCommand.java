/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.BallCounterSubsystem;
import frc.robot.subsystems.BallManipulatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class ShootBallCommand extends CommandBase {
  /**
   * Creates a new ShootBallCommand.
   */

  public ShooterSubsystem m_shooterSubsystem;
  public BallManipulatorSubsystem m_ballManipulatorSubsystem;
  public BallCounterSubsystem m_ballCounterSubsystem;
  public IntakeSubsystem m_intakeSubsystem;
  public LimelightSubsystem m_limelightSubsystem;
  public TurretSubsystem m_turretSubsystem;
  boolean shoot;
  boolean finished;

  public ShootBallCommand(ShooterSubsystem shooterSubsystem, BallManipulatorSubsystem ballManipulatorSubsystem, BallCounterSubsystem ballCounterSubsystem, IntakeSubsystem intakeSubsystem, LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem){
    m_shooterSubsystem = shooterSubsystem;
    m_ballManipulatorSubsystem = ballManipulatorSubsystem;
    m_ballCounterSubsystem = ballCounterSubsystem;
    m_intakeSubsystem = intakeSubsystem;
    m_limelightSubsystem = limelightSubsystem;
    m_turretSubsystem = turretSubsystem;
    shoot = false;
    finished = false;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterSubsystem);
  }

  //Called when when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //int ballCount = m_ballCounterSubsystem.getBallsInRobot();
    double horizontalOffset = m_limelightSubsystem.getAngle();
    double distance = m_limelightSubsystem.calculateDistance();
    double speed = m_shooterSubsystem.setShooterSpeed(distance);
    // if (shoot){
    if (true){
      m_ballManipulatorSubsystem.setConveyorMotor(-.5);
      m_ballManipulatorSubsystem.setBallIndexerMotor(1);
      //m_shooterSubsystem.setShooterSpeed(distance);
      m_shooterSubsystem.setShooterMotor(-.75);
    }    
    if(Robot.ballCount == 0){
      Timer.delay(6); //may need to change this value
      finished = true;
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ballManipulatorSubsystem.setConveyorMotor(0);
    m_ballManipulatorSubsystem.setBallIndexerMotor(0);
    m_shooterSubsystem.setShooterMotor(0);   
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
