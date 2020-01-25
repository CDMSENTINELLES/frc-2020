package ca.qc.cdm.sentinelles.commands;

import ca.qc.cdm.sentinelles.subsystems.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class JoystickDrive extends CommandBase {
    private final DriveSubsystem driveSubsystem;

    public JoystickDrive(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Init DriveTrain Joystick Drive.");
    }

    @Override
    public void execute() {
        System.out.println("Executing Joystick Drive.");
        Joystick joystick = new Joystick(0);
        double move = joystick.getX();
        double rotate = joystick.getY();

        driveSubsystem.drive(move, rotate);
    }
}
