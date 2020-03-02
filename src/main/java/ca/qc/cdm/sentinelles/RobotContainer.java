package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.command.DriveToTarget;
import ca.qc.cdm.sentinelles.command.JoystickDrive;
import ca.qc.cdm.sentinelles.command.NullCommand;
import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static ca.qc.cdm.sentinelles.Constants.JoystickConstants.XBOX_PORT;
import static edu.wpi.first.wpilibj.XboxController.Button.kA;

public class RobotContainer {
    private final XboxController xboxController = new XboxController(XBOX_PORT);

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
        JoystickButton buttonA = new JoystickButton(xboxController, kA.value);
        buttonA.whileHeld(new DriveToTarget(driveTrain));
    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}