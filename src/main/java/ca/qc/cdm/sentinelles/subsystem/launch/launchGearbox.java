package ca.qc.cdm.sentinelles.subsystem.launch;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.CAN;

import java.util.List;

public class launchGearbox {
    private final CANSparkMax master;
    private final CANSparkMax slave;
    private final boolean inverted;
    private final List<CANSparkMax> motors;

    public launchGearbox(int masterId, int slaveId, boolean inverted) {
        master = new CANSparkMax(masterId, CANSparkMaxLowLevel.MotorType.kBrushless);
        slave = new CANSparkMax(slaveId, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.inverted = inverted;
        motors = List.of(master, slave);
        init();
    }
    private void init() {
        master.setInverted(inverted);
        slave.setInverted(inverted);
        slave.follow(master);

    }
}
