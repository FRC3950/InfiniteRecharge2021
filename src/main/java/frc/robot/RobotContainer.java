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
import frc.robot.commands.AutoDriveBackwardCommand;
import frc.robot.commands.AutoDriveForwardCommand;
import frc.robot.commands.AutonomousCommandGroup2020;
import frc.robot.commands.BUTTONShootBallCommand;
import frc.robot.commands.BallManipulateCommand;
import frc.robot.commands.BallManipulateCommandAuto;
import frc.robot.commands.BallOverrideCommand;
import frc.robot.commands.ClimberLowerCommand;
import frc.robot.commands.ClimberRaiseCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.DriveShiftGearCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.GSCPlanACommandGroup;
import frc.robot.commands.GSCPlanADriveCommand;
import frc.robot.commands.GSCPlanADriveCommand2;
import frc.robot.commands.GSCPlanADriveCommand3;
import frc.robot.commands.GSCPlanADriveCommand4;
import frc.robot.commands.GSCPlanADriveCommand5;
import frc.robot.commands.GSCPlanADriveCommand6;
import frc.robot.commands.GSCPlanADriveCommand7;
import frc.robot.commands.GSCPlanBCommandGroup;
import frc.robot.commands.GSCPlanBDriveCommand;
import frc.robot.commands.GSCPlanBDriveCommand2;
import frc.robot.commands.GSCPlanBDriveCommand3;
import frc.robot.commands.GSCPlanBDriveCommand4;
import frc.robot.commands.GSCPlanBDriveCommand5;
import frc.robot.commands.GSCPlanBDriveCommand6;
import frc.robot.commands.GSCPlanBDriveCommand7;
import frc.robot.commands.IntakeBallCommand;
import frc.robot.commands.IntakeLiftCommand;
import frc.robot.commands.ResetBallCountCommand;
import frc.robot.commands.ShootBallCommand;
import frc.robot.commands.TurretSetAngleCommand;
import frc.robot.commands.TurretSpinLeftCommand;
import frc.robot.commands.TurretSpinRightCommand;
import frc.robot.subsystems.BallCounterSubsystem;
import frc.robot.subsystems.BallManipulatorSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;
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
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public final BallCounterSubsystem m_ballCounterSubsystem = new BallCounterSubsystem();
  public final BallManipulatorSubsystem m_ballManipulatorSubsystem = new BallManipulatorSubsystem();
  public final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  public final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  public final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  public final LimelightSubsystem m_limelightSubsystem = new LimelightSubsystem();
  public final ShooterSubsystem m_shooterSubsystem  = new ShooterSubsystem();
  public final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
  public final GyroSubsystem m_gyroSubsystem = new GyroSubsystem();
  

  public final AutonomousCommandGroup2020 m_autonomousCommandGroup2020 = new AutonomousCommandGroup2020(m_drivetrainSubsystem, m_shooterSubsystem, m_ballManipulatorSubsystem, m_ballCounterSubsystem, m_intakeSubsystem, m_limelightSubsystem, m_turretSubsystem);
  public final GSCPlanACommandGroup m_gscPlanACommandGroup = new GSCPlanACommandGroup(m_drivetrainSubsystem, m_ballManipulatorSubsystem, m_intakeSubsystem, m_gyroSubsystem);
  public final GSCPlanBCommandGroup m_gscPlanBCommandGroup = new GSCPlanBCommandGroup(m_drivetrainSubsystem, m_ballManipulatorSubsystem, m_intakeSubsystem, m_gyroSubsystem);


  public final AutoDriveForwardCommand m_autoDriveForwardCommand = new AutoDriveForwardCommand(m_drivetrainSubsystem);
  public final AutoDriveBackwardCommand m_autoDriveBackwardCommand = new AutoDriveBackwardCommand(m_drivetrainSubsystem);
  public final BallOverrideCommand m_ballOverrideCommand = new BallOverrideCommand(m_ballManipulatorSubsystem, m_ballCounterSubsystem, m_intakeSubsystem);
  public final BallManipulateCommand m_ballManipulateCommand = new BallManipulateCommand(m_intakeSubsystem, m_ballManipulatorSubsystem, m_ballCounterSubsystem);
  public final BallManipulateCommandAuto m_ballManipulateCommandAuto = new BallManipulateCommandAuto(m_intakeSubsystem, m_ballManipulatorSubsystem, m_ballCounterSubsystem, m_drivetrainSubsystem);
  public final ClimberLowerCommand m_climberLowerCommand = new ClimberLowerCommand(m_climberSubsystem);
  public final ClimberRaiseCommand m_climberRaiseCommand = new ClimberRaiseCommand(m_climberSubsystem);
  public final DriveCommand m_driveCommand = new DriveCommand(m_drivetrainSubsystem);
  public final DriveShiftGearCommand m_driveShiftGearCommand = new DriveShiftGearCommand(m_drivetrainSubsystem);
  public final GSCPlanADriveCommand m_gscPlanADriveCommand = new GSCPlanADriveCommand(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanADriveCommand2 m_gscPlanADriveCommand2 = new GSCPlanADriveCommand2(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanADriveCommand3 m_gscPlanADriveCommand3 = new GSCPlanADriveCommand3(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanADriveCommand4 m_gscPlanADriveCommand4 = new GSCPlanADriveCommand4(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);  
  public final GSCPlanADriveCommand5 m_gscPlanADriveCommand5 = new GSCPlanADriveCommand5(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanADriveCommand6 m_gscPlanADriveCommand6 = new GSCPlanADriveCommand6(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanADriveCommand7 m_gscPlanADriveCommand7 = new GSCPlanADriveCommand7(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem); 
  public final GSCPlanBDriveCommand m_gscPlanBDriveCommand = new GSCPlanBDriveCommand(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanBDriveCommand2 m_gscPlanBDriveCommand2 = new GSCPlanBDriveCommand2(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanBDriveCommand3 m_gscPlanBDriveCommand3 = new GSCPlanBDriveCommand3(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanBDriveCommand4 m_gscPlanBDriveCommand4 = new GSCPlanBDriveCommand4(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);  
  public final GSCPlanBDriveCommand5 m_gscPlanBDriveCommand5 = new GSCPlanBDriveCommand5(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanBDriveCommand6 m_gscPlanBDriveCommand6 = new GSCPlanBDriveCommand6(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);
  public final GSCPlanBDriveCommand7 m_gscPlanBDriveCommand7 = new GSCPlanBDriveCommand7(m_drivetrainSubsystem, m_gyroSubsystem, m_intakeSubsystem, m_ballManipulatorSubsystem);   
  public final IntakeBallCommand m_intakeBallCommand = new IntakeBallCommand(m_intakeSubsystem, m_ballCounterSubsystem);
  public final IntakeLiftCommand m_intakeLiftCommand = new IntakeLiftCommand(m_intakeSubsystem);
  public final ResetBallCountCommand m_resetBallCountCommand = new ResetBallCountCommand(m_ballCounterSubsystem);
  public final ShootBallCommand m_shootBallCommand = new ShootBallCommand(m_ballManipulatorSubsystem, m_shooterSubsystem, m_intakeSubsystem, m_limelightSubsystem);
  public final BUTTONShootBallCommand m_BUTTONShootBallCommand = new BUTTONShootBallCommand(m_ballManipulatorSubsystem, m_shooterSubsystem, m_intakeSubsystem, m_limelightSubsystem);
  public final TurretSetAngleCommand m_turretSetAngleCommand = new TurretSetAngleCommand(m_limelightSubsystem, m_turretSubsystem);
  // public final TurretSpinCommand m_turretSpinCommand = new TurretSpinCommand(m_turretSubsystem);
  public final TurretSpinLeftCommand m_turretSpinLeftCommand = new TurretSpinLeftCommand(m_turretSubsystem);
  public final TurretSpinRightCommand m_turretSpinRightCommand = new TurretSpinRightCommand(m_turretSubsystem);
    
    public SendableChooser<Integer> m_ballCounterChooser = new SendableChooser<>();


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
    Integer zero = new Integer(0);
    Integer one = new Integer(1);
    Integer two = new Integer(2);
    Integer three = new Integer(3);
    Integer four = new Integer(4);
    Integer five = new Integer(5);
    
    m_ballCounterChooser.setDefaultOption("0", zero);
    m_ballCounterChooser.addOption("1", one);
    m_ballCounterChooser.addOption("2", two);
    m_ballCounterChooser.addOption("3", three);
    m_ballCounterChooser.addOption("4", four);
    m_ballCounterChooser.addOption("5", five);

    SmartDashboard.putData(m_ballCounterChooser);


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
    driveStick11Button.whileHeld(m_turretSpinLeftCommand);
    driveStick12Button.whileHeld(m_turretSpinRightCommand);
    xboxControllerAButton.whileHeld(m_BUTTONShootBallCommand);
    //xboxControllerBButton.toggleWhenPressed(m_shootBallCommand);
    xboxControllerBButton.whenPressed(m_intakeLiftCommand);
    xboxControllerXButton.whileHeld(m_intakeBallCommand);
    xboxControllerYButton.whileHeld(m_ballManipulateCommand);
    xboxControllerLBButton.whileHeld(m_climberLowerCommand);
    xboxControllerRBButton.whileHeld(m_climberRaiseCommand);
    xboxControllerStartButton.whenPressed(m_ballOverrideCommand);
    xboxControllerBackButton.whileHeld(m_ballOverrideCommand);
    driveStick2Button.whenPressed(m_driveShiftGearCommand);
    driveStick9Button.whenPressed(m_resetBallCountCommand);
    driveStick10Button.toggleWhenPressed(m_turretSetAngleCommand);
    driveStick7Button.whenPressed(m_gscPlanBCommandGroup);
    driveStick8Button.whenPressed(m_gscPlanACommandGroup);


  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous00
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_gscPlanBCommandGroup;
    // return m_autoAdvancedCommandGroup; CHANGE TO THIS WHEN TESTING ADVANCED AUTO
  }
}
