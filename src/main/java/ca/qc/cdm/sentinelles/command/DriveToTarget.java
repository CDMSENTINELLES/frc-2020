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

        double kSteer = -0.0325;
        double moveCom = 0.0;
        double steerCom = 0.0;

        if (tv == 1) {
            if (ty < 18) {
                moveCom = 0.25;
            } else if (ty > 21) {
                moveCom = -0.25;
            }
            if (tx >= 2) {
                steerCom = kSteer * -ty + 0.05;
            } else if (tx <= -2) {
                steerCom = kSteer * ty - 0.05;
            }
            if (steerCom > 0.5) {
                steerCom = 0.5;
            } else if (steerCom < -0.5) {
                steerCom = -0.5;
            }
        } else if (tv == 0) {
            moveCom = 0;
            steerCom = 0.5;
        }
        if (tx <= 2 && tx >= -2 && ty > 18 && ty < 20) {
            driveSubsystem.drive.stopMotor();
        } else {
            driveSubsystem.drive(moveCom, steerCom);
        }
    }

    @Override
    public void end(boolean interrupted) {
        ledMode.setNumber(1);
    }
}
