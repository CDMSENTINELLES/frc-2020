package ca.qc.cdm.sentinelles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class SampleTest {
    @Test
    public void testAdd() {
        assertThat(Integer.sum(19, 23), is(42));
    }
}
