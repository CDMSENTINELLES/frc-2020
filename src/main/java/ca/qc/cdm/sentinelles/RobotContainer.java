package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.command.JoystickDrive;
import ca.qc.cdm.sentinelles.command.NullCommand;
import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
    // Subsystems
    private DriveSubsystem driveTrain;

    // Commands
    private final Command autoCommand = new NullCommand();

    public RobotContainer() {
        initSubsystem();
        buttonBindings();

        driveTrain.setDefaultCommand(new JoystickDrive(driveTrain));
    }

    private void initSubsystem() {
        driveTrain = new DriveSubsystem();
    }

    private void buttonBindings() {

    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
