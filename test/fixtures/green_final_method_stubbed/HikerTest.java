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
public class HikerTest {

    @Test
    public void the_answer_goes_to_the_named_file() {
        DiskLogger logger = mock(DiskLogger.class);
        mockStatic(DiskLogger.class);
        when(DiskLogger.getInstance()).thenReturn(logger);
        when(logger.filename()).thenReturn("hiker.log");

        assertEquals("42 is written to hiker.log", new Hiker().answer());
    }
}
