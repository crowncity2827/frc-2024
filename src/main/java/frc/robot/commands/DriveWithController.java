// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Drivetrain;

import frc.robot.subsystems.Bill;

public class DriveWithController extends Command {
  /** Creates a new DriveWithController. */
  DoubleSupplier m_vertical, m_horizontal, m_rotational;
  Trigger m_leftbumper, m_rightbumper;
  Drivetrain m_drivetrain;
  Bill m_Bill;
  public DriveWithController(DoubleSupplier m_vertical, DoubleSupplier m_horizontal, DoubleSupplier m_rotational, CommandXboxController m_driverController, Drivetrain m_drivetrain, Bill m_Bill) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_vertical = m_vertical;
    this.m_horizontal = m_horizontal;
    this.m_rotational = m_rotational;
    this.m_drivetrain = m_drivetrain;


    this.m_leftbumper = m_driverController.leftBumper();
    this.m_rightbumper = m_driverController.rightBumper();
    this.m_Bill = m_Bill;

    addRequirements(m_drivetrain);
    addRequirements(m_Bill);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_leftbumper.onFalse(Commands.runOnce(()->{m_Bill.stop();}));
    m_rightbumper.onFalse(Commands.runOnce(()->{m_Bill.stop();}));
    m_leftbumper.onTrue(Commands.runOnce(()->{m_Bill.reverse(1.0);}));
    m_rightbumper.onTrue(Commands.runOnce(()->{m_Bill.forward(1.0);}));
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double y = m_vertical.getAsDouble(); // Remember, Y stick value is reversed
    double x = m_horizontal.getAsDouble() * 1.1; // Counteract imperfect strafing
    double rx = m_rotational.getAsDouble();

    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
    double frontLeftPower = (y + x + rx) / denominator;
    double backLeftPower = (y - x + rx) / denominator;
    double frontRightPower = (y - x - rx) / denominator;
    double backRightPower = (y + x - rx) / denominator;

    m_drivetrain.drive(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0, 0, 0);
  }

  public void forward() {
    m_drivetrain.drive(0.2, 0.2, 0.2 , 0.2);
  }

  public void stop() {
    m_drivetrain.drive(0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
