import org.junit.*;
import static org.junit.Assert.*;

// The name would be discovered by cyber-dojo.sh's ls -1 *Test*.class, but
// javac stops on the missing semicolon below and no .class file is ever
// written, so discovery is never reached.
public class AnswerSizeTest {

    @Test
    public void the_answer_is_two_digits_long() {
        assertEquals(2, String.valueOf(42).length())
    }
}
