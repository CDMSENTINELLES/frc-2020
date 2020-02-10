package ca.qc.cdm.sentinelles.subsystems.drive;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {
    private final NetworkTableEntry speedEntry;

    private final DriveGearbox leftDriveGearbox = new DriveGearbox(leftMaster, leftSlave, false, true);
    private final DriveGearbox rightDriveGearbox = new DriveGearbox(rightMaster, rightSlave, false, true);

    public final DifferentialDrive drive = new DifferentialDrive(
            leftDriveGearbox.master(),
            rightDriveGearbox.master()
    );

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem(NetworkTableEntry speedEntry) {
        super();
        init();
        this.speedEntry = speedEntry;
    }

    private void init() {
        drive.setRightSideInverted(false);
    }

    public void drive(double move, double turn) {
        speedEntry.forceSetNumber(move);
        drive.arcadeDrive(move, turn);
    }
}