package ca.qc.cdm.sentinelles;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;


import java.util.Map;

public class Dashboard {

    private final NetworkTableEntry speedEntry;

    public Dashboard() {
        ShuffleboardTab driveTab = Shuffleboard.getTab("Drivetrain");
        speedEntry = driveTab.add("Speed", 0)
                .withWidget(BuiltInWidgets.kGraph)
                .withProperties(Map.of("Min", 0, "Max", 100))
                .withPosition(0,0)
                .withSize(3,3)
                .getEntry();
    }

    public NetworkTableEntry speedEntry() {
        return speedEntry;
    }
}
