/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

public class IntakeLiftCommand extends CommandBase {
  /**
   * Creates a new IntakeLiftCommand.
   */
  
  private final IntakeSubsystem m_intakeSubsystem;
 
  public IntakeLiftCommand(IntakeSubsystem intakeSubsystem) {
    m_intakeSubsystem = intakeSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeSubsystem.changeIntakePosition();
    // position = m_intakeSubsystem.m_intakeSolenoid.get().toString();
    // System.out.println(position);
    // if(m_intakeSubsystem.m_intakeSolenoid.get() == Value.kReverse){
    //   m_intakeSubsystem.m_intakeSolenoid.set(Value.kForward);
    //   System.out.println(m_intakeSubsystem.m_intakeSolenoid.get());
    //   finished = true;
    // } else {
    //   System.out.println("R" + m_intakeSubsystem.m_intakeSolenoid.get());
    //   finished = true;
    // }    
    //finished = m_intakeSubsystem.changeIntakePosition();
    // System.out.println("execute");
    // if(position == "kForward" || position == "kOff"){
    //   m_intakeSubsystem.setIntakePosition(true);
    //   System.out.println("kForward to kREverse");

    //   finished = true;
    // } else if (position == "kReverse"){
    //   m_intakeSubsystem.setIntakePosition(false);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("end");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
