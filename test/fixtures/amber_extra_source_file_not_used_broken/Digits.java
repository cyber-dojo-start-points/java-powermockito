// Nothing references this class, but cyber-dojo.sh hands javac *.java, so the
// missing semicolon below stops the whole compile and no test ever runs.
public class Digits {

    public static int count(int n) {
        return String.valueOf(n).length()
    }
}
