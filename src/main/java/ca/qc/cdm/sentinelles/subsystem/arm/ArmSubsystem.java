package ca.qc.cdm.sentinelles.subsystem.arm;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.pheonix.motorcontrol.ControlMode;
import com.ctre.pheonix.motorcontrol.can.VictorSPX;

import static ca.qc.cdm.sentinelles.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {
    private ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private NetworkTableEntry maxSpeed = 
            tab.add("Max Speed Arm Base", 1)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();

    VictorSPX armBase = new VictorSPX(BASE_ARM);

    public void lowerArmUp (){
        armBase.set(ControlMode.PercentOutput, maxSpeed);
    }

    public void lowerArmDown () {
        armBase.set(ControlMode.PercentOutput, -maxSpeed);
    }
}