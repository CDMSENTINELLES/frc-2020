package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.commands.JoystickDrive;
import ca.qc.cdm.sentinelles.commands.NullCommand;
import ca.qc.cdm.sentinelles.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {
    // Init Subsystems
    public final DriveTrain drivetrain = new DriveTrain();

    // Init Commands
    public final JoystickDrive joystickDrive = new JoystickDrive(drivetrain);

    private final Command autoCommand = new NullCommand();

    public RobotContainer() {
        drivetrain.setDefaultCommand(joystickDrive);

    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
