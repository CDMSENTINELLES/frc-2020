package ca.qc.cdm.sentinelles.command;

import ca.qc.cdm.sentinelles.subsystem.launch.LaunchSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MiddleArmDown extends CommandBase {
    private final LaunchSubsystem launchSubsystem;

    public MiddleArmDown(LaunchSubsystem launchSubsystem) {
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }

    @Override
    public void initialize() {

    }
    
    @Override
    public void execute() {
        launchSubsystem.middleArmDown();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
