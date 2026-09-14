import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest(DiskLogger.class)
public class AnswerSizeTest {

    @Test
    public void the_answer_is_three_digits_long() {
        DiskLogger logger = mock(DiskLogger.class);
        mockStatic(DiskLogger.class);
        when(DiskLogger.getInstance()).thenReturn(logger);

        assertEquals(3, String.valueOf(new Hiker().answer()).length());
    }
}
