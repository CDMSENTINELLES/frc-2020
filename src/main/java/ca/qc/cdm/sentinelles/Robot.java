package ca.qc.cdm.sentinelles;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import static ca.qc.cdm.sentinelles.Constants.DriveConstants.*;

public class Robot extends TimedRobot {
    private RobotContainer robotContainer;
    private Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {

        robotContainer = new RobotContainer();

        // Drive Init

        frontLeftDrive.configFactoryDefault();
        frontRightDrive.configFactoryDefault();
        backLeftDrive.configFactoryDefault();
        backRightDrive.configFactoryDefault();

        frontLeftDrive.setInverted(false);
        frontRightDrive.setInverted(true);
        backLeftDrive.setInverted(false);
        backRightDrive.setInverted(true);

        drive.setRightSideInverted(false);

        backLeftDrive.follow(frontLeftDrive);
        backRightDrive.follow(backRightDrive);

        frontLeftDrive.configVoltageCompSaturation(11.5, kTimeoutMs);
        frontRightDrive.configVoltageCompSaturation(11.5, kTimeoutMs);
        backLeftDrive.configVoltageCompSaturation(11.5, kTimeoutMs);
        backRightDrive.configVoltageCompSaturation(11.5, kTimeoutMs);

        frontRightDrive.enableVoltageCompensation(true);
        frontLeftDrive.enableVoltageCompensation(true);
        backRightDrive.enableVoltageCompensation(true);
        backLeftDrive.enableVoltageCompensation(true);

        frontRightDrive.configVoltageMeasurementFilter(32, kTimeoutMs);
        frontLeftDrive.configVoltageMeasurementFilter(32, kTimeoutMs);
        backLeftDrive.configVoltageMeasurementFilter(32, kTimeoutMs);
        backRightDrive.configVoltageMeasurementFilter(32, kTimeoutMs);

        frontLeftDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, kTimeoutMs);
        frontRightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, kTimeoutMs);
        backRightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, kTimeoutMs);
        backLeftDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, kTimeoutMs);

        frontLeftDrive.configOpenloopRamp(3);
        frontRightDrive.configOpenloopRamp(3);

        frontRightDrive.configClosedloopRamp(0);
        frontLeftDrive.configClosedloopRamp(0);

        backLeftDrive.follow(frontLeftDrive);
        backRightDrive.follow(frontRightDrive);

    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
        autonomousCommand = robotContainer.getAutonomousCommand();
        autonomousCommand.schedule();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
    }
}
