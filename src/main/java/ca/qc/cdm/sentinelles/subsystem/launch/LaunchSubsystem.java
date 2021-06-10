package ca.qc.cdm.sentinelles.subsystem.launch;

import static ca.qc.cdm.sentinelles.Constants.ArmConstants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class LaunchSubsystem extends SubsystemBase {
    private ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private NetworkTableEntry maxSpeed = 
            tab.add("Max Speed Arm Middle", 1)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();

    
    private final LaunchGearbox middleArmGearbox = new launchGearbox(MIDDLE_ARM, true);


    public void middleArmUp () {
        middleArmGearbox.master.set(maxSpeed);
    }

    public void middleArmDown () {
        middleArmGearbox.master.set(-maxSpeed);
    }
}
