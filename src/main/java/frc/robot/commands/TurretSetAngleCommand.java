/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class TurretSetAngleCommand extends CommandBase {
  /**
   * Creates a new LimelightSkewCommand.
   */
  private final LimelightSubsystem m_limelightSubsystem;
  private final TurretSubsystem m_turretSubsystem;
  private boolean finished;
  private final double Kp = -.105;
  private final double setPointIncrement = .05;

  public TurretSetAngleCommand(LimelightSubsystem limelightSubsystem, TurretSubsystem turretSubsystem) {
    m_limelightSubsystem = limelightSubsystem;
    m_turretSubsystem = turretSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(limelightSubsystem);
    //addRequirements(turretSubsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finished = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_turretSubsystem.setRelativeAngle(m_limelightSubsystem.getAngle());
    //System.out.println(m_limelightSubsystem.getTarget());
    double angle = m_limelightSubsystem.getAngle();
    double changeInSetPoint = 0;
    if(angle > 1 && m_limelightSubsystem.getTarget()){
      changeInSetPoint = Kp*angle - setPointIncrement;
      //System.out.println("positive");
    }else if(angle < -1 && m_limelightSubsystem.getTarget()){
      changeInSetPoint = Kp*angle + setPointIncrement;
      //System.out.println("negative");
    }
    m_turretSubsystem.turretSpin(changeInSetPoint);
    // if(m_limelightSubsystem.getAngle() < 1 && m_limelightSubsystem.getAngle() > -1){
    //   finished = true;
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_turretSubsystem.turretSpin(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
