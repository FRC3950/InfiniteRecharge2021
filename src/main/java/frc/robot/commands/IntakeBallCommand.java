/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallCounterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.BallManipulatorSubsystem;

public class IntakeBallCommand extends CommandBase {
  /**
   * Creates a new IntakeBallCommand.
   */

   private final IntakeSubsystem m_intakeSubsystem;
   private final BallCounterSubsystem m_ballCounterSubsystem;
   private final BallManipulatorSubsystem m_ballManipulatorSubsystem;
   int ballsInRobot;
   private boolean finished;
   

  public IntakeBallCommand(IntakeSubsystem intakeSubsystem, BallCounterSubsystem ballCounterSubsystem, BallManipulatorSubsystem ballManipulatorSubsystem){
    m_intakeSubsystem = intakeSubsystem;
    m_ballCounterSubsystem = ballCounterSubsystem;
    m_ballManipulatorSubsystem = ballManipulatorSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSubsystem);
    SmartDashboard.putString("intake" ,"");

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {    
    m_intakeSubsystem.setIntakePosition(false);//Might need to change to false
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int ballCount = m_ballCounterSubsystem.ballCounter();
    //int ballInIntake = m_ballCounterSubsystem.getEntrySensorValue();
    //boolean intakePosition = m_intakeSubsystem.intakePosition();
    
   

    if(m_ballCounterSubsystem. < 4 && m_ballCounterSubsystem.isBallInIndexer() == true){
      m_intakeSubsystem.setIntakeMotor(1.0);
      m_intakeSubsystem.setSingulatorMotor(1.0);
      m_ballManipulatorSubsystem.setBallIndexerMotor(1.0);
      m_ballManipulatorSubsystem.setConveyorMotor(-1.0);
    }

    else if(m_ballCounterSubsystem.ballsInConveyer() == 4 && m_ballCounterSubsystem.isBallInIndexer() == false){
      m_intakeSubsystem.setIntakeMotor(1.0);
      m_intakeSubsystem.setSingulatorMotor(1.0);
      m_ballManipulatorSubsystem.setBallIndexerMotor(0);
      m_ballManipulatorSubsystem.setConveyorMotor(-1.0);
    } 
    else if(m_ballCounterSubsystem.ballsInConveyer() == 4 && m_ballCounterSubsystem.isBallInSingulator() == true){
      m_intakeSubsystem.setIntakeMotor(1.0);
      m_intakeSubsystem.setSingulatorMotor(1.0);
    } 

    else if(m_ballCounterSubsystem.ballsInConveyer() == 4 && m_ballCounterSubsystem.isBallInSingulator() == false){
      m_intakeSubsystem.setIntakeMotor(0);
      m_intakeSubsystem.setSingulatorMotor(0);
      m_ballManipulatorSubsystem.setBallIndexerMotor(0);
      m_ballManipulatorSubsystem.setConveyorMotor(0);
    }



      
    // insert a way to turn on the singulator, conveyor, and indexer until indexer detects a ball, then only run the singulator and conveyor 
    //finished = m_intakeSubsystem.intakeBalls(ballCount, ballInIntake, intakePosition);
    //m_intakeSubsystem.autoBallIntakeMotors(m_ballCounterSubsystem.getMotorsBasedOnBalls(), intakePosition);
    SmartDashboard.putString("intake" ,"on");

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    

    //m_intakeSubsystem.setIntakePosition(false);
    SmartDashboard.putString("intake" ," off");

  }

  // Returns true when the command should end.\\


  @Override
  public boolean isFinished() {
    return true;
  }
}
