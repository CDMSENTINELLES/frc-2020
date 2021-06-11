package ca.qc.cdm.sentinelles.subsystem.midarm;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import static ca.qc.cdm.sentinelles.Constants.ArmConstants;

import java.util.Map;

public class MidArmSubsystem extends SubsystemBase {
    private ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    public NetworkTableEntry maxSpeed = 
            tab.add("Max Speed Arm Mid", 0.05)
                .withWidget(BuiltInWidgets.kSpeedController)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();

    private final CANSparkMax armMid = new CANSparkMax(ArmConstants.MIDDLE_ARM, CANSparkMaxLowLevel.MotorType.kBrushless);
    public void midArmUp () {
        double max = maxSpeed.getDouble(0.05);
        armMid.set(max);
    }

    public void midArmDown () {
        double max = maxSpeed.getDouble(0.05);
        armMid.set(-max);
    }

    public void midArmStop () {
        armMid.set(0);
    }
}