// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems.armmotor;
import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.pneumatics.CompressorIO;
import frc.robot.subsystems.pneumatics.CompressorIOInputsAutoLogged;
import frc.robot.subsystems.pneumatics.DoubleSolenoidIO;
import frc.robot.subsystems.pneumatics.DoubleSolenoidIOInputsAutoLogged;

public class JointedArm extends SubsystemBase {
  private final CompressorIO compressor;
  private final DoubleSolenoidIO brake;
  private final JointedArmIO arm;
  private final CompressorIOInputsAutoLogged cInputs = new CompressorIOInputsAutoLogged();
  private final DoubleSolenoidIOInputsAutoLogged dsInputs = new DoubleSolenoidIOInputsAutoLogged();
  private final JointedArmIOInputsAutoLogged pInputs = new JointedArmIOInputsAutoLogged();

  public JointedArm(CompressorIO compressor, DoubleSolenoidIO brake, JointedArmIO arm) {
    this.compressor = compressor;
    this.brake = brake;
    this.arm = arm;
  }

  @Override
  public void periodic() {
    compressor.updateInputs(cInputs);
    brake.updateInputs(dsInputs);
    arm.updateInputs(pInputs);

    Logger.processInputs("Compressor", cInputs);
    Logger.processInputs("Double Solenoid", dsInputs);
    Logger.processInputs("Arm", pInputs);
  }

  public void setPosition(double position) {
    brake.stopIntake();
    arm.setPosition(position);
    brake.extendIntake();

    Logger.recordOutput("Intake Target", position);
  }

  public void stop() {
    arm.stop();
  }

  public void activateBrake() {
    brake.stopIntake();
  }

  public void deactivateBrake() {
    brake.extendIntake();
  }

  public void toggleCompressor() {
    compressor.toggleCompressor();
  }
}