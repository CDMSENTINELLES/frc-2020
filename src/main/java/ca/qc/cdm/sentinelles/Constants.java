package ca.qc.cdm.sentinelles;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Constants {
    public static final class DriveConstants {
        public static final int frontLeftDriveCAN = 0;
        public static final int frontRightDriveCAN = 3;
        public static final int backLeftDriveCAN = 1;
        public static final int backRightDriveCAN = 2;

        public static final WPI_TalonFX frontLeftDrive = new WPI_TalonFX(0);
        public static final WPI_TalonFX frontRightDrive = new WPI_TalonFX(3);
        public static final WPI_TalonFX backLeftDrive = new WPI_TalonFX(1);
        public static final WPI_TalonFX backRightDrive = new WPI_TalonFX(2);

        public static final boolean _rSetInvertedBool = false;
        public static final boolean _lSetInvertedBool = false;


        public static final DifferentialDrive drive = new DifferentialDrive(frontLeftDrive, frontRightDrive);

        public static final int kTimeoutMs = 10;

        public static final int kSlotIdx = 0;
        public static final int kPIDLoopIdx = 0;
        static final Gains kGains = new Gains(0.2, 0.0, 0.0, 0.2, 0, 1.0);

        public static final int kEncoderCPR = 1024;
        public static final double kWheelDiameterInches = 6;
        public static final double kEncoderDistancePerPulse = (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 1;
    }
}
