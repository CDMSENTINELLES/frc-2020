package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class DriveToTarget extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private AHRS navx;
    private JoystickButton buttonA;

    public DriveToTarget(DriveSubsystem driveSubsystem, JoystickButton buttonA) {
        this.driveSubsystem = driveSubsystem;
        this.buttonA = buttonA;
        this.navx = new AHRS(SerialPort.Port.kUSB);
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        System.out.println("Execute Drive to target Command");
        driveSubsystem.drive(-1 * 0.5, 0);
    }
}
