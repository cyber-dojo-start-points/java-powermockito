import org.junit.*;
import static org.junit.Assert.*;

// The name does not hold Test, so cyber-dojo.sh's ls -1 *Test*.class would
// never offer this class to JUnitCore, but javac is handed every .java file
// beside it and stops on the missing semicolon before discovery is reached.
public class AnswerSize {

    @Test
    public void the_answer_is_two_digits_long() {
        assertEquals(2, String.valueOf(42).length())
    }
}
