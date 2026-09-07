package testloop_runner;

import org.testng.TestNG;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoopTestRunner {

    // interval between executions (1 hour)
    private static final Duration INTERVAL = Duration.ofHours(1);
    // total wall-clock duration (24 hours)
    private static final Duration TOTAL_DURATION = Duration.ofHours(24);

    public static void main(String[] args) throws InterruptedException {
        LocalDateTime endTime = LocalDateTime.now().plus(TOTAL_DURATION);
        int run = 0;

        // optional: handle manual stop
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                System.out.println("\nShutdown requested. Exiting loop…")));

        while (LocalDateTime.now().isBefore(endTime)) {
            run++;
            String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            System.out.println("===== Run #" + run + " starting at " + stamp + " =====");

            TestNG testng = new TestNG();
            // point to your test class (replace/add more if needed)
            testng.setTestClasses(new Class[] {
                    tests.AdobeEsign.Sprint1.Esign_Agreement_PDF_Attachment_Flow.class
            });

            // unique output folder for each run
            testng.setOutputDirectory("test-output/run-" + stamp);

            try {
                testng.run();
            } catch (Throwable t) {
                t.printStackTrace(); // don't break the loop on failure
            }

            System.out.println("===== Run #" + run + " finished. Sleeping 1 hour... =====");

            // only sleep if we haven’t reached end time yet
            if (LocalDateTime.now().plus(INTERVAL).isBefore(endTime)) {
                Thread.sleep(INTERVAL.toMillis());
            }
        }

        System.out.println("All scheduled runs completed (24 hours reached).");
    }
}
