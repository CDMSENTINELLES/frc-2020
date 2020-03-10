package ca.qc.cdm.sentinelles.subsystem.launch;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LaunchSubsystem extends SubsystemBase {
    public final launchGearbox mainGearbox = new launchGearbox(9, true);

    public void launchBallRPM (double RPM, double RPMperS, int slotID) {
        mainGearbox.master.setSmartMotionMaxAccel();
    }
    public void launchBall (int RPM, int kF, int kP, int kI, int kD, int kIzone, boolean selection) {
        if selection
    }
}
