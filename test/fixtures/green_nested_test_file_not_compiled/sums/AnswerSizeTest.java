import org.junit.*;
import static org.junit.Assert.*;

// cyber-dojo.sh hands javac *.java, which names only the files beside it, so
// nothing in this directory is ever compiled and the test below never runs.
// It asserts an answer the Hiker does not give, so a run that did compile it
// would go red and say so.
public class AnswerSizeTest {

    @Test
    public void the_answer_is_nine_hundred() {
        assertEquals(900, new Hiker().answer());
    }
}
