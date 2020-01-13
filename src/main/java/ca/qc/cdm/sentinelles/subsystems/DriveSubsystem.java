package ca.qc.cdm.sentinelles.subsystems;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;
import static com.ctre.phoenix.motorcontrol.InvertType.FollowMaster;

public class DriveSubsystem extends SubsystemBase {
    // The motors on the left side of the drive.
    private VictorSPX leftMotor1 = new VictorSPX(0);
    private VictorSPX leftMotor2 = new VictorSPX(1);
    private VictorSPX rightMotor1 = new VictorSPX(2);
    private VictorSPX rightMotor2 = new VictorSPX(2);

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveSubsystem() {
        // Make M2 follow exactly what M1 does
        leftMotor2.follow(leftMotor1);
        leftMotor1.setInverted(false);
        leftMotor2.setInverted(FollowMaster);

        // Make M4 follow exactly what M3 does
        rightMotor2.follow(rightMotor1);
        rightMotor1.setInverted(false);
        rightMotor2.setInverted(FollowMaster);
    }

    /**
     * Drives the robot using arcade controls.
     *
     * @param fwd the commanded forward movement
     * @param rot the commanded rotation
     */
    public void arcadeDrive(double fwd, double rot) {
//        m_drive.arcadeDrive(fwd, rot);
    }
}