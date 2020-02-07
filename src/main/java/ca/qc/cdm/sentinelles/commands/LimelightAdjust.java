package ca.qc.cdm.sentinelles.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import ca.qc.cdm.sentinelles.subsystems.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;

import static ca.qc.cdm.sentinelles.Constants.JoystickConstants.JOYSTICK_PORT;

public class LimelightAdjust extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final Joystick joystick;


    public LimelightAdjust(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.joystick =  new Joystick(JOYSTICK_PORT);
        addRequirements(driveSubsystem);
    }

    public LimelightAdjust(DriveSubsystem driveSubsystem, Joystick joystick) {
        this.driveSubsystem = driveSubsystem;
        this.joystick = joystick;
    }

    @Override
    public void initialize() {
        System.out.println("Init Limelight Drive.");
    }

    @Override
    public void execute() {
        double move = joystick.getX();
        double rotate = joystick.getY();

        driveSubsystem.drive(move, rotate);
    }
}
