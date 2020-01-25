package ca.qc.cdm.sentinelles.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import java.util.List;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.kTimeoutMs;
import static ca.qc.cdm.sentinelles.subsystems.drive.DriveSubsystem.*;
import static com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative;

public class DriveGearbox {
    private static final Gains kGains = new Gains(0.2, 0.0, 0.0, 0.2, 0, 1.0);

    private final WPI_TalonFX master;
    private final WPI_TalonFX slave;
    private final boolean inverted;
    private final List<TalonFX> motors;

    public DriveGearbox(int masterId, int salveId, boolean inverted) {
        master = new WPI_TalonFX(masterId);
        slave = new WPI_TalonFX(salveId);
        this.inverted = inverted;
        motors = List.of(master, slave);

        init();
    }

    public WPI_TalonFX master() {
        return master;
    }

    private void init() {
        slave.follow(master);

        // Configure Encoder
        master.configSelectedFeedbackSensor(CTRE_MagEncoder_Relative, 0, kTimeoutMs);
        master.setSelectedSensorPosition(0, 0, kTimeoutMs);
        master.configMotionCruiseVelocity(15000, kTimeoutMs);
        master.configMotionAcceleration(6000, kTimeoutMs);
        master.configOpenloopRamp(3);
        master.configClosedloopRamp(0);
        master.selectProfileSlot(kSlotIdx, kPIDLoopIdx);

        // Configure Inversion
        master.setInverted(inverted);
        slave.setInverted(inverted);


        motors.forEach(motor -> {
            motor.configFactoryDefault();

            motor.configPeakOutputForward(1, kTimeoutMs);
            motor.configPeakOutputReverse(-1, kTimeoutMs);

            motor.configNominalOutputForward(0, kTimeoutMs);
            motor.configNominalOutputReverse(0, kTimeoutMs);
            motor.configNeutralDeadband(0.001, kTimeoutMs);

            motor.enableVoltageCompensation(true);
            motor.configVoltageCompSaturation(11.5, kTimeoutMs);

            motor.configVoltageMeasurementFilter(32, kTimeoutMs);

            // TODO
            // BreakMode -> NeutralMode.Brake
        });

        setClosedLoopGains();


    }

    private void setClosedLoopGains() {
        motors.forEach(motor -> {
            motor.config_kF(kSlotIdx, kGains.kF, kTimeoutMs);
            motor.config_kP(kSlotIdx, kGains.kP, kTimeoutMs);
            motor.config_kI(kSlotIdx, kGains.kI, kTimeoutMs);
            motor.config_kD(kSlotIdx, kGains.kD, kTimeoutMs);
        });
    }
}
