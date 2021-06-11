package ca.qc.cdm.sentinelles.command;


import ca.qc.cdm.sentinelles.subsystem.midarm.MidArmSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MiddleArmStop extends CommandBase {
    private final MidArmSubsystem m_midArmSubsystem;

    public MiddleArmStop(MidArmSubsystem midArmSubsystem) {
        m_midArmSubsystem = midArmSubsystem;
        addRequirements(m_midArmSubsystem);
    }

    @Override
    public void initialize() {
        
    }
    
    @Override
    public void execute() {
        m_midArmSubsystem.midArmStop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
