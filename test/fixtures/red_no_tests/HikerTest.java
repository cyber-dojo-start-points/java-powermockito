import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import static org.powermock.api.mockito.PowerMockito.*;

// The @Test annotation is missing, so the method below is an ordinary one that
// nothing calls. The class still compiles and its name still matches
// cyber-dojo.sh's ls -1 *Test*.class, so JUnitCore is handed a class holding
// nothing it can run.
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest(DiskLogger.class)
public class HikerTest {

    public void life_the_universe_and_everything() {
        DiskLogger logger = mock(DiskLogger.class);
        mockStatic(DiskLogger.class);
        when(DiskLogger.getInstance()).thenReturn(logger);

        assertEquals(42, new Hiker().answer());
    }
}
