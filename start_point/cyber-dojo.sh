
CLASSES=.:`ls /powermockito/*.jar | tr '\n' ':'`
javac -Xlint:unchecked -cp $CLASSES  *.java 

if [ $? -eq 0 ]; then
  # run test classes even if they are inner classes
  java --add-opens java.base/java.lang=ALL-UNNAMED \
      --add-opens java.base/jdk.internal.loader=ALL-UNNAMED \
      --add-opens java.base/java.security=ALL-UNNAMED \
      -cp $CLASSES org.junit.runner.JUnitCore \
      `ls -1 *Test*.class | grep -v '\\$' | sed 's/\(.*\)\..*/\1/'`
fi
