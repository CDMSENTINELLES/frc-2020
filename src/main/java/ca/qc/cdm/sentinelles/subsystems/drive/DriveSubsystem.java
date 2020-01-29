package ca.qc.cdm.sentinelles.subsystems.drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;
import static ca.qc.cdm.sentinelles.Constants.Shuffleboard.DRIVE_TRAIN_TAB;

public class DriveSubsystem extends SubsystemBase {
    private final DriveGearbox leftDriveGearbox = new DriveGearbox(leftMaster, leftSlave, false, true);
    private final DriveGearbox rightDriveGearbox = new DriveGearbox(rightMaster, rightSlave, false, true);

    public final DifferentialDrive drive = new DifferentialDrive(
            leftDriveGearbox.master(),
            rightDriveGearbox.master()
    );

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem() {
        super();
        init();
    }

    private void init() {
        Shuffleboard.getTab(DRIVE_TRAIN_TAB);
        drive.setRightSideInverted(false);
    }

    public void drive(double move, double turn) {
        Shuffleboard.selectTab(DRIVE_TRAIN_TAB);

        Shuffleboard
                .getTab(DRIVE_TRAIN_TAB)
                .add("Speed", move)
                .withWidget(BuiltInWidgets.kAccelerometer);

        drive.arcadeDrive(move, turn);
    }
}