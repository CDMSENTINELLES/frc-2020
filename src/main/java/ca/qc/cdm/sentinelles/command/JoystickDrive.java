package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static ca.qc.cdm.sentinelles.Constants.JoystickConstants.XBOX_PORT;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

public class JoystickDrive extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final XboxController xboxController;

    public JoystickDrive(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.xboxController = new XboxController(XBOX_PORT);
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        double move = deadband(xboxController.getY(kLeft));
        double rotate = deadband(xboxController.getX(kLeft) * -1);
        double smooth = calibrateSlider(-1.0 * xboxController.getTriggerAxis(kRight));

        driveSubsystem.drive(move * smooth, rotate * smooth);
    }

    protected static double calibrateSlider(double value) {
        return (value + 1) / (2);
    }

    private double deadband(double value) {
        return Math.abs(value) < 0.10 ? 0 : value;
    }
}