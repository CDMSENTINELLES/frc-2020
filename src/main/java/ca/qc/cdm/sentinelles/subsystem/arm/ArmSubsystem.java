package ca.qc.cdm.sentinelles.subsystem.arm;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import static ca.qc.cdm.sentinelles.Constants.ArmConstants.BASE_ARM;

import java.util.Map;

public class ArmSubsystem extends SubsystemBase {
    private ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    public NetworkTableEntry maxSpeed = 
            tab.add("Max Speed Arm Base", 0.25)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();

    VictorSPX armBase = new VictorSPX(BASE_ARM);

    

    public void lowerArmUp () {
        double max = maxSpeed.getDouble(0.05);
        armBase.set(ControlMode.PercentOutput, max);
    }

    public void lowerArmDown () {
        double max = maxSpeed.getDouble(0.05);
        armBase.set(ControlMode.PercentOutput, max);
    }

    public void lowerArmStop () {
        armBase.set(ControlMode.PercentOutput, 0);
    }
}