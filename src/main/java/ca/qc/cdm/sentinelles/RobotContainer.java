package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.command.JoystickDrive;
import ca.qc.cdm.sentinelles.command.NullCommand;
import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static edu.wpi.first.wpilibj.XboxController.Button.kA;

public class RobotContainer {
    private final XboxController xboxController = new XboxController(0);

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
        new JoystickButton(xboxController, kA.value)
                .whenPressed(new RunCommand(() -> System.out.println("Ka-PRESSED")))
                .whenReleased(new RunCommand(() -> System.out.println("Ka-RELEASED")));
    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
