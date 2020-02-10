package ca.qc.cdm.sentinelles;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SampleTest {
    @Test
    public void testAdd() {
        assertThat(Integer.sum(19, 23), is(42));
    }
}
