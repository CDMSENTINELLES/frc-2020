package ca.qc.cdm.sentinelles;

import ca.qc.cdm.sentinelles.command.ClawClose;
import ca.qc.cdm.sentinelles.command.ClawOpen;
import ca.qc.cdm.sentinelles.command.JoystickDrive;
import ca.qc.cdm.sentinelles.command.LowerArmDown;
import ca.qc.cdm.sentinelles.command.LowerArmStop;
import ca.qc.cdm.sentinelles.command.LowerArmUp;
import ca.qc.cdm.sentinelles.command.MiddleArmDown;
import ca.qc.cdm.sentinelles.command.MiddleArmStop;
import ca.qc.cdm.sentinelles.command.MiddleArmUp;
import ca.qc.cdm.sentinelles.command.NullCommand;
import ca.qc.cdm.sentinelles.subsystem.arm.ArmSubsystem;
import ca.qc.cdm.sentinelles.subsystem.claw.ClawSubsystem;
import ca.qc.cdm.sentinelles.subsystem.drive.DriveSubsystem;
import ca.qc.cdm.sentinelles.subsystem.launch.LaunchSubsystem;
import ca.qc.cdm.sentinelles.subsystem.midarm.MidArmSubsystem;
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
    private DriveSubsystem driveTrain = new DriveSubsystem();

    private ClawSubsystem clawSubsystem = new ClawSubsystem();

    private LaunchSubsystem launchSubsystem;
    
    private final ArmSubsystem armSubsystem = new ArmSubsystem();

    private final MidArmSubsystem midArmSubsystem = new MidArmSubsystem();

    private final CommandBase joystickDrive = new JoystickDrive(driveTrain);

    private final Joystick joystickSub = new Joystick(JoystickConstants.JOYSTICK_SUB_PORT);

    private final CommandBase clawOpen = new ClawOpen(clawSubsystem);
    private final CommandBase clawClose = new ClawClose(clawSubsystem);

    private final CommandBase middleArmDown = new MiddleArmDown(midArmSubsystem);
    private final CommandBase middleArmUp = new MiddleArmUp(midArmSubsystem);
    
    private final CommandBase lowerArmDown = new LowerArmDown(armSubsystem);
    private final CommandBase lowerArmUp = new LowerArmUp(armSubsystem);

    // Commands
    private final Command autoCommand = new NullCommand();

    public RobotContainer() {
        buttonBindings();
        driveTrain.setDefaultCommand(joystickDrive);
        //initSubsystem();
        /*clawOpen.setName("Open Claw");
        clawClose.setName("Close Claw");

        middleArmDown.setName("Middle Arm (NEO) down");
        middleArmUp.setName("Middle Arm (NEO) up");
        
        lowerArmDown.setName("Lower Arm (VICTOR) down");
        lowerArmUp.setName("Lower Arm (Victor) up");


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
*/
        
        //launchSubsystem.setDefaultCommand(new ArmControl(launchSubsystem, armSubsystem, clawSubsystem));
       
    }

    /*public void initSubsystem() {
        driveTrain = new DriveSubsystem();
        //launchSubsystem = new LaunchSubsystem();
        //clawSubsystem = new ClawSubsystem();
        armSubsystem = new ArmSubsystem();
        midArmSubsystem = new MidArmSubsystem();
    }*/

    private void buttonBindings() {
        new JoystickButton(joystickSub, 7).whenHeld(clawOpen);
        new JoystickButton(joystickSub, 8).whenHeld(clawClose);

        new JoystickButton(joystickSub, 9).whenHeld(new MiddleArmDown(midArmSubsystem));
        new JoystickButton(joystickSub, 9).whenInactive(new MiddleArmStop(midArmSubsystem));
        new JoystickButton(joystickSub, 10).whenHeld(new MiddleArmUp(midArmSubsystem));
        new JoystickButton(joystickSub, 10).whenInactive(new MiddleArmStop(midArmSubsystem));

        new JoystickButton(joystickSub, 11).whenHeld(new LowerArmDown(armSubsystem));
        new JoystickButton(joystickSub, 11).whenInactive(new LowerArmStop(armSubsystem));
        new JoystickButton(joystickSub, 12).whenHeld(new LowerArmUp(armSubsystem));
        new JoystickButton(joystickSub, 12).whenInactive(new LowerArmStop(armSubsystem));

    }

    public Command getAutonomousCommand() {
        return autoCommand;
    }
}
