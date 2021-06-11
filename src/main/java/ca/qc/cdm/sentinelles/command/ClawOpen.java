package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.claw.ClawSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClawOpen extends CommandBase {
    private final ClawSubsystem clawSubsystem;

    public ClawOpen(ClawSubsystem clawSubsystem) {
        this.clawSubsystem = clawSubsystem;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        clawSubsystem.init();
    }
    
    @Override
    public void execute() {
        clawSubsystem.clawOpen();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
