package ca.qc.cdm.sentinelles.subsystem.claw;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.pheonix.motorcontrol.ControlMode;
import com.ctre.pheonix.motorcontrol.can.VictorSPX;

import static ca.qc.cdm.sentinelles.Constants.ArmConstants;

public class ClawSubsystem extends SubsystemBase {

    VictorSPX clawMaster = new VictorSPX(CLAW_MASTER);
    VictorSPX clawSlave = new VictorSPX(CLAW_SLAVE);

    private ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private NetworkTableEntry maxSpeed = 
            tab.add("Max Speed Claw", 1)
                .withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1))
                .getEntry();

    private void init() {
        
        clawSlave.follow(clawMaster);
        clawSlave.setInverted(InvertType.OpposeMaster);
    }

    public void clawOpen () {
        clawMaster.set(ControlMode.PercentOutput, maxSpeed);
    }

    public void clawClose () {
        clawMaster.set(ControlMode.PercentOutput, -maxSpeed);
    }
}