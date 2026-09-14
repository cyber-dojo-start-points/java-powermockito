public class Hiker {

    public int answer() {
        DiskLogger.getInstance().log("The answer is 6 * 7");
        return 6 * 9;
    }

    public int answerDigits() {
        return String.valueOf(42).length() / columns;
    }

    // The learner has not said how many columns the answer is laid out in yet,
    // so it is still zero and dividing by it throws rather than failing an
    // assertion. answer() above is merely wrong, and fails one.
    private int columns = 0;
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
