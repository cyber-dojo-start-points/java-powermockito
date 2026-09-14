public class Hiker {

    public int answer() {
        // The printing sits here rather than in the test class because a
        // System.out call inside a @PrepareForTest class is routed through
        // PowerMock's MockGateway, which reflects over PrintStream and is
        // refused by java.base.
        for (int i = 0; i != 20000; i++) {
            System.out.println("debug: i is " + i);
        }
        DiskLogger.getInstance().log("The answer is 6 * 7");
        return 6 * 7;
    }
}

// The singleton the Hiker reaches through a static method, which is what
// PowerMock is here to stub. Its log() throws so that a test which failed to
// stub the static would say so instead of quietly passing.
final class DiskLogger {

    public static DiskLogger getInstance() {
        return singleton;
    }

    public void log(String line) {
        throw new UnsupportedOperationException("the real DiskLogger writes to disk");
    }

    private DiskLogger() {
    }

    private static final DiskLogger singleton = new DiskLogger();
}
