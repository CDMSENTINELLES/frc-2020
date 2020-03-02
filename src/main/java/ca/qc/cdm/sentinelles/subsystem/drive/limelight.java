package ca.qc.cdm.sentinelles.subsystem.drive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class limelight extends SubsystemBase {

    public static final double TEST_TARGET_INDEX = 0;
    public static final double LAUNCH_TARGET_INDEX = 1;

    NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry tv;
    NetworkTableEntry ta;

    NetworkTableEntry pipelineIndex;


    public limelight() {
        super();
        table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        tv = table.getEntry("tv");
        ta = table.getEntry("ta");

    }

    public void turnOn() {

    }

    public void turnOff() {

    }

    public void blink() {

    }

    public void setPipelineIndex(double index) {
        pipelineIndex.setDouble(index);
    }

    public boolean hasTarget() {
        return tv.getDouble(0.0) == 1;
    }

    public double getTrackingX() {
        return tx.getDouble(0.0);
    }

    public double getTrackingY() {
        return ty.getDouble(0.0);
    }

    public double getTrackingArea() {
        return ta.getDouble(0.0);

    }
}
