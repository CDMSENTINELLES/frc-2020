package ca.qc.cdm.sentinelles.subsystems;

import ca.qc.cdm.sentinelles.Prefs;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.ctre.phoenix.motorcontrol.InvertType.FollowMaster;

public class DriveTrain extends SubsystemBase {
    private Prefs prefs = Prefs.getPrefs();

    // The motors on the left side of the drive.
    private WPI_VictorSPX leftMotor1 = new WPI_VictorSPX(1);
    private WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(2);
    private WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(3);
    private WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(4);

//    private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
//    private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

    private ConstantAccelerationCalculator ramp = new ConstantAccelerationCalculator(prefs.getRamp_C());

    private DifferentialDrive drive = new DifferentialDrive(leftMotor1, rightMotor1);

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveTrain() {
        super();

        leftMotor2.follow(leftMotor1);
        leftMotor1.setInverted(false);
        leftMotor2.setInverted(FollowMaster);

        // Make M4 follow exactly what M3 does
        rightMotor2.follow(rightMotor1);
        rightMotor1.setInverted(false);
        rightMotor2.setInverted(FollowMaster);

    }

    public void drive(double move, double turn) {
        System.out.println("=========================  Drive");
        drive.arcadeDrive(move, turn);
    }


}