package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static ca.qc.cdm.sentinelles.Constants.JoystickConstants.XBOX_PORT;
import static edu.wpi.first.wpilibj.SerialPort.Port.kUSB;

public class DriveToTarget extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final XboxController xboxController;
    private AHRS navx;
    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private NetworkTableEntry ledMode = table.getEntry("ledMode");
    private NetworkTableEntry pipeline = table.getEntry("sentinelles_test");

    public DriveToTarget(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.xboxController = new XboxController(XBOX_PORT);
        this.navx = new AHRS(kUSB);
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        ledMode.setNumber(3);
    }

    @Override
    public void execute() {
//        double tv = table.getEntry("tv").getDouble(0);
//        double tx = table.getEntry("tx").getDouble(0);
//        double ty = table.getEntry("ty").getDouble(0);
//
//        boolean active = true;
//
//        double kSteer = -0.05,
//                kDrive = 0.2,
//                steerCommand = 0.0,
//                driveCommand = 0.0,
//                headingError = -tx,
//                distanceError = -ty,
//                minCommand = 0.0,
//                txDeadband = 1.0;
//
//        System.out.println("tv " + tv);
//
//        if (tv == 1) {
//            if (tx > txDeadband) {
//                steerCommand = kSteer * headingError -minCommand;
//                return;
//            } else if (tx < txDeadband) {
//                steerCommand = kSteer * headingError + minCommand;
//                return;
//            }
//
//            driveCommand = kDrive * distanceError;
//            if (headingError <= 2.0 && distanceError <= 1.0) {
//                System.out.println("At target");
//            }
//            else {
//                System.out.println("tx: " + tx);
//                System.out.println("ty: " + ty);
//                System.out.println("aligning to target");
//                return;
//            }
//        }
//        else {
//            System.out.println("No target, turning around in circles...");
//            System.out.println("--RELEASE A TO DISABLE--");
//            driveCommand = 0;
//            steerCommand = 0.4;
//            return;
//        }

//        driveSubsystem.drive(driveCommand, steerCommand);

//        System.out.println("Execute Drive to target Command");
//        driveSubsystem.drive(-1 * 0.5, 0);
    }

    @Override
    public void end(boolean interrupted) {
        ledMode.setNumber(1);
    }
}
