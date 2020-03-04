package ca.qc.cdm.sentinelles.subsystem.drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {
    private final DriveGearbox leftDriveGearbox = new DriveGearbox(LEFT_MASTER, LEFT_SLAVE, true);
    private final DriveGearbox rightDriveGearbox = new DriveGearbox(RIGHT_MASTER, RIGHT_SLAVE, true);
    private boolean hasSeenTarget = false;
    double area;

    private double xOffset;

    public final static double TOO_CLOSE = 20;
    public final static double TOO_FAR = 15;
    public final static double SPEED = 0.5;

    private final static double TARGET_AREA = 17.5;
    private final static double DEADBAND_RADIUS = 2.5;

    private boolean trackingComplete;
    private boolean notFound;

    private int leftSenPos = leftDriveGearbox.master().getSelectedSensorPosition(0);
    private int rightSenPos = rightDriveGearbox.master().getSelectedSensorPosition(0);

//    private double distanceLeft = leftSenPos

    public final DifferentialDrive drive = new DifferentialDrive(
            leftDriveGearbox.master(),
            rightDriveGearbox.master()
    );

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem() {
        super();
        drive.setMaxOutput(1.0);
    }

    public void drive(double move, double turn) {
        drive.setSafetyEnabled(true);
        drive.arcadeDrive(move, turn);
    }

    public void disable() {
        drive.stopMotor();
    }
}