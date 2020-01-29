package ca.qc.cdm.sentinelles.subsystems.drive;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import java.util.List;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;
import static ca.qc.cdm.sentinelles.subsystems.drive.DriveSubsystem.*;

public class DriveGearbox {
    private static final Gains kGains_Distance = new Gains(0.1, 0.0, 0.0, 0.0, 100, 0.50);
    private static final Gains kGains_Turning = new Gains(2.0, 0.0, 4.0, 0.0, 200, 1.00);
    private static final Gains kGains_Velocity = new Gains(0.1, 0.0, 20.0, 1023.0/6800.0, 300, 0.50);
    private static final Gains kGains_MotProf = new Gains(1.0, 0.0, 0.0, 1023.0/6800.0, 400, 1.00);

    private static final int kSlot_Distance = 0;
    private static final int kSlot_Turning = 1;
    private static final int kSlot_Velocity = 2;
    private static final int kSlot_MotProf = 3;

    private final static double kRotationsToTravel = 0;

    private final static int kSensorUnitsPerRotation = 4096;

    private final static int kNumButtonsPlusOne = 10;

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

        master.config_kP(kSlot_Distance, kGains_Distance.kP, kTimeoutMs);
        master.config_kI(kSlot_Distance, kGains_Distance.kI, kTimeoutMs);
        master.config_kD(kSlot_Distance, kGains_Distance.kD, kTimeoutMs);
        master.config_kF(kSlot_Distance, kGains_Distance.kF, kTimeoutMs);
        master.config_IntegralZone(kSlot_Distance, kGains_Distance.kIzone, kTimeoutMs);
        master.configClosedLoopPeakOutput(kSlot_Distance, kGains_Distance.kPeakOutput, kTimeoutMs);

        master.configClosedLoopPeriod(0,1, kTimeoutMs);
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

        zeroSensors();
    }

    public void zeroSensors() {
        motors.forEach(motor -> {
            System.out.println("MAKE SURE ROBOT IS STOPPED.");
            motor.getSensorCollection().setIntegratedSensorPosition(0, kTimeoutMs);
            System.out.println("(INTEGRATED SENSORS) - All Integrated Talon FX 2048 CPR Encoders are now at 0.");
       });
    }

    public void decreaseSmoothing(int smoothing) {
        smoothing--;
        if (smoothing < 0) smoothing = 0;
        master.configMotionSCurveStrength(smoothing);
        System.out.println("(DRIVE BASE SMOOTHING) Smoothing is now: " +smoothing);
    }

    public void increaseSmoothing(int smoothing) {
        smoothing++;
        if (smoothing < 10) smoothing = 10;
        master.configMotionSCurveStrength(smoothing);
        System.out.println("(DRIVE BASE SMOOTHING) Smoothing is now: " +smoothing);
    }

    // TODO
    // Work on Arcade Drive using Arbitrary Feed Forward
    // Work on Motion Magic with Arbitrary Feed Forward
    // Work on switching mechanism

    // Work on making slow mode
    // Work on deadband?
    // Work on making a command for zeroSensors, decrease smoothing and increase smoothing functions

    // Work on Motion Profiling for Auto mode

    // FOR FRIDAY!


}
