package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveToTarget extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private AHRS navx;

    public DriveToTarget(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.navx = new AHRS(SerialPort.Port.kUSB);
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        driveSubsystem.drive(-0.5, 0.0);
        Timer.delay(2.0);
        driveSubsystem.drive(0.0, 0.0);
    }
}
