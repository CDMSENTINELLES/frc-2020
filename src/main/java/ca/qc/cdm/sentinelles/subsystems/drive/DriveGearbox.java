package ca.qc.cdm.sentinelles.subsystems.drive;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import java.util.List;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.kTimeoutMs;

public class DriveGearbox {
    private final WPI_TalonFX master;
    private final WPI_TalonFX slave;
    private final boolean inverted;
    private final boolean sensorPhase;
    private final List<TalonFX> motors;

    public DriveGearbox(int masterId, int slaveId, boolean inverted, boolean sensorPhase) {
        master = new WPI_TalonFX(masterId);
        slave = new WPI_TalonFX(slaveId);
        this.inverted = inverted;
        this.sensorPhase = sensorPhase;
        motors = List.of(master, slave);

        init();
    }

    public WPI_TalonFX master() {
        return master;
    }

    private void init() {
        slave.follow(master);

        // Configure Encoder
        master.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, kTimeoutMs);

        master.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, kTimeoutMs);
        master.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, kTimeoutMs);
        master.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, kTimeoutMs);

        master.configMotionAcceleration(2000, kTimeoutMs);
        master.configMotionCruiseVelocity(2000, kTimeoutMs);

        master.config_kP(0, 0.1, kTimeoutMs);
        master.config_kI(0, 0, kTimeoutMs);
        master.config_kD(0, 0, kTimeoutMs);
        master.config_kF(0, 0, kTimeoutMs);
        master.config_IntegralZone(0, 100, kTimeoutMs);
        master.configClosedLoopPeakOutput(0, 0.50, kTimeoutMs);

        master.configClosedLoopPeriod(0, 1, kTimeoutMs);
        master.configClosedLoopPeriod(1, 1, kTimeoutMs);

        master.configOpenloopRamp(0.5);
        master.configClosedloopRamp(0);
        master.configNeutralDeadband(0.001);

        master.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, kTimeoutMs);

        // Configure Inversion
        master.setInverted(inverted);
        slave.setInverted(inverted);

        // Configure Sensor Phase
        master.setSensorPhase(sensorPhase);
        slave.setSensorPhase(sensorPhase);

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

            motor.setNeutralMode(NeutralMode.Brake);
        });
    }
}
