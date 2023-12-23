package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class DoubleSolenoidIOPCM implements DoubleSolenoidIO {
  private final DoubleSolenoid dSolenoid;

  public DoubleSolenoidIOPCM(int module, int fChannel, int rChannel) {
    dSolenoid = new DoubleSolenoid(module, PneumaticsModuleType.CTREPCM, fChannel, rChannel);
  }

  @Override
  public void updateInputs(DoubleSolenoidIOInputs inputs) {
    inputs.val = dSolenoid.get();
  }

  @Override
  public void set(Value value) {
    dSolenoid.set(value);
  }
  @Override
  public void extendIntake() {
    dSolenoid.set(Value.kForward);
  }
  @Override
  public void retractIntake() {
    dSolenoid.set(Value.kReverse);
  }
  @Override
  public void stopIntake() {
    dSolenoid.set(Value.kOff);
  }
}
