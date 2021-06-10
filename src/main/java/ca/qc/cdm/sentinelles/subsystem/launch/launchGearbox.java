package ca.qc.cdm.sentinelles.subsystem.launch;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.CAN;

import static ca.qc.cdm.sentinelles.Constants.ArmConstants;

import java.util.List;

public class launchGearbox {
    public final CANSparkMax master;
    private final boolean inverted;
    private final List<CANSparkMax> motors;

    public launchGearbox(int masterId, boolean inverted) {
        master = new CANSparkMax(masterId, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.inverted = inverted;
        motors = List.of(master);
        init();
    }
    private void init() {
        master.setInverted(inverted);

        motors.forEach(motor ->{
            motor.restoreFactoryDefaults();
            motor.enableVoltageCompensation(11);
            motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
            motor.setSmartCurrentLimit(20, 15, 3500);
            motor.setOpenLoopRampRate(1);
            motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 1);
        });

    }
}
