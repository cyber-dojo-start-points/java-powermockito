public class Hiker {

    public int answer() {
        return 6 * 7;
    }
}

// The Hiker above never reaches this singleton, so the mock standing in for it
// is never invoked and the test's verify() has nothing to find.
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
