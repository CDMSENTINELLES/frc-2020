package ca.qc.cdm.sentinelles.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static com.ctre.phoenix.motorcontrol.InvertType.FollowMaster;

public class DriveSubsystem extends SubsystemBase {
    // The motors on the left side of the drive.
    private WPI_VictorSPX leftMotor1 = new WPI_VictorSPX(1);
    private WPI_VictorSPX leftMotor2 = new WPI_VictorSPX(2);
    private WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(3);
    private WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(4);

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem() {
        super();
        // Make M2 follow exactly what M1 does
        leftMotor2.follow(leftMotor1);
        leftMotor1.setInverted(false);
        leftMotor2.setInverted(FollowMaster);

        // Make M4 follow exactly what M3 does
        rightMotor2.follow(rightMotor1);
        rightMotor1.setInverted(false);
        rightMotor2.setInverted(FollowMaster);

        DifferentialDrive m_drive = new DifferentialDrive(leftMotor1, rightMotor2);

    }

    /**
     * Drives the robot using arcade controls.
     *
     * @param fwd the commanded forward movement
     * @param rot the commanded rotation
     */
    public void arcadeDrive(double fwd, double rot) {
        m_drive.arcadeDrive(fwd, rot);
    }
    public void initDefaultCommand() {
        setDefaultCommand(new DriveSubsystem());
    }
}