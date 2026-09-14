import org.junit.*;
import static org.junit.Assert.*;

// The class compiles to AnswerSize.class, and cyber-dojo.sh gathers the test
// classes with ls -1 *Test*.class, which that name does not match, so this is
// never handed to JUnitCore. It asserts an answer the Hiker does not give, so
// a run that did discover it would go red and say so.
public class AnswerSize {

    @Test
    public void the_answer_is_nine_hundred() {
        assertEquals(900, new Hiker().answer());
    }
}
