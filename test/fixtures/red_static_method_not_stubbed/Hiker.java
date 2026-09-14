public class Hiker {

    public int answer() {
        DiskLogger.getInstance().log("The answer is 6 * 7");
        return 6 * 7;
    }
}

// The singleton the Hiker reaches through a static method. The test does not
// stub that static, so the real getInstance() hands back the real singleton
// and the real log() below is what runs.
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
