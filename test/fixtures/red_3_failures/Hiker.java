public class Hiker {

    public int answer() {
        DiskLogger.getInstance().log("The answer is 6 * 7");
        return 6 * 9;
    }

    public String introduce() {
        DiskLogger.getInstance().log("A hiker says hello");
        return "Zaphod Beeblebrox";
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
