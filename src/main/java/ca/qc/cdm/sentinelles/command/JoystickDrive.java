package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
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
        double move = deadband(joystick.getX());
        double rotate = deadband(joystick.getY());
        double smooth = calibrateSlider(-1.0 * joystick.getRawAxis(3));

        driveSubsystem.drive(move * smooth , rotate);
    }

    protected static double calibrateSlider(double value) {
        return (value + 1) / (2);
    }

    private double deadband(double value) {
        return Math.abs(value) < 0.10 ? 0 : value;
    }
}
