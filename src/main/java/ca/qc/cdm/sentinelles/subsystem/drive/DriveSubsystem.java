package ca.qc.cdm.sentinelles.subsystem.drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {
    private final DriveGearbox leftDriveGearbox = new DriveGearbox(LEFT_MASTER, LEFT_SLAVE, true);
    private final DriveGearbox rightDriveGearbox = new DriveGearbox(RIGHT_MASTER, RIGHT_SLAVE, true);

    public final DifferentialDrive drive = new DifferentialDrive(
            leftDriveGearbox.master(),
            rightDriveGearbox.master()
    );

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem() {
        super();
        drive.setSafetyEnabled(true);
        drive.setExpiration(0.1);
        drive.setMaxOutput(1.0);
    }

    public void drive(double move, double turn) {
        drive.arcadeDrive(move, turn);


    }


}