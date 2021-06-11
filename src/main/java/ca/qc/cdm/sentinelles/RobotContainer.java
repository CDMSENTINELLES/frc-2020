package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.command.ArmControl;
import ca.qc.cdm.sentinelles.command.ClawClose;
import ca.qc.cdm.sentinelles.command.ClawOpen;
import ca.qc.cdm.sentinelles.command.JoystickDrive;
import ca.qc.cdm.sentinelles.command.LowerArmDown;
import ca.qc.cdm.sentinelles.command.LowerArmUp;
import ca.qc.cdm.sentinelles.command.MiddleArmDown;
import ca.qc.cdm.sentinelles.command.MiddleArmUp;
import ca.qc.cdm.sentinelles.command.NullCommand;
import ca.qc.cdm.sentinelles.subsystem.arm.ArmSubsystem;
import ca.qc.cdm.sentinelles.subsystem.claw.ClawSubsystem;
import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import ca.qc.cdm.sentinelles.subsystem.launch.LaunchSubsystem;
import ca.qc.cdm.sentinelles.Constants.JoystickConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;



public class RobotContainer {

    // Subsystems
    private DriveSubsystem driveTrain;

    private ClawSubsystem clawSubsystem;

    private LaunchSubsystem launchSubsystem;
    
    private ArmSubsystem armSubsystem;

    private final Joystick joystickSub = new Joystick(JoystickConstants.JOYSTICK_SUB_PORT);

    private final CommandBase clawOpen = new ClawOpen(clawSubsystem);
    private final CommandBase clawClose = new ClawClose(clawSubsystem);

    private final CommandBase middleArmDown = new MiddleArmDown(launchSubsystem);
    private final CommandBase middleArmUp = new MiddleArmUp(launchSubsystem);
    
    private final CommandBase lowerArmDown = new LowerArmDown(armSubsystem);
    private final CommandBase lowerArmUp = new LowerArmUp(armSubsystem);
    
  
   
    

    // Commands
    private final Command autoCommand = new NullCommand();

    public RobotContainer() {
        initSubsystem();
        buttonBindings();


        CommandScheduler.getInstance()
            .onCommandInitialize(
                command -> 
                    Shuffleboard.addEventMarker(
                        "Command init.", command.getName(), EventImportance.kNormal));
        
        CommandScheduler.getInstance()
            .onCommandInterrupt(
                command -> 
                    Shuffleboard.addEventMarker(
                        "Command inter.", command.getName(), EventImportance.kNormal));
    
        CommandScheduler.getInstance()
            .onCommandFinish(
                command -> 
                    Shuffleboard.addEventMarker(
                        "Command complete", command.getName(), EventImportance.kNormal));
    

        driveTrain.setDefaultCommand(new JoystickDrive(driveTrain));
        //launchSubsystem.setDefaultCommand(new ArmControl(launchSubsystem, armSubsystem, clawSubsystem));
    }

    private void initSubsystem() {
        driveTrain = new DriveSubsystem();
        launchSubsystem = new LaunchSubsystem();
        clawSubsystem = new ClawSubsystem();
        armSubsystem = new ArmSubsystem();
    }

    private void buttonBindings() {
        new JoystickButton(joystickSub, 7).whenHeld(clawOpen);
        new JoystickButton(joystickSub, 8).whenHeld(clawClose);

        new JoystickButton(joystickSub, 9).whenHeld(middleArmDown);
        new JoystickButton(joystickSub, 10).whenHeld(middleArmUp);

        new JoystickButton(joystickSub, 11).whenHeld(lowerArmDown);
        new JoystickButton(joystickSub, 12).whenHeld(lowerArmUp);

    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
