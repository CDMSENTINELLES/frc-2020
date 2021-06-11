package ca.qc.cdm.sentinelles;

public class Constants {
    public static final class JoystickConstants {
        public static final int JOYSTICK_PORT = 1;
        public static final int JOYSTICK_SUB_PORT = 0;
    }

    public static final class DriveConstants {
        public static final int TIMEOUT_MS = 30;

        public static final int LEFT_MASTER = 1;
        public static final int LEFT_SLAVE = 2;
        public static final int RIGHT_MASTER = 3;
        public static final int RIGHT_SLAVE = 4;
    }

    public static final class ArmConstants {
        public static final int TIMEOUT_MS = 30;

        public static final int BASE_ARM = 5;
        public static final int MIDDLE_ARM = 8;
        public static final int CLAW_MASTER = 6;
        public static final int CLAW_SLAVE = 7;
    }
}
