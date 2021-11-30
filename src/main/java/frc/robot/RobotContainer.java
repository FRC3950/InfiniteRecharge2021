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

  public Joystick firstDriveStick = new Joystick(0);
  public Joystick secondDriveStick = new Joystick(1);
  

  //public final AutonomousCommandGroup2020 m_autonomousCommandGroup2020 = new AutonomousCommandGroup2020(m_drivetrainSubsystem, m_shooterSubsystem, m_ballManipulatorSubsystem, m_ballCounterSubsystem, m_intakeSubsystem, m_limelightSubsystem, m_turretSubsystem);
  

  public final AutoDriveForwardCommand m_autoDriveForwardCommand = new AutoDriveForwardCommand(m_drivetrainSubsystem);
  public final AutoDriveBackwardCommand m_autoDriveBackwardCommand = new AutoDriveBackwardCommand(m_drivetrainSubsystem);
  public final BallOverrideCommand m_ballOverrideCommand = new BallOverrideCommand(m_ballManipulatorSubsystem, m_intakeSubsystem);
  public final BallManipulateCommand m_ballManipulateCommand = new BallManipulateCommand(m_intakeSubsystem, m_ballManipulatorSubsystem);
  //public final BallManipulateCommandAuto m_ballManipulateCommandAuto = new BallManipulateCommandAuto(m_intakeSubsystem, m_ballManipulatorSubsystem, m_ballCounterSubsystem, m_drivetrainSubsystem);
  public final ClimberLowerCommand m_climberLowerCommand = new ClimberLowerCommand(m_climberSubsystem);
  public final ClimberRaiseCommand m_climberRaiseCommand = new ClimberRaiseCommand(m_climberSubsystem);
  public final DriveCommand m_driveCommand = new DriveCommand(m_drivetrainSubsystem);

  public final DriveCommandJoysticks m_driveCommandJoySticks = new DriveCommandJoysticks(m_drivetrainSubsystem, firstDriveStick, secondDriveStick);
  public final DriveShiftGearCommand m_driveShiftGearCommand = new DriveShiftGearCommand(m_drivetrainSubsystem);
  
  public final IntakeBallCommand m_intakeBallCommand = new IntakeBallCommand(m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final OuttakeBallCommand m_outtakeBallCommand = new OuttakeBallCommand(m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final IntakeLiftCommand m_intakeLiftCommand = new IntakeLiftCommand(m_intakeSubsystem);
  //public final ResetBallCountCommand m_resetBallCountCommand = new ResetBallCountCommand();
  public final ShootBallCommand m_shootBallCommand = new ShootBallCommand(m_ballManipulatorSubsystem, m_shooterSubsystem, m_intakeSubsystem, m_limelightSubsystem);
  public final BUTTONShootBallCommand m_BUTTONShootBallCommand = new BUTTONShootBallCommand(m_ballManipulatorSubsystem, m_shooterSubsystem, m_intakeSubsystem, m_limelightSubsystem);
  public final TurretSetAngleCommand m_turretSetAngleCommand = new TurretSetAngleCommand(m_limelightSubsystem, m_turretSubsystem);
  public final ResetCountCommand m_resetCountCommand = new ResetCountCommand();
  //public final TurretSpinCommand m_turretSpinCommand = new TurretSpinCommand(m_turretSubsystem);
  public final TurretSpinLeftCommand m_turretSpinLeftCommand = new TurretSpinLeftCommand(m_turretSubsystem);
  public final TurretSpinRightCommand m_turretSpinRightCommand = new TurretSpinRightCommand(m_turretSubsystem);
   // need this to function as a 
   public static SendableChooser<Integer> m_ballCounterChooser = new SendableChooser<Integer>();


   

    public Button firstDriveStick1Button = new JoystickButton(firstDriveStick, 1);
    public Button firstDriveStick2Button = new JoystickButton(firstDriveStick, 2);
    public Button firstDriveStick3Button = new JoystickButton(firstDriveStick, 3);
    public Button firstDriveStick4Button = new JoystickButton(firstDriveStick, 4);
    public Button firstDriveStick5Button = new JoystickButton(firstDriveStick, 5);
    public Button firstDriveStick6Button = new JoystickButton(firstDriveStick, 6);
    public Button firstDriveStick7Button = new JoystickButton(firstDriveStick, 7);
    public Button firstDriveStick8Button = new JoystickButton(firstDriveStick, 8);
    public Button firstDriveStick9Button = new JoystickButton(firstDriveStick, 9);
    public Button firstDriveStick10Button = new JoystickButton(firstDriveStick, 10);
    public Button firstDriveStick11Button = new JoystickButton(firstDriveStick, 11);
    public Button firstDriveStick12Button = new JoystickButton(firstDriveStick, 12);  
  
    public Button secondDriveStick1Button = new JoystickButton(secondDriveStick, 1);
    public Button secondDriveStick2Button = new JoystickButton(secondDriveStick, 2);
    public Button secondDriveStick3Button = new JoystickButton(secondDriveStick, 3);
    public Button secondDriveStick4Button = new JoystickButton(secondDriveStick, 4); 
    public Button secondDriveStick5Button = new JoystickButton(secondDriveStick, 5); 
    public Button secondDriveStick6Button = new JoystickButton(secondDriveStick, 6);   
    public Button secondDriveStick7Button = new JoystickButton(secondDriveStick, 7); 
    public Button secondDriveStick8Button = new JoystickButton(secondDriveStick, 8); 
    public Button secondDriveStick9Button = new JoystickButton(secondDriveStick, 9); 
    public Button secondDriveStick10Button = new JoystickButton(secondDriveStick, 10);

    //1.
    SendableChooser<Command> m_chooser = new SendableChooser<>();

  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();    
    CommandScheduler.getInstance().setDefaultCommand(m_drivetrainSubsystem, m_driveCommandJoySticks);
    //CommandScheduler.getInstance().setDefaultCommand(m_intakeSubsystem, m_intakeBallCommand);
    //CommandScheduler.getInstance().setDefaultCommand(m_ballManipulatorSubsystem, m_ballManipulateCommand);
    
    
    // m_ballCounterChooser.setDefaultOption("0", 0);
    // m_ballCounterChooser.addOption("1", 1);
    // m_ballCounterChooser.addOption("2", 2);
    // m_ballCounterChooser.addOption("3", 3);

    // SmartDashboard.putData(m_ballCounterChooser);

    m_chooser.setDefaultOption("AutoDrive", m_autoDriveForwardCommand);
    //put on dashboard in next line later


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
    
    secondDriveStick1Button.whileHeld(m_BUTTONShootBallCommand);
    secondDriveStick9Button.whileHeld(m_intakeBallCommand);
    secondDriveStick10Button.whileHeld(m_outtakeBallCommand);
    secondDriveStick2Button.whileHeld(m_resetCountCommand);
    



    
    firstDriveStick2Button.whenPressed(m_driveShiftGearCommand);
    firstDriveStick10Button.toggleWhenPressed(m_turretSetAngleCommand);
    firstDriveStick11Button.whileHeld(m_turretSpinLeftCommand);
    firstDriveStick12Button.whileHeld(m_turretSpinRightCommand);



  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous00
   */
  public Command getAutonomousCommand() {
    
   
    return m_chooser.getSelected(); 

  }

  
}
