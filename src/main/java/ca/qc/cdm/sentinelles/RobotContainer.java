package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.command.ArmControl;
import ca.qc.cdm.sentinelles.command.JoystickDrive;
import ca.qc.cdm.sentinelles.command.NullCommand;
import ca.qc.cdm.sentinelles.subsystem.arm.ArmSubsystem;
import ca.qc.cdm.sentinelles.subsystem.claw.ClawSubsystem;
import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import ca.qc.cdm.sentinelles.subsystem.launch.LaunchSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
    // Subsystems
    private DriveSubsystem driveTrain;
    private LaunchSubsystem launchSubsystem;
    private ClawSubsystem clawSubsystem;
    private ArmSubsystem armSubsystem;

    // Commands
    private final Command autoCommand = new NullCommand();

    public RobotContainer() {
        initSubsystem();
        buttonBindings();

        driveTrain.setDefaultCommand(new JoystickDrive(driveTrain));
        launchSubsystem.setDefaultCommand(new ArmControl(launchSubsystem, armSubsystem, clawSubsystem));
    }

    private void initSubsystem() {
        driveTrain = new DriveSubsystem();
        launchSubsystem = new LaunchSubsystem();
        clawSubsystem = new ClawSubsystem();
        armSubsystem = new ArmSubsystem();
    }

    private void buttonBindings() {

    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
