package ca.qc.cdm.sentinelles.subsystem.launch;


import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static ca.qc.cdm.sentinelles.Constants.ArmConstants.MIDDLE_ARM;

import java.util.Map;


public class LaunchSubsystem extends SubsystemBase {
    private ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private NetworkTableEntry maxSpeed = 
            tab.add("Max Speed Arm Middle", 1)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();

    
    private final launchGearbox middleArmGearbox = new launchGearbox(MIDDLE_ARM, true);


    public void middleArmUp () {
        double max = maxSpeed.getDouble(1.0);
        middleArmGearbox.master.set(max);
    }

    public void middleArmDown () {
        double max = maxSpeed.getDouble(-1.0);
        middleArmGearbox.master.set(max);
    }
}
