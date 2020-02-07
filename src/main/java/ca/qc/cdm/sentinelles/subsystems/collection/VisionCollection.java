package ca.qc.cdm.sentinelles.subsystems.collection;

import ca.qc.cdm.sentinelles.Constants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionCollection {

    private double degreesToRotate = 0.0;
    private boolean validTarget = false;
    private double defaultVal = 0;

    private double tx = 0;
    private double ty = 0;
    private double ta = 0;

    NetworkTable limelightTable;

    public VisionCollection() {
        super();
        init();
    }

    public void init() {
        NetworkTableInstance tableInstance = NetworkTableInstance.getDefault();
        tableInstance.startClientTeam(7574);
        limelightTable = tableInstance.getTable("limelight");
    }

    public void diagnosticsInit() {

    }

    public void diagnosticsPeriodic() {

    }

    public void diagnosticsCheck() {

    }

    public void visionCollect() {

    }

    public double seekTarget(double kP) {
        double tv = queryLimelightNetworkTable("tv");
        double tx = queryLimelightNetworkTable("tx");
        double steeringAdjust = 0.0;
        if (tv == 0.0) {
            steeringAdjust = 0.3;
        }
        else {
            double headingError = tx;
            steeringAdjust = kP * tx;
        }

        return steeringAdjust;
    }

    public double approximateDistanceFromTarget(double ty) {
        return (Constants.VisionConstants.targetHeightInchCollect - Constants.VisionConstants.cameraHeightInch) / Math.tan(0 + ty);
    }

    public double aimRangeTarget(double kP_Aim, double kP_Distance, double minAim) {
        double kP_Aim = -0.1;
        double kP_Distance = -0.1;
        double minAim = 0.05;
        double tx = queryLimelightNetworkTable("tx");
        double ty = queryLimelightNetworkTable("ty");
        double headingError = -tx;
        double distanceError = -ty;
        double steeringAdjust = 0.0;

        if (tx > 1.0) {
            steeringAdjust = kP_Aim * headingError - minAim;
        }
        else if (tx < 1.0) {
            steeringAdjust = kP_Aim * headingError + minAim;
        }

        double distanceAdjust = kP_Distance + distanceError;

        return steeringAdjust + distanceAdjust;
    }

    public double queryLimelightNetworkTable(String value) {
        return limelightTable.getEntry(value).getDouble(defaultVal);
    }

    public double updateTargetInfo() {
        double tv = queryLimelightNetworkTable("tv");
        if (tv == 1) {
            validTarget = true;
        }
        else {
            validTarget = false;
        }
        tx = queryLimelightNetworkTable("tx");
        ty = queryLimelightNetworkTable("ty");
    }

    public double getDegreesToRotate() {
        return degreesToRotate;
    }

    public double getTx() {
        return tx;
    }

    public boolean getValidTarget() {
        return validTarget;
    }
}
