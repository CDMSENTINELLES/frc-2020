package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.Constants.OIConstants;
import ca.qc.cdm.sentinelles.commands.NullCommand;
import ca.qc.cdm.sentinelles.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
    private final DriveSubsystem drive = new DriveSubsystem();
    private XboxController xboxController = new XboxController(OIConstants.kDriverControllerPort);

    private final Command autoCommand = new NullCommand();

    public RobotContainer() {
        configureButtonBindings();

        drive.setDefaultCommand(
                // A split-stick arcade command, with forward/backward controlled by the left
                // hand, and turning controlled by the right.
                new RunCommand(() -> drive
                        .arcadeDrive(xboxController.getY(GenericHID.Hand.kLeft),
                                xboxController.getX(GenericHID.Hand.kRight)), drive));

    }

    private void configureButtonBindings() {
//        new JoystickButton(xboxController, XboxController.Button.kBumperRight.value)
//                .whenPressed(() -> drive.setMaxOutput(0.5))
//                .whenReleased(() -> drive.setMaxOutput(1));
    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
