package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import org.littletonrobotics.junction.AutoLog;

public interface DoubleSolenoidIO {
  @AutoLog
  public static class DoubleSolenoidIOInputs {
    public Value val = Value.kOff;
  }

  public default void updateInputs(DoubleSolenoidIOInputs inputs) {}

  public default void set(Value value) {}

  public default void extendIntake() {}

  public default void stopIntake() {}

  public default void retractIntake() {}
}
