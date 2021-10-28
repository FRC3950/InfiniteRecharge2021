/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */
  private final WPI_TalonSRX m_shooterMotor;
  private final WPI_TalonSRX m_shooterMotorFollower;
  
  private double kF = -.029;
  private double kP = 0;
  private double kI = 0;
  private double kD = 0;

  public ShooterSubsystem() {

    m_shooterMotor = new WPI_TalonSRX(3);
    m_shooterMotorFollower = new WPI_TalonSRX(2);
    
    m_shooterMotor.setInverted(true);

    m_shooterMotorFollower.follow(m_shooterMotor);

    m_shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    m_shooterMotor.setSensorPhase(true);

    m_shooterMotor.configNominalOutputForward(0, 30);
    m_shooterMotor.configNominalOutputReverse(0, 30);
    m_shooterMotor.configPeakOutputForward(1, 30);
    m_shooterMotor.configPeakOutputReverse(-1, 30);

    m_shooterMotor.config_kF(0, kF, 30);
    m_shooterMotor.config_kP(0, kP, 30);
    m_shooterMotor.config_kI(0, kI, 30);
    m_shooterMotor.config_kD(0, kD, 30);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   // System.out.println(m_shooterMotor.getClosedLoopError(0));
  }

  //Gets the velocity of the shooter motor from the encoder
  public double getVelocityFromEncoder(){
    return m_shooterMotorFollower.getSelectedSensorVelocity(0);
  }

  //Converts the velocity from the encoder to an RPM
  public double getRPM(){
    return getVelocityFromEncoder() / 4096 * 600;
  }


  public void setShooterMotor(double speed){
    m_shooterMotor.set(ControlMode.Velocity, speed);
    //System.out.println("kF:" + kF + "/nkP:" + kP + "/nkI:" + kI + "/nkD:" + kD);
   // System.out.println(getVelocityFromEncoder());
  
  }

  //Converts the distance determined by a formula and the limelight to the speed the ball needs to be shot at
  public double setShooterSpeed(double distance){
    //CREATE FORMULA BASED ON DISTANCE TO CALCULATE THE NECESSARY SPEED
    double speed = 1;
    m_shooterMotor.set(speed);
    return speed;
  }

  //Sets the motor to a speed if there is a ball in the shooter
  public boolean shootBall(double horizontalOffset, double speed, double distance){
    if(speed >= (setShooterSpeed(distance) -.02) && (horizontalOffset < 1 && horizontalOffset > -1)){
       return true;
      } else {
        return false;
      }
    }
}
