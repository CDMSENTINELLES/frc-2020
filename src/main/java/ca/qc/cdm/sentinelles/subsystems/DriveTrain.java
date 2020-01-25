package ca.qc.cdm.sentinelles.subsystems;

import ca.qc.cdm.sentinelles.Prefs;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;

public class DriveTrain extends SubsystemBase {
    private Prefs prefs = Prefs.getPrefs();

    /**
     * Creates a new DriveSubsystem.
     */
    public DriveTrain() {
        super();
    }

    public void drive(double move, double turn) {
        drive.arcadeDrive(move, turn);
    }
}