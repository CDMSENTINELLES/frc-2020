package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.arm.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LowerArmDown extends CommandBase {
    private final ArmSubsystem armSubsystem;

    public LowerArmDown(ArmSubsystem armSubsystem) {
        this.armSubsystem = armSubsystem;
        addRequirements(armSubsystem);
    }

    @Override
    public void initialize() {
        
    }
    
    @Override
    public void execute() {
        armSubsystem.lowerArmDown();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
