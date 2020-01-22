package ca.qc.cdm.sentinelles.subsystems;

import ca.qc.cdm.sentinelles.Prefs;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private Prefs prefs = Prefs.getPrefs();

    // The motors on the left side of the drive.
    private WPI_VictorSPX frontLeftDrive = new WPI_VictorSPX(1);
    private WPI_VictorSPX frontRightDrive = new WPI_VictorSPX(2);
    private WPI_VictorSPX backLeftDrive = new WPI_VictorSPX(3);
    private WPI_VictorSPX backRightDrive = new WPI_VictorSPX(4);

    private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
    private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

    private ConstantAccelerationCalculator ramp = new ConstantAccelerationCalculator(prefs.getRamp_C());

    private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveTrain() {
        super();
    }

    public void drive(double move, double turn) {
        System.out.println("=========================  Drive");
        drive.arcadeDrive(move, turn);
    }


}