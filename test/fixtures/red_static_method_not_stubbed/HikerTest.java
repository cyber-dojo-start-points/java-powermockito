import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import static org.powermock.api.mockito.PowerMockito.*;

// DiskLogger is named in @PrepareForTest, so PowerMock has rewritten it, but
// the test never calls mockStatic. Nothing stands in for the static
// getInstance(), so the Hiker reaches the real singleton and its log() throws.
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest(DiskLogger.class)
public class HikerTest {

    @Test
    public void life_the_universe_and_everything() {
        assertEquals(42, new Hiker().answer());
    }
}
