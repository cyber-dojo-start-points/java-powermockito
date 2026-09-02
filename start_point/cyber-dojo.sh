
# The jars come first and the sandbox directory last. An AOT cache is validated
# against the classpath of the JVM reading it, and validates when what it was
# recorded against is a prefix of that; the caches in this image were recorded
# against the jars alone, so anything of yours has to follow them.
CLASSES=`ls /powermockito/*.jar | tr '\n' ':'`.

# Each [test] press starts two JVMs, one to compile and one to run the tests,
# and starting them is most of the wait rather than a part of it. The image holds
# an AOT cache for each, recorded when it was built, holding the classes that JVM
# loads in the form the JVM wants them; reading one back costs a fraction of
# loading them again. There is one cache per JVM because a cache is validated
# against the classpath of the JVM reading it, and these two have nothing in
# common. Nothing is lost when a cache is missing or unusable: the JVM says so
# and loads the classes itself, and the run is only slower.
#
# Your own classes are in neither cache, so editing them cannot invalidate one.
# javac's options are spelled -J here because that is how javac passes an option
# to the JVM running it rather than to the compiler.
#
# The collector is named rather than left to the JVM to choose. Replaying an AOT
# cache under the collector it picks by default crashes it outright, often enough
# to see in a handful of runs, and a JVM that lives for a fraction of a second
# has nothing to gain from a concurrent collector in any case.
JAVAC_JVM_OPTS=()
JAVAC_JVM_OPTS+=(-J-XX:TieredStopAtLevel=1)      # a run is milliseconds; later tiers never repay
JAVAC_JVM_OPTS+=(-J-XX:+UseSerialGC)             # see above
JAVAC_JVM_OPTS+=(-J-XX:AOTCache=/aot/javac.aot)  # the compiler's own classes

TEST_JVM_OPTS=()
TEST_JVM_OPTS+=(-XX:TieredStopAtLevel=1)             # as above, for the JVM running the tests
TEST_JVM_OPTS+=(-XX:+UseSerialGC)                    # as above
TEST_JVM_OPTS+=(-XX:AOTCache=/aot/junit-runner.aot)  # PowerMock's classes, Mockito's and JUnit's

# PowerMock rewrites classes to make their statics mockable, and reaches through
# sun.misc.Unsafe and into java.base to do it. The JDK still permits both, and
# says so on every run; the message is about PowerMock rather than about anything
# you wrote, so it is turned off here rather than left to puzzle over.
TEST_JVM_OPTS+=(--sun-misc-unsafe-memory-access=allow)
TEST_JVM_OPTS+=(--add-opens java.base/java.lang=ALL-UNNAMED)
TEST_JVM_OPTS+=(--add-opens java.base/jdk.internal.loader=ALL-UNNAMED)
TEST_JVM_OPTS+=(--add-opens java.base/java.security=ALL-UNNAMED)

javac "${JAVAC_JVM_OPTS[@]}" -Xlint:unchecked -cp $CLASSES *.java

if [ $? -eq 0 ]; then
  # run test classes even if they are inner classes
  java "${TEST_JVM_OPTS[@]}" \
      -cp $CLASSES org.junit.runner.JUnitCore \
      `ls -1 *Test*.class | grep -v '\\$' | sed 's/\(.*\)\..*/\1/'`
fi
