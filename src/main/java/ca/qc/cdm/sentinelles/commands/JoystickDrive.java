package ca.qc.cdm.sentinelles.commands;

import ca.qc.cdm.sentinelles.subsystems.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static ca.qc.cdm.sentinelles.Constants.JoystickConstants.joystickPort;

public class JoystickDrive extends CommandBase {
    private final DriveSubsystem driveSubsystem;

    public JoystickDrive(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Init DriveTrain Joystick Drive.");
        // Get Buttons from Joystick
    }

    @Override
    public void execute() {
        Joystick joystick = new Joystick(joystickPort);

        double move = joystick.getX();
        double rotate = joystick.getY();

        SmartDashboard.putNumber("DriveTrain Move", move);
        SmartDashboard.putNumber("DriveTrain rotate", rotate);

        driveSubsystem.drive(move, rotate);
    }
}
