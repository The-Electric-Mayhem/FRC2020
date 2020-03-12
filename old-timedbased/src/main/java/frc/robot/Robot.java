/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
  private final PWMVictorSPX m_leftFrontMotor = new PWMVictorSPX(2);
  private final PWMVictorSPX m_rightFrontMotor = new PWMVictorSPX(1);
  // private final PWMVictorSPX m_leftRear = new PWMVictorSPX(0);
  // private final PWMVictorSPX m_rightRear = new PWMVictorSPX(3);

  private final PWMVictorSPX m_colorWheel = new PWMVictorSPX(4);

  private final PWMVictorSPX m_rightShooterWheel = new PWMVictorSPX(5);
  private final PWMVictorSPX m_leftShooterWheel = new PWMVictorSPX(6);
  private final PWMVictorSPX m_intakeRampLeft = new PWMVictorSPX(8);


  // private final SpeedControllerGroup leftSpeedControllerGroup = new SpeedControllerGroup(m_leftFrontMotor, m_leftRear);
  // private final SpeedControllerGroup rightSpeedControllerGroup = new SpeedControllerGroup(m_rightFrontMotor, m_rightRear);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftFrontMotor, m_rightFrontMotor);
  private final Joystick m_stick = new Joystick(0);
  private final Timer m_timer = new Timer(); 
  // private final Compressor m_compressor = new Compressor();
  // private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(0, 1);
  // private final DoubleSolenoid m_doubleSolenoid2 = new DoubleSolenoid(2, 3);
  // private final AnalogInput m_pressureSensor = new AnalogInput(3);
  //private static final double kMaxPressure = 2.55;

  // private static final int kDoubleSolenoidForward = 5;
  // private static final int kDoubleSolenoidReverse = 3;

  /**
   * Button for color wheel
   */
  private static final int kColorWheelButton = 5;

  /**
   * button to engage/Disengage the intake system
   */
  private static final int kIntakeButton = 7;

  /**
   * button to engage shooter system
   */
  private static final int kShooterButton = 1;

  /**
   * boolean to track intake status
   */
  private boolean intakeRampEngaged = false;

  /**
   * Whether or not the system is fully pressurized.
   */
  // public boolean isPressurized() {
  //   if (Robot.isReal()) {
  //     return kMaxPressure <= m_pressureSensor.getVoltage();
  //   } else {
  //     return true; // NOTE: Simulation always has full pressure
  //   }
  // }

  @Override
  public void robotInit() {
    // m_compressor.setClosedLoopControl(true);
  }

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 0.5 ) {
      m_robotDrive.arcadeDrive(-1.0, 0.0); // drive forwards half speed
    } else {
      m_robotDrive.stopMotor(); // stop robot

      if(m_timer.get() < 0.6) {
        m_robotDrive.arcadeDrive(1.0, 0.0); // drive
      } else {
        m_robotDrive.stopMotor();
      }
    }
  }

  @Override
  public void teleopInit() {
    // ensures the intake is disengaged
    intakeRampEngaged = false;
  }

  @Override
  public void teleopPeriodic() {
    // calculates the intake speed from the slider axis
    double intakeSpeed = m_stick.getRawAxis(3);
    intakeSpeed = (intakeSpeed + 1) / 2;

    SmartDashboard.putNumber("Intake Ramp Speed", intakeSpeed);

    m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());

    // if (m_stick.getRawButton(kDoubleSolenoidForward)) {
    //   m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    //   m_doubleSolenoid2.set(DoubleSolenoid.Value.kForward);
    // } else if (m_stick.getRawButton(kDoubleSolenoidReverse)) {
    //   m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    //   m_doubleSolenoid2.set(DoubleSolenoid.Value.kReverse);
    // }


    /**
     * enable color wheel motor while button 6 is pressed
     */
    if(m_stick.getRawButton(kColorWheelButton)) {
      m_colorWheel.setSpeed(0.2);
    } else {
      m_colorWheel.setSpeed(0);
    }


    /**
     * enable shooter wheels while button 1 (trigger) is pressed
     */
    if(m_stick.getRawButton(kShooterButton)) {
      m_rightShooterWheel.set(1.0);
      m_leftShooterWheel.set(1.0);
    } else {
      m_rightShooterWheel.set(0.0);
      m_leftShooterWheel.set(0.0);
    }

    /**
     * enable or disable intake ramp
     */
    if(m_stick.getRawButton(kIntakeButton)) {
      // enable shooter
      intakeRampEngaged = true;
    }

    if(m_stick.getRawButton(8)) {
      intakeRampEngaged = false;
    }

    if(intakeRampEngaged) {
      m_intakeRampLeft.set(intakeSpeed);
    } else {
      m_intakeRampLeft.set(0.0);
    }
  }
}