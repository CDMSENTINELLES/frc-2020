package ca.qc.cdm.sentinelles.subsystems.drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {
    private final DriveGearbox leftDriveGearbox = new DriveGearbox(leftMaster, leftSlave, false, true);
    private final DriveGearbox rightDriveGearbox = new DriveGearbox(rightMaster, rightSlave, false, true);

    public final DifferentialDrive drive = new DifferentialDrive(
            leftDriveGearbox.master(),
            rightDriveGearbox.master()
    );

    public static final int kSlotIdx = 0;
    public static final int kPIDLoopIdx = 0;

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem() {
        super();
        init();
    }

    private void init() {
        System.out.println("Init DriveTrain Subsystem.");
        drive.setRightSideInverted(false);
    }

    public void drive(double move, double turn) {
        drive.arcadeDrive(move, turn);
    }
}