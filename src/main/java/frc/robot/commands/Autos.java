// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.GoForward;
import frc.robot.commands.StopForward;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj.Timer;

public final class Autos {
  /** Example static factory for an autonomous command. */

  Timer timer;
  Double time;

  static boolean runOnce = false;

  public static Command exampleAuto(Drivetrain m_drivetrain, Double time, Double autoSpeed) {
    if (time < 3) {
//      System.out.println(time);
      return Commands.sequence(new GoForward(m_drivetrain, autoSpeed));
    } else {
      if (!runOnce) {
        runOnce = true;
        return Commands.sequence(new StopForward(m_drivetrain));
      }
      else {
        return null;
      }
    }
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
