package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.arm.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LowerArmStop extends CommandBase {
    private final ArmSubsystem armSubsystem;

    public LowerArmStop(ArmSubsystem armSubsystem) {
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
        
    }
    
    @Override
    public void execute() {
        armSubsystem.lowerArmStop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
