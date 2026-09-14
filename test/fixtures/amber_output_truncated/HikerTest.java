import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.mockito.Mockito.verify;

// The runner keeps the first 50K of each stream and drops the rest. JUnitCore
// prints its "OK (1 test)" summary to stdout after the test body has run, so
// the Hiker flooding stdout is what pushes that line past the cut and leaves
// the lambda with nothing to read.
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest(DiskLogger.class)
public class HikerTest {

    @Test
    public void life_the_universe_and_everything() {
        DiskLogger logger = mock(DiskLogger.class);
        mockStatic(DiskLogger.class);
        when(DiskLogger.getInstance()).thenReturn(logger);

        assertEquals(42, new Hiker().answer());
        verify(logger).log("The answer is 6 * 7");
    }
}
