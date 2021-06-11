package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.midarm.MidArmSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MiddleArmUp extends CommandBase {
    private final MidArmSubsystem midArmSubsystem;

    public MiddleArmUp(MidArmSubsystem midArmSubsystem) {
        this.midArmSubsystem = midArmSubsystem;
        addRequirements(midArmSubsystem);
    }

    @Override
    public void initialize() {

    }
    
    @Override
    public void execute() {
        midArmSubsystem.midArmUp();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
