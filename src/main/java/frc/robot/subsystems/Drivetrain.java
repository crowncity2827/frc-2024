// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */

  CANSparkMax m_frontLeft, m_frontRight, m_backLeft, m_backRight;

  public Drivetrain() {
    m_frontLeft = new CANSparkMax(2, MotorType.kBrushed);
    m_frontRight = new CANSparkMax(3, MotorType.kBrushed);
    m_backLeft = new CANSparkMax(5, MotorType.kBrushed);
    m_backRight = new CANSparkMax(4, MotorType.kBrushed);

    m_frontLeft.setIdleMode(IdleMode.kBrake);
    m_frontRight.setIdleMode(IdleMode.kBrake);
    m_backLeft.setIdleMode(IdleMode.kBrake);
    m_backRight.setIdleMode(IdleMode.kBrake);

    m_frontLeft.setInverted(true);
    m_backLeft.setInverted(true);
    m_frontRight.setInverted(false);
    m_backRight.setInverted(false);
  }

  @Override
  public void periodic() {
    
  }

  public void drive(double fl_power, double fr_power, double bl_power, double br_power) {
    m_frontLeft.set(fl_power);
    m_frontRight.set(fr_power);
    m_backLeft.set(bl_power);
    m_backRight.set(br_power);
  }
}
