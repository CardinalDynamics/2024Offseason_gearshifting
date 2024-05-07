// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX; 


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  // motors
  private VictorSPX leftMotor1 = new VictorSPX(5);
  private VictorSPX leftMotor2 = new VictorSPX(6);
  private VictorSPX leftMotor3 = new VictorSPX(7);
  private VictorSPX leftMotor4 = new VictorSPX(8);
  private VictorSPX rightMotor1 = new VictorSPX(1);
  private VictorSPX rightMotor2 = new VictorSPX(2);
  private VictorSPX rightMotor3 = new VictorSPX(3);
  private VictorSPX rightMotor4 = new VictorSPX(4);
  // xbox things
  private Joystick joy1 = new Joystick(0);
  private XboxController xbox = new XboxController(1);
  // puhneumatics
   //private Compressor comp = new Compressor(PneumaticsModuleType.CTREPCM);
   private DoubleSolenoid solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,0,1);
  
  
  
   @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  
    
    
    double speed = -joy1.getRawAxis(1) * 0.6;
    double turn = joy1.getRawAxis(4) * 0.3;
    double left = speed + turn;
    double right = speed - turn;
    leftMotor1.set(ControlMode.PercentOutput,left);
    leftMotor2.set(ControlMode.PercentOutput,left);
    leftMotor3.set(ControlMode.PercentOutput,left);
    leftMotor4.set(ControlMode.PercentOutput,left);
    rightMotor1.set(ControlMode.PercentOutput,-right);
    rightMotor2.set(ControlMode.PercentOutput,-right);
    rightMotor3.set(ControlMode.PercentOutput,-right);
    rightMotor4.set(ControlMode.PercentOutput,-right);

    if (xbox.getLeftBumper()) {

      solenoid.set(DoubleSolenoid.Value.kForward);
    }
    else if(xbox.getRightBumper()) {

      solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
