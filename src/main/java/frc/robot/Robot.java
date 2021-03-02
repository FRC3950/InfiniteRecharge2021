/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public static int ourFieldPosition = 0;
  public static int ballCount;
  String startValues = "2222";
  SendableChooser<String> fieldPosition = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    fieldPosition = new SendableChooser<String>();
		fieldPosition.setDefaultOption("Right Wall", new String("Right Wall"));
		fieldPosition.addOption("Power Port", new String("Power Port"));
    fieldPosition.addOption("Loading Bay", new String("Loading Bay"));
    SmartDashboard.putData("fieldPosition", fieldPosition);

    
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putBoolean("end conveyor values", m_robotContainer.m_ballCounterSubsystem.endConveyorSensor.get());
    SmartDashboard.putBoolean("entry values", m_robotContainer.m_ballCounterSubsystem.entrySensor.get());
    SmartDashboard.putBoolean("indexer values", m_robotContainer.m_ballCounterSubsystem.indexerSensor.get());
    SmartDashboard.putBoolean("initial conveyor values", m_robotContainer.m_ballCounterSubsystem.initialConveyorSensor.get());
    SmartDashboard.putString("sensor values", m_robotContainer.m_ballCounterSubsystem.getSensorValues());
    SmartDashboard.putNumber("ball count",ballCount);
    SmartDashboard.putNumber("distance from target", m_robotContainer.m_limelightSubsystem.calculateDistance());
    SmartDashboard.putBoolean("left limit switch", m_robotContainer.m_turretSubsystem.getLeftLimit());
    SmartDashboard.putBoolean("right limit switch", m_robotContainer.m_turretSubsystem.getRightLimit());
    SmartDashboard.putNumber("velocity", m_robotContainer.m_shooterSubsystem.getVelocityFromEncoder());
    SmartDashboard.putNumber("rpm", m_robotContainer.m_shooterSubsystem.getRPM());

    
    if(startValues.charAt(1) == '1' && m_robotContainer.m_ballCounterSubsystem.getSensorValues().charAt(1) == '0' && m_robotContainer.m_ballManipulatorSubsystem.getConveyorMotor() < 0){
      ballCount++;
    }
    if(startValues.charAt(3) == '0' && m_robotContainer.m_ballCounterSubsystem.getSensorValues().charAt(3) == '1' && m_robotContainer.m_ballManipulatorSubsystem.getBallIndexerMotor() > 0){
      ballCount--;
    }
    if(startValues.charAt(1) == '1' && m_robotContainer.m_ballCounterSubsystem.getSensorValues().charAt(1) == '0' && m_robotContainer.m_ballManipulatorSubsystem.getConveyorMotor() > 0){
      ballCount--;
    }
    if(startValues.charAt(3) == '0' && m_robotContainer.m_ballCounterSubsystem.getSensorValues().charAt(3) == '1' && m_robotContainer.m_ballManipulatorSubsystem.getBallIndexerMotor() < 0){
      ballCount++;
    }
    //System.out.println(ballCount);
    startValues = m_robotContainer.m_ballCounterSubsystem.getSensorValues();


  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // ballCount = m_robotContainer.m_ballCounterChooser.getSelected();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    if(fieldPosition.getSelected().compareTo("Power Port") == 0) {
			ourFieldPosition = 1;
		} else if(fieldPosition.getSelected().compareTo("Loading Bay") == 0) {
			ourFieldPosition = 2;
		} else {
			ourFieldPosition = 3;
		}
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {
    ballCount = m_robotContainer.m_ballCounterChooser.getSelected();

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    //ballCount = m_robotContainer.m_ballCounterChooser.getSelected();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
