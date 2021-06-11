package ca.qc.cdm.sentinelles.subsystem.claw;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import static ca.qc.cdm.sentinelles.Constants.ArmConstants.CLAW_MASTER;
import static ca.qc.cdm.sentinelles.Constants.ArmConstants.CLAW_SLAVE;

import java.util.Map;

public class ClawSubsystem extends SubsystemBase {

    VictorSPX clawMaster = new VictorSPX(CLAW_MASTER);
    VictorSPX clawSlave = new VictorSPX(CLAW_SLAVE);

    private ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private NetworkTableEntry maxSpeed = 
            tab.add("Max Speed Claw", 0.05)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();

    public void init() {
        clawSlave.follow(clawMaster);
        clawSlave.setInverted(InvertType.OpposeMaster);
    }

    public void clawOpen () {
        double max = maxSpeed.getDouble(0.05);
        clawMaster.set(ControlMode.PercentOutput, max);
    }

    public void clawClose () {
        double max = maxSpeed.getDouble(0.05);
        clawMaster.set(ControlMode.PercentOutput, max);
    }

    public void clawStop () {
        clawMaster.set(ControlMode.PercentOutput, 0);
    }
}