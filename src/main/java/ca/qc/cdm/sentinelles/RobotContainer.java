package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.commands.JoystickDrive;
import ca.qc.cdm.sentinelles.commands.NullCommand;
import ca.qc.cdm.sentinelles.subsystems.drive.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
    // Init Subsystems
    public final DriveSubsystem drivetrain = new DriveSubsystem();

    // Init Commands
    private final Command autoCommand = new NullCommand();

    public final JoystickDrive joystickDrive = new JoystickDrive(drivetrain);

    public RobotContainer() {
        drivetrain.setDefaultCommand(joystickDrive);
    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
