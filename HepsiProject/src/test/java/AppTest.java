import org.apache.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AppTest {
    private static final Logger logger = Logger.getLogger(AppTest.class);
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(HepsiTest.class);
        for (Failure failure : result.getFailures()) {
            logger.info(failure.toString());
        }
    }
}
