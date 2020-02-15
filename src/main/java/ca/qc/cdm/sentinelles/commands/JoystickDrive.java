package ca.qc.cdm.sentinelles.commands;

import ca.qc.cdm.sentinelles.subsystems.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static ca.qc.cdm.sentinelles.Constants.JoystickConstants.JOYSTICK_PORT;

public class JoystickDrive extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final Joystick joystick;

    public JoystickDrive(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.joystick = new Joystick(JOYSTICK_PORT);
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Init Joystick Drive Command.");
    }

    @Override
    public void execute() {
        double move = joystick.getX();
        double rotate = joystick.getY();

        driveSubsystem.drive(move, rotate);
    }
}
