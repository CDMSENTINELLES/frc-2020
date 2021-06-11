package ca.qc.cdm.sentinelles.subsystem.drive;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;

import java.util.ArrayList;
import java.util.List;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.TIMEOUT_MS;
import static com.ctre.phoenix.motorcontrol.NeutralMode.Brake;

public class DriveGearbox {
    public Orchestra _orchestra;
    private static final int SLOT_ID = 0;

    private final WPI_TalonFX master;
    private final WPI_TalonFX slave;
    private final boolean inverted;
    private final List<TalonFX> motors;

    public DriveGearbox(int masterId, int slaveId, boolean inverted) {
        master = new WPI_TalonFX(masterId);
        slave = new WPI_TalonFX(slaveId);
        this.inverted = inverted;
        motors = List.of(master, slave);

        init();
    }

    public WPI_TalonFX master() {
        return master;
    }

    private void init() {
        master.setInverted(inverted);
        slave.setInverted(inverted);

        slave.follow(master);

        ArrayList<TalonFX> _instruments = new ArrayList<TalonFX>();

        _orchestra = new Orchestra(_instruments);

        _orchestra.loadMusic("eye_of_tiger.chrp");
        _orchestra.play();

        // TODO read the DOC
//        master.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, TIMEOUT_MS);
//        master.setStatusFramePeriod(Status_12_Feedback1, 20, TIMEOUT_MS);
//        master.setStatusFramePeriod(Status_13_Base_PIDF0, 20, TIMEOUT_MS);
//        master.setStatusFramePeriod(Status_2_Feedback0, 5, TIMEOUT_MS);
//        master.configClosedLoopPeriod(SLOT_ID, 1, TIMEOUT_MS);
//        master.configOpenloopRamp(0.5);
//        master.configClosedloopRamp(0);
//        master.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, TIMEOUT_MS);

        // TODO use with Drive to position
//        master.configMotionAcceleration(2000, TIMEOUT_MS);
//        master.configMotionCruiseVelocity(2000, TIMEOUT_MS);

        motors.forEach(motor -> {
            motor.configFactoryDefault();
            _instruments.add(motor);
            motor.setSensorPhase(true);
            motor.config_kP(SLOT_ID, 0.1, TIMEOUT_MS);
            motor.config_kI(SLOT_ID, 0, TIMEOUT_MS);
            motor.config_kD(SLOT_ID, 0, TIMEOUT_MS);
            motor.config_kF(SLOT_ID, 0, TIMEOUT_MS);
            motor.config_IntegralZone(SLOT_ID, 100, TIMEOUT_MS);
            motor.configClosedLoopPeakOutput(SLOT_ID, 0.50, TIMEOUT_MS);

            motor.configNominalOutputForward(0, TIMEOUT_MS);
            motor.configNominalOutputReverse(0, TIMEOUT_MS);
            motor.configPeakOutputForward(1, TIMEOUT_MS);
            motor.configPeakOutputReverse(-1, TIMEOUT_MS);
            motor.configNeutralDeadband(0.001, TIMEOUT_MS);

            motor.enableVoltageCompensation(true);
            motor.configVoltageCompSaturation(11.5, TIMEOUT_MS);

            motor.configVoltageMeasurementFilter(32, TIMEOUT_MS);

            motor.setNeutralMode(Brake);
        });
    }
}
