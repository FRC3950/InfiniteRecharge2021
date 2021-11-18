/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoDriveBackwardCommand;
import frc.robot.commands.AutoDriveForwardCommand;
import frc.robot.commands.BUTTONShootBallCommand;
import frc.robot.commands.BallManipulateCommand;
import frc.robot.commands.IntakeLiftCommand;
import frc.robot.commands.TurretSetAngleCommand;
import frc.robot.subsystems.BallManipulatorSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

import frc.robot.RobotContainer;

import com.analog.adis16470.frc.ADIS16470_IMU; //Gyroscope


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;


  private RobotContainer m_robotContainer;
  private DrivetrainSubsystem drivetrainSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private BallManipulatorSubsystem ballManipulatorSubsystem;
  private IntakeSubsystem intakeSubsystem;
  private LimelightSubsystem limelightSubsystem;
  private TurretSubsystem turretSubsystem;
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  
  private String m_autoSelected;
  private final SendableChooser<String> m_autoChooser = new SendableChooser<>();

  private static final String kYawDefault = "Z-Axis"; // Rotation
  private static final String kYawXAxis = "X-Axis"; 
  private static final String kYawYAxis = "Y-Axis"; 
  private String m_yawSelected;
  private ADIS16470_IMU.IMUAxis m_yawActiveAxis;
  private final SendableChooser<String> m_yawChooser = new SendableChooser<>();

  private boolean m_runCal = false;
  private boolean m_configCal = false;
  private boolean m_reset = false;
  private boolean m_setYawAxis = false;
  private double m_decRate = 4;
  private boolean m_setDecRate = false;

  public static final ADIS16470_IMU m_imu = new ADIS16470_IMU();

  public DigitalInput entrySensor = new DigitalInput(0); //sensor at singulator. May need to change channel
  public DigitalInput initialConveyorSensor = new DigitalInput(1); //sensor right after singulator. May need to change channel
  public DigitalInput endConveyorSensor = new DigitalInput(2);
  public DigitalInput indexerSensor = new DigitalInput(3);

  public static int ourFieldPosition = 0;

  public static int ballCount = 0;
  public static boolean toggleEntryState = true;
  public static int ballsInConveyer; 

  public static boolean previousEntryValue = true;
  public static boolean previousIndexerValue;

  public static boolean entrySensorValue;
  public static boolean endConveyorSensorValue;
  public static boolean indexerSensorValue;
  public static boolean startConveyorSensorValue;

 // public static int ballCount;

  String startValues = "2222";
  SendableChooser<String> fieldPosition = new SendableChooser<>();
  SendableChooser<Integer> startingBallCount = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.


    m_robotContainer = new RobotContainer();
    m_robotContainer.m_gyroSubsystem.resetAngle();

    fieldPosition = new SendableChooser<String>();
		fieldPosition.setDefaultOption("Right Wall", new String("Right Wall"));
		fieldPosition.addOption("Power Port", new String("Power Port"));
    fieldPosition.addOption("Loading Bay", new String("Loading Bay"));
    SmartDashboard.putData("fieldPosition", fieldPosition);
    
    startingBallCount.setDefaultOption("0", 0);
    startingBallCount.addOption("1", 1);
    startingBallCount.addOption("2", 2);
    startingBallCount.addOption("3", 3);

    SmartDashboard.putData("startingBallCount", startingBallCount);

    
    
    ballCount = startingBallCount.getSelected();




   



    m_autoChooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_autoChooser.addOption("My Auto", kCustomAuto);
   // m_yawChooser.setDefaultOption("Z-Axis", kYawDefault);
    //m_yawChooser.addOption("X-Axis", kYawXAxis);
    //m_yawChooser.addOption("Y-Axis", kYawYAxis);
    


    previousIndexerValue = indexerSensor.get();
    

    
    
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
    System.out.println(ballCount);
    // System.out.println("The Count Periodically is: ");
    // System.out.println(ballCount);

    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.

    CommandScheduler.getInstance().run();
    // SmartDashboard.putNumber("NumberBalls", m_robotContainer.m_ballCounterSubsystem.getBallCount());
    // SmartDashboard.putBoolean("end conveyor values", m_robotContainer.m_ballCounterSubsystem.endConveyorSensor.get());
    // SmartDashboard.putBoolean("entry values", m_robotContainer.m_ballCounterSubsystem.entrySensor.get());
    // SmartDashboard.putBoolean("indexer values", m_robotContainer.m_ballCounterSubsystem.indexerSensor.get());
    // SmartDashboard.putBoolean("initial conveyor values", m_robotContainer.m_ballCounterSubsystem.initialConveyorSensor.get());
    //SmartDashboard.putString("sensor values", m_robotContainer.m_ballCounterSubsystem.getSensorValues());
    
    SmartDashboard.putNumber("distance from target", m_robotContainer.m_limelightSubsystem.calculateDistance());
    SmartDashboard.putBoolean("left limit switch", m_robotContainer.m_turretSubsystem.getLeftLimit());
    SmartDashboard.putBoolean("right limit switch", m_robotContainer.m_turretSubsystem.getRightLimit());
    SmartDashboard.putNumber("velocity", m_robotContainer.m_shooterSubsystem.getVelocityFromEncoder());
    SmartDashboard.putNumber("rpm", m_robotContainer.m_shooterSubsystem.getRPM());
    
    SmartDashboard.putNumber("YawAngle", m_imu.getAngle()); //Angle of yaw 
    SmartDashboard.putNumber("GyroInstantZ", m_imu.getGyroInstantZ()); //Rate of change 
    SmartDashboard.putNumber(("DecRate"), m_decRate); //
    SmartDashboard.putNumber("ball count", ballCount);
   

    /*if(startValues.charAt(1) == '1' && m_robotContainer.m_ballCounterSubsystem.getSensorValues().charAt(1) == '0' && m_robotContainer.m_ballManipulatorSubsystem.getConveyorMotor() < 0){
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
    }*/
    // System.out.println(startValues.charAt(1));
   
    // startValues = m_robotContainer.m_ballCounterSubsystem.getSensorValues();

    entrySensorValue = entrySensor.get();
    startConveyorSensorValue = (boolean) initialConveyorSensor.get(); //initial conveyor sensor
    endConveyorSensorValue = endConveyorSensor.get();
    indexerSensorValue = indexerSensor.get();
    SmartDashboard.putBoolean("Entry sensor", entrySensorValue);
    SmartDashboard.putBoolean("Initial conveyor sensor", startConveyorSensorValue);
    SmartDashboard.putBoolean("End conveyor sensor", endConveyorSensorValue);
    SmartDashboard.putBoolean("Indexer sensor value", indexerSensorValue);

    
    //System.out.println(initialConveyorSensorValue);

    // if(!initialConveyorSensorValue){
    //   toggleEntryState = false;
    //   if(initialConveyorSensorValue){
    //     toggleEntryState = true;
    //     ballCount++;

   
      // true = no ball 
    if (previousEntryValue == true && startConveyorSensorValue == false) {
      previousEntryValue = false;
      ballCount++;
    }
    if(initialConveyorSensor.get() == true && previousEntryValue == false){
      previousEntryValue = true; 
    }
    if (previousIndexerValue == false  && indexerSensor.get() == true) {
       previousIndexerValue = true; 
       ballCount--;
    }
    if(indexerSensor.get() == false && previousIndexerValue == true ){
      previousIndexerValue = false;
    }
    

    

    

   // Start values = 2222 //
      
    //System.out.println(ballCount);



/* Commands to fix later: ballManipulate, intake, ballOverride, all autonomous commands and command groups  */    


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
    // new AutoDriveForwardCommand(drivetrainSubsystem);
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // ballCount = m_robotContainer.m_ballCounterChooser.getSelected();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    // if(fieldPosition.getSelected().compareTo("Power Port") == 0) {
		// 	ourFieldPosition = 1;
		// } else if(fieldPosition.getSelected().compareTo("Loading Bay") == 0) {
		// 	ourFieldPosition = 2;
		// } else {
		// 	ourFieldPosition = 3;
		// }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // new SequentialCommandGroup(      
    //     new TurretSetAngleCommand(limelightSubsystem, turretSubsystem), 
    //     new BUTTONShootBallCommand(ballManipulatorSubsystem, shooterSubsystem, intakeSubsystem, limelightSubsystem),
    //     new IntakeLiftCommand(intakeSubsystem),
    //     new ParallelDeadlineGroup(
           //new AutoDriveForwardCommand(drivetrainSubsystem);
    //       new BallManipulateCommand(intakeSubsystem, ballManipulatorSubsystem, ballCounterSubsystem)
    //     ),
    //       new AutoDriveBackwardCommand(drivetrainSubsystem),
    //     new BUTTONShootBallCommand(ballManipulatorSubsystem, shooterSubsystem, intakeSubsystem, limelightSubsystem),
    //     new TurretSetAngleCommand(limelightSubsystem, turretSubsystem)
    //   );
  }

  @Override
  public void teleopInit() {
    // ballCount = m_robotContainer.m_ballCounterChooser.getSelected();

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
    // ballCount = m_robotContainer.m_ballCounterChooser.getSelected();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
