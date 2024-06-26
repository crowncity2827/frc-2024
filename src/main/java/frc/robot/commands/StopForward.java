// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StopForward extends Command {
  /** Creates a new GoForward. */

  Drivetrain m_drivetrain;

  Double autoSpeed;

  public StopForward(Drivetrain m_drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    this.m_drivetrain = m_drivetrain;
  }
  @Override
  public void initialize() {
    // System.out.println(timer.get());
    autoSpeed = 0.0;
  }

  @Override
  public void execute() {
    // System.out.println(timer.get());
    m_drivetrain.drive(0, 0, 0, 0);
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0, 0, 0);
  }
}
