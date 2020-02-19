package ca.qc.cdm.sentinelles.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ca.qc.cdm.sentinelles.command.JoystickDrive.calibrateSlider;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Joystick Drive Test Command")
public class JoystickDriveTest {
    @Test
    @DisplayName("Test Slider Calibration")
    public void calibration() {
        assertThat(calibrateSlider(-1), is(0.0));
        assertThat(calibrateSlider(-0.5), is(0.25));
        assertThat(calibrateSlider(0), is(0.5));
        assertThat(calibrateSlider(0.5), is(0.75));
        assertThat(calibrateSlider(1), is(1.0));
    }
}
