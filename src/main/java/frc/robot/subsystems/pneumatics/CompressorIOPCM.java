package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class CompressorIOPCM implements CompressorIO {
    private boolean toggle;
  private final Compressor compressor;

  public CompressorIOPCM(int module) {
    compressor = new Compressor(module, PneumaticsModuleType.CTREPCM);
  }

  @Override
  public void updateInputs(CompressorIOInputs inputs) {
    inputs.isOn = compressor.isEnabled();
    inputs.compressorCurrent = compressor.getCurrent();
    inputs.compressorPressure = compressor.getPressure();
  }

  @Override
  public void toggleCompressor() {
    if (toggle == false) {
      compressor.enableDigital();
      toggle = true;
    } else {
      compressor.disable();
      toggle = false;
    }
  }
}
