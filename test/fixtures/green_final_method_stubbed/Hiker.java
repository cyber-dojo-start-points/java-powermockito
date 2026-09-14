public class Hiker {

    public String answer() {
        return "42 is written to " + DiskLogger.getInstance().filename();
    }
}

// filename() is final, so a plain Mockito mock could not replace it. PowerMock
// rewrites the class named in @PrepareForTest, which is what lets the test
// stub a final method at all. The body throws so that a run which did not
// stub it would say so instead of quietly passing.
class DiskLogger {

    public static DiskLogger getInstance() {
        return singleton;
    }

    public final String filename() {
        throw new UnsupportedOperationException("the real DiskLogger writes to disk");
    }

    private DiskLogger() {
    }

    private static final DiskLogger singleton = new DiskLogger();
}
