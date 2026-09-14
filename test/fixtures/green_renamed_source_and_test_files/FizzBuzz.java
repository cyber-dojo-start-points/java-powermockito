public class FizzBuzz {

    public String say(int n) {
        DiskLogger.getInstance().log("Saying the word for " + n);
        return "Fizz";
    }
}

// The singleton the FizzBuzz reaches through a static method, which is what
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
