package ca.qc.cdm.sentinelles.commands;

import ca.qc.cdm.sentinelles.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.frontLeftDrive;

public class JoystickDrive extends CommandBase {
    private final DriveTrain driveTrain;

    public JoystickDrive(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
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
        double motorOutput = frontLeftDrive.getMotorOutputPercent();

        _sb.append
        driveTrain.drive(move, rotate);
    }
}
