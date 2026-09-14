import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest(DiskLogger.class)
public class HikerTest {

    private DiskLogger logger;

    @Before
    public void stub_the_static_getInstance() {
        logger = mock(DiskLogger.class);
        mockStatic(DiskLogger.class);
        when(DiskLogger.getInstance()).thenReturn(logger);
    }

    @Test
    public void life_the_universe_and_everything() {
        assertEquals(42, new Hiker().answer());
    }

    @Test
    public void the_answer_is_two_digits_long() {
        assertEquals(2, new Hiker().answerDigits());
    }
}
