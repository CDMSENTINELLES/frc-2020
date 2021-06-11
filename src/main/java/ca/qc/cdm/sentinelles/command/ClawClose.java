package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.claw.ClawSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawClose extends CommandBase {
    private final ClawSubsystem clawSubsystem;

    public ClawClose(ClawSubsystem clawSubsystem) {
        this.clawSubsystem = clawSubsystem;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {

    }
    
    @Override
    public void execute() {
        clawSubsystem.clawClose();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
