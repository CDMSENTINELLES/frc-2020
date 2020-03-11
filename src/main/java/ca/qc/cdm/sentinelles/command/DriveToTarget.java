package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveToTarget extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private final NetworkTableEntry ledMode = table.getEntry("ledMode");
    private final NetworkTableEntry pipeline = table.getEntry("sentinelles_test");

    final double STEER_K = 0.03;                    // how hard to turn toward the target
    final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
    final double MAX_DRIVE_POSITIVE = 0.7;
    final double MAX_DRIVE_NEGATIVE = -0.7;

    private boolean m_LimelightHasValidTarget = false;
    private double m_LimelightDriveCommand = 0.0;
    private double m_LimelightSteerCommand = 0.0;

    public DriveToTarget(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        ledMode.setNumber(3);
        pipeline.setNumber(0);
    }

    @Override
    public void execute() {
        double tv = table.getEntry("tv").getDouble(0);
        double tx = table.getEntry("tx").getDouble(0);
        double ty = table.getEntry("ty").getDouble(0);
        double ta = table.getEntry("ta").getDouble(0);

        if (tv < 1.0) {
            m_LimelightHasValidTarget = false;
            m_LimelightDriveCommand = 0.0;
            m_LimelightSteerCommand = 0.0;
            return;
        }

        m_LimelightHasValidTarget = true;

        // Start with proportional steering
        double steer_cmd = tx * STEER_K;
        m_LimelightSteerCommand = steer_cmd;

        // try to drive forward until the target area reaches our desired area
        double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

        // don't let the robot drive too fast into the goal
        if (drive_cmd > MAX_DRIVE_POSITIVE) {
            drive_cmd = MAX_DRIVE_POSITIVE;
        }
        else if (drive_cmd < MAX_DRIVE_NEGATIVE) {
            drive_cmd = MAX_DRIVE_NEGATIVE;
        }
        m_LimelightDriveCommand = drive_cmd;

        if (m_LimelightHasValidTarget) {
            driveSubsystem.drive(m_LimelightDriveCommand, m_LimelightSteerCommand);
        } else {
            driveSubsystem.drive(0.0, 0.0);
        }
//
//        double kSteer = -0.0325;
//        double moveCom = 0.0;
//        double steerCom = 0.0;
//
//        if (tv == 1) {
//            if (ty < 18) {
//                moveCom = 0.25;
//            } else if (ty > 21) {
//                moveCom = -0.25;
//            }
//            if (tx >= 2) {
//                steerCom = kSteer * -ty + 0.05;
//            } else if (tx <= -2) {
//                steerCom = kSteer * ty - 0.05;
//            }
//            if (steerCom > 0.5) {
//                steerCom = 0.5;
//            } else if (steerCom < -0.5) {
//                steerCom = -0.5;
//            }
//        } else if (tv == 0) {
//            moveCom = 0;
//            steerCom = 0.5;
//        }
//        if (tx <= 2 && tx >= -2 && ty > 18 && ty < 20) {
//            driveSubsystem.drive.stopMotor();
//        } else {
//            driveSubsystem.drive(moveCom, steerCom);
//        }
    }

    @Override
    public void end(boolean interrupted) {
        ledMode.setNumber(1);
    }
}
