package ca.qc.cdm.sentinelles;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
    private static final int kMotorPort = 0;
    private static final int kJoystickPort = 0;

    private SpeedController m_motor;
    private Joystick m_joystick;

    @Override
    public void robotInit() {
        new WPI_VictorSPX(0);
//        m_motor = new PWMVictorSPX(kMotorPort);
        m_joystick = new Joystick(kJoystickPort);
    }

    @Override
    public void teleopPeriodic() {
        m_motor.set(m_joystick.getY());
    }
}
