/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;



import frc.robot.commands.*;

import frc.robot.subsystems.*;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final BallManipulatorSubsystem m_ballManipulatorSubsystem = new BallManipulatorSubsystem();
  public final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  public final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  public final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  public final LimelightSubsystem m_limelightSubsystem = new LimelightSubsystem();
  public final ShooterSubsystem m_shooterSubsystem  = new ShooterSubsystem();
  public final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
  public final GyroSubsystem m_gyroSubsystem = new GyroSubsystem();
  

  //public final AutonomousCommandGroup2020 m_autonomousCommandGroup2020 = new AutonomousCommandGroup2020(m_drivetrainSubsystem, m_shooterSubsystem, m_ballManipulatorSubsystem, m_ballCounterSubsystem, m_intakeSubsystem, m_limelightSubsystem, m_turretSubsystem);
  

  public final AutoDriveForwardCommand m_autoDriveForwardCommand = new AutoDriveForwardCommand(m_drivetrainSubsystem);
  public final AutoDriveBackwardCommand m_autoDriveBackwardCommand = new AutoDriveBackwardCommand(m_drivetrainSubsystem);
  public final BallOverrideCommand m_ballOverrideCommand = new BallOverrideCommand(m_ballManipulatorSubsystem, m_intakeSubsystem);
  public final BallManipulateCommand m_ballManipulateCommand = new BallManipulateCommand(m_intakeSubsystem, m_ballManipulatorSubsystem);
  //public final BallManipulateCommandAuto m_ballManipulateCommandAuto = new BallManipulateCommandAuto(m_intakeSubsystem, m_ballManipulatorSubsystem, m_ballCounterSubsystem, m_drivetrainSubsystem);
  public final ClimberLowerCommand m_climberLowerCommand = new ClimberLowerCommand(m_climberSubsystem);
  public final ClimberRaiseCommand m_climberRaiseCommand = new ClimberRaiseCommand(m_climberSubsystem);
  public final DriveCommand m_driveCommand = new DriveCommand(m_drivetrainSubsystem);
  public final DriveShiftGearCommand m_driveShiftGearCommand = new DriveShiftGearCommand(m_drivetrainSubsystem);
  public final IntakeBallCommand m_intakeBallCommand = new IntakeBallCommand(m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final OuttakeBallCommand m_outtakeBallCommand = new OuttakeBallCommand(m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final IntakeLiftCommand m_intakeLiftCommand = new IntakeLiftCommand(m_intakeSubsystem);
  //public final ResetBallCountCommand m_resetBallCountCommand = new ResetBallCountCommand();
  public final ShootBallCommand m_shootBallCommand = new ShootBallCommand(m_ballManipulatorSubsystem, m_shooterSubsystem, m_intakeSubsystem, m_limelightSubsystem);
  public final BUTTONShootBallCommand m_BUTTONShootBallCommand = new BUTTONShootBallCommand(m_ballManipulatorSubsystem, m_shooterSubsystem, m_intakeSubsystem, m_limelightSubsystem);
  public final TurretSetAngleCommand m_turretSetAngleCommand = new TurretSetAngleCommand(m_limelightSubsystem, m_turretSubsystem);
  //public final TurretSpinCommand m_turretSpinCommand = new TurretSpinCommand(m_turretSubsystem);
  public final TurretSpinLeftCommand m_turretSpinLeftCommand = new TurretSpinLeftCommand(m_turretSubsystem);
  public final TurretSpinRightCommand m_turretSpinRightCommand = new TurretSpinRightCommand(m_turretSubsystem);
   // need this to function as a 
   public static SendableChooser<Integer> m_ballCounterChooser = new SendableChooser<Integer>();


    public Joystick driveStick = new Joystick(0);
    public XboxController xboxController = new XboxController(1);

    public Button driveStick1Button = new JoystickButton(driveStick, 1);
    public Button driveStick2Button = new JoystickButton(driveStick, 2);
    public Button driveStick3Button = new JoystickButton(driveStick, 3);
    public Button driveStick4Button = new JoystickButton(driveStick, 4);
    public Button driveStick5Button = new JoystickButton(driveStick, 5);
    public Button driveStick6Button = new JoystickButton(driveStick, 6);
    public Button driveStick7Button = new JoystickButton(driveStick, 7);
    public Button driveStick8Button = new JoystickButton(driveStick, 8);
    public Button driveStick9Button = new JoystickButton(driveStick, 9);
    public Button driveStick10Button = new JoystickButton(driveStick, 10);
    public Button driveStick11Button = new JoystickButton(driveStick, 11);
    public Button driveStick12Button = new JoystickButton(driveStick, 12);  
  
    public Button xboxControllerAButton = new JoystickButton(xboxController, 1);
    public Button xboxControllerBButton = new JoystickButton(xboxController, 2);
    public Button xboxControllerXButton = new JoystickButton(xboxController, 3);
    public Button xboxControllerYButton = new JoystickButton(xboxController, 4);
    public Button xboxControllerLBButton = new JoystickButton(xboxController, 5);
    public Button xboxControllerRBButton = new JoystickButton(xboxController, 6);  
    public Button xboxControllerBackButton = new JoystickButton(xboxController, 7);
    public Button xboxControllerStartButton = new JoystickButton(xboxController, 8);
    public Button xboxControllerLeftStickButton = new JoystickButton(xboxController, 9);
    public Button xboxControllerRightStickButton = new JoystickButton(xboxController, 10);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();    
    CommandScheduler.getInstance().setDefaultCommand(m_drivetrainSubsystem, m_driveCommand);
    //CommandScheduler.getInstance().setDefaultCommand(m_intakeSubsystem, m_intakeBallCommand);
    //CommandScheduler.getInstance().setDefaultCommand(m_ballManipulatorSubsystem, m_ballManipulateCommand);
    
    
    // m_ballCounterChooser.setDefaultOption("0", 0);
    // m_ballCounterChooser.addOption("1", 1);
    // m_ballCounterChooser.addOption("2", 2);
    // m_ballCounterChooser.addOption("3", 3);

    // SmartDashboard.putData(m_ballCounterChooser);


  }

/**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * 
   * 
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first
   * .wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    xboxControllerAButton.whileHeld(m_BUTTONShootBallCommand);
    //xboxControllerBButton.toggleWhenPressed(m_shootBallCommand);
    xboxControllerBButton.whenPressed(m_intakeLiftCommand);
    //We need to find out why X button is not turning on motor
    xboxControllerXButton.whileHeld(m_intakeBallCommand);
    //xboxControllerYButton.whileHeld(m_ballManipulateCommand);
    xboxControllerLBButton.whileHeld(m_climberLowerCommand);
    xboxControllerRBButton.whileHeld(m_climberRaiseCommand);
    //xboxControllerStartButton.whenPressed(m_ballOverrideCommand);
    xboxControllerBackButton.whileHeld(m_outtakeBallCommand);

    
    driveStick2Button.whenPressed(m_driveShiftGearCommand);
    //driveStick9Button.whenPressed(m_resetBallCountCommand);
    driveStick10Button.toggleWhenPressed(m_turretSetAngleCommand);
    driveStick11Button.whileHeld(m_turretSpinLeftCommand);
    driveStick12Button.whileHeld(m_turretSpinRightCommand);



  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous00
   */
  public Command getAutonomousCommand() {
    
   
    return m_autoDriveForwardCommand(); 

  }

  private Command m_autoDriveForwardCommand() {
    return null;
  }
}
