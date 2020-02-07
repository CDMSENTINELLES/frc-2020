package ca.qc.cdm.sentinelles.subsystems.collection;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.SubsystemConstants.collectBalls;

public class Collection extends SubsystemBase {
    public static final VictorSPX collectMotor = new VictorSPX(collectBalls);
    public Collection() {
        super();
        init();
    }

    private void init() {
        collectMotor.configFactoryDefault();
    }

    public void collect(int percentOutput) {
        collectMotor.set(ControlMode.PercentOutput, percentOutput);
    }

}
