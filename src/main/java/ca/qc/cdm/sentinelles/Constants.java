package ca.qc.cdm.sentinelles;

public class Constants {
    public static final class JoystickConstants {
        public static final int JOYSTICK_PORT = 0;
        public static final int SEC_JOYSTICK_PORT = 1;
    }

    public static final class DriveConstants {
        public static final int kTimeoutMs = 10;
        public static final int leftMaster = 1;
        public static final int leftSlave = 2;
        public static final int rightMaster = 3;
        public static final int rightSlave = 4;
    }

    public static final class SubsystemConstants {
        public static final int winchMaster = 5;
        public static final int winchSlave1 = 6;
        public static final int winchSlave2 = 7;
        public static final int winchSlave3 = 8;
        public static final int launchMaster = 9;
        public static final int launchSlave = 10;
        public static final int adjustLaunchMaster = 11;
        public static final int adjustLaunchSlave = 12;
        public static final int collectBalls = 13;
        public static final int storage1 = 14;
        public static final int storage2 = 15;
    }
}
