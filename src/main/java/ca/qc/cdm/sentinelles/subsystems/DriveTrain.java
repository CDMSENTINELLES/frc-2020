package ca.qc.cdm.sentinelles.subsystems;

import ca.qc.cdm.sentinelles.Gains;
import ca.qc.cdm.sentinelles.Prefs;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;

public class DriveTrain extends SubsystemBase {

    private WPI_TalonFX frontLeftDrive = new WPI_TalonFX(frontLeftDriveCAN);
    private WPI_TalonFX frontRightDrive = new WPI_TalonFX(frontRightDriveCAN);
    private WPI_TalonFX backLeftDrive = new WPI_TalonFX(backLeftDriveCAN);
    private WPI_TalonFX backRightDrive = new WPI_TalonFX(backRightDriveCAN);

    public static final boolean _rSetInvertedBool = false;
    public static final boolean _lSetInvertedBool = false;

    public final DifferentialDrive drive = new DifferentialDrive(frontLeftDrive, frontRightDrive);

    public static final int kTimeoutMs = 10;

    public static final int kSlotIdx = 0;
    public static final int kPIDLoopIdx = 0;

    static final Gains kGains = new Gains(0.2, 0.0, 0.0, 0.2, 0, 1.0);

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveTrain() {
        super();
        init();

    }

    private void init() {
        int _smoothing = 0;
        int _pov = -1;

        StringBuilder _sb = new StringBuilder();

        frontLeftDrive.configFactoryDefault();
        frontRightDrive.configFactoryDefault();
        backLeftDrive.configFactoryDefault();
        backRightDrive.configFactoryDefault();

        frontLeftDrive.setInverted(_lSetInvertedBool);
        frontRightDrive.setInverted(_rSetInvertedBool);
        backLeftDrive.setInverted(_lSetInvertedBool);
        backRightDrive.setInverted(_rSetInvertedBool);

        drive.setRightSideInverted(false);

        frontLeftDrive.configVoltageCompSaturation(11.5, kTimeoutMs);
        frontRightDrive.configVoltageCompSaturation(11.5, kTimeoutMs);
        backLeftDrive.configVoltageCompSaturation(11.5, kTimeoutMs);
        backRightDrive.configVoltageCompSaturation(11.5, kTimeoutMs);

        frontRightDrive.enableVoltageCompensation(true);
        frontLeftDrive.enableVoltageCompensation(true);
        backRightDrive.enableVoltageCompensation(true);
        backLeftDrive.enableVoltageCompensation(true);

        frontRightDrive.configVoltageMeasurementFilter(32, kTimeoutMs);
        frontLeftDrive.configVoltageMeasurementFilter(32, kTimeoutMs);
        backLeftDrive.configVoltageMeasurementFilter(32, kTimeoutMs);
        backRightDrive.configVoltageMeasurementFilter(32, kTimeoutMs);

        frontLeftDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        frontRightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        // backLeftDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        // backRightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);

        frontRightDrive.configNeutralDeadband(0.001, kTimeoutMs);
        frontLeftDrive.configNeutralDeadband(0.001, kTimeoutMs);
        // backRightDrive.configNeutralDeadband(0.001, kTimeoutMs);
        // backLeftDrive.configNeutralDeadband(0.001, kTimeoutMs);

        frontLeftDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,  kTimeoutMs);
        frontRightDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,  kTimeoutMs);
        // backLeftDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,  kTimeoutMs);
        // backRightDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10,  kTimeoutMs);

        frontRightDrive.configNominalOutputForward(0, kTimeoutMs);
        frontLeftDrive.configNominalOutputForward(0, kTimeoutMs);
        // backRightDrive.configNominalOutputForward(0, kTimeoutMs);
        // backLeftDrive.configNominalOutputForward(0, kTimeoutMs);

        frontLeftDrive.configNominalOutputReverse(0, kTimeoutMs);
        frontRightDrive.configNominalOutputReverse(0, kTimeoutMs);
        // backLeftDrive.configNominalOutputReverse(0, kTimeoutMs);
        // backRightDrive.configNominalOutputReverse(0, kTimeoutMs);

        frontRightDrive.configPeakOutputForward(1, kTimeoutMs);
        frontLeftDrive.configPeakOutputForward(1, kTimeoutMs);
        // backLeftDrive.configPeakOutputForward(1, kTimeoutMs);
        // backRightDrive.configPeakOutputForward(1, kTimeoutMs);

        frontLeftDrive.configPeakOutputReverse(-1, kTimeoutMs);
        frontRightDrive.configPeakOutputReverse(-1, kTimeoutMs);
        // backRightDrive.configPeakOutputReverse(-1, kTimeoutMs);
        // backLeftDrive.configPeakOutputReverse(-1, kTimeoutMs);

        frontLeftDrive.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
        frontLeftDrive.config_kF(kSlotIdx, kGains.kF, kTimeoutMs);
        frontLeftDrive.config_kP(kSlotIdx, kGains.kP, kTimeoutMs);
        frontLeftDrive.config_kI(kSlotIdx, kGains.kI, kTimeoutMs);
        frontLeftDrive.config_kD(kSlotIdx, kGains.kD, kTimeoutMs);

        frontRightDrive.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
        frontRightDrive.config_kF(kSlotIdx, kGains.kF, kTimeoutMs);
        frontRightDrive.config_kP(kSlotIdx, kGains.kP, kTimeoutMs);
        frontRightDrive.config_kI(kSlotIdx, kGains.kI, kTimeoutMs);
        frontRightDrive.config_kD(kSlotIdx, kGains.kD, kTimeoutMs);

        frontLeftDrive.configMotionCruiseVelocity(15000, kTimeoutMs);
        frontLeftDrive.configMotionCruiseVelocity(15000, kTimeoutMs);

        frontRightDrive.configMotionAcceleration(6000, kTimeoutMs);
        frontLeftDrive.configMotionAcceleration(6000, kTimeoutMs);

        frontLeftDrive.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
        frontRightDrive.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);

        frontLeftDrive.configOpenloopRamp(3);
        frontRightDrive.configOpenloopRamp(3);

        frontRightDrive.configClosedloopRamp(0);
        frontLeftDrive.configClosedloopRamp(0);

        backLeftDrive.follow(frontLeftDrive);
        backRightDrive.follow(frontRightDrive);
    }

    public void drive(double move, double turn) {
        drive.arcadeDrive(move, turn);
    }
}