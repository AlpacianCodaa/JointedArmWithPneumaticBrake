// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.armmotor;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.util.Units;

public class JointedArmMotorIO implements JointedArmIO {
  private final TalonFX falcon;

  private final StatusSignal<Double> armPosition;
  private final StatusSignal<Double> armVelocity;
  private final StatusSignal<Double> appliedVolts;
  private final StatusSignal<Double> currentAmps;

  public JointedArmMotorIO(int id) {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.CurrentLimits.StatorCurrentLimit = 30;
    config.CurrentLimits.StatorCurrentLimitEnable = true;
    config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    falcon = new TalonFX(id);

    falcon.getConfigurator().apply(config);

    armPosition = falcon.getPosition();
    armVelocity = falcon.getVelocity();
    appliedVolts = falcon.getMotorVoltage();
    currentAmps = falcon.getStatorCurrent();

    BaseStatusSignal.setUpdateFrequencyForAll(
        100, armPosition, armVelocity, appliedVolts, currentAmps);
  }

  @Override
  public void updateInputs(JointedArmIOInputs inputs) {
    inputs.armPosition = Units.rotationsToDegrees(armPosition.getValueAsDouble());
    inputs.armVelocity =
        Units.rotationsPerMinuteToRadiansPerSecond(armVelocity.getValueAsDouble());

    inputs.appliedVolts = appliedVolts.getValue();
    inputs.currentAmps = currentAmps.getValue();
  }

  @Override
  public void setPosition(double position) {
    falcon.setControl(new PositionVoltage(position));
  }

  @Override
  public void stop() {
    falcon.stopMotor();
  }

  @Override
  public void configurePID(double kP, double kI, double kD) {
    Slot0Configs config = new Slot0Configs();

    config.kP = kP;
    config.kI = kI;
    config.kD = kD;

    falcon.getConfigurator().apply(config);
  }
}
