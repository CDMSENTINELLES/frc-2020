package ca.qc.cdm.sentinelles;

import edu.wpi.first.wpilibj.RobotBase;

public class Main {
    private Main() {
    }

    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }
}


