package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.commands.DriveWithGamepad;
import ca.qc.cdm.sentinelles.commands.NullCommand;
import ca.qc.cdm.sentinelles.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
    public static DriveTrain driveTrain = new DriveTrain();
    private final DriveWithGamepad driveWithGamepad = new DriveWithGamepad(driveTrain);

    private final Command autoCommand = new NullCommand();

    public RobotContainer() {
        driveTrain.setDefaultCommand(driveWithGamepad);
    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
