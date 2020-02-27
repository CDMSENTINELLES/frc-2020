package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static ca.qc.cdm.sentinelles.Constants.JoystickConstants.JOYSTICK_PORT;

public class JoystickDrive extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final XboxController getXboxController;

    public JoystickDrive(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.getXboxController = new XboxController(1);
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Init Joystick Drive Command.");
    }

    @Override
    public void execute() {
        double move = deadband(getXboxController.getY(GenericHID.Hand.kLeft));
        double rotate = deadband(getXboxController.getX(GenericHID.Hand.kLeft) * -1);
        double smooth = calibrateSlider(-1.0 * getXboxController.getTriggerAxis(GenericHID.Hand.kRight));

        driveSubsystem.drive(move * smooth , rotate * smooth);
    }

    protected static double calibrateSlider(double value) {
        return (value + 1) / (2);
    }

    private double deadband(double value) {
        return Math.abs(value) < 0.10 ? 0 : value;
    }
}