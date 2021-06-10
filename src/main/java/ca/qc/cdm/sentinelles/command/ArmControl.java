package ca.qc.cdm.sentinelles.command;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static ca.qc.cdm.sentinelles.Constants.JoystickConstants.JOYSTICK_PORT;

import ca.qc.cdm.sentinelles.subsystem.arm.ArmSubsystem;
import ca.qc.cdm.sentinelles.subsystem.claw.ClawSubsystem;
import ca.qc.cdm.sentinelles.subsystem.launch.LaunchSubsystem;

public class ArmControl extends CommandBase {
    private final LaunchSubsystem launchSubsystem;
    private final ArmSubsystem armSubsystem;
    private final ClawSubsystem clawSubsystem;
    private final Joystick joystick;

    

    

    public ArmControl(LaunchSubsystem launchSubsystem, ArmSubsystem armSubsystem, ClawSubsystem clawSubsystem) {
        this.launchSubsystem = launchSubsystem;
        this.armSubsystem = armSubsystem;
        this.clawSubsystem = clawSubsystem;
        this.joystick = new Joystick(JOYSTICK_SUB_PORT);
        addRequirements(launchSubsystem);
        addRequirements(armSubsystem);
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize(){
        System.out.println("Init Sub Joystick Arm Command.");
        
    }

    @Override
    public void execute() {
        if (joystick.getRawButton(11)) {
            armSubsystem.lowerArmUp();
        }
        else if (joystick.getRawButton(12)){
            armSubsystem.lowerArmDown();
        }
        else if (joystick.getRawButton(9)){
            launchSubsystem.middleArmUp();
        }
        else if (joystick.getRawButton(10)){
            launchSubsystem.middleArmDown();
        }
        else if (joystick.getRawButton(7)){
            clawSubsystem.clawOpen();
        }
        else if (joystick.getRawButton(8)){
            clawSubsystem.clawClose();
        }
    }
}