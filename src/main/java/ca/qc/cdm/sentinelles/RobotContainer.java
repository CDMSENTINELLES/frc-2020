package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.commands.NullCommand;
import ca.qc.cdm.sentinelles.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {
    public static DriveTrain drivetrain = new DriveTrain();

    XboxController joystick = new XboxController(1);

    private final Command autoCommand = new NullCommand();

    public RobotContainer() {
        drivetrain.setDefaultCommand(
                // A split-stick arcade command, with forward/backward controlled by the left
                // hand, and turning controlled by the right.
                new RunCommand(() -> {
                    double move = joystick.getX();
                    double rotate = joystick.getY();
                    drivetrain.drive(move, rotate);
                }));

    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
