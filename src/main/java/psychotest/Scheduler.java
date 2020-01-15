package psychotest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


//class just for see that app is started
@Component
public class Scheduler {
    private static Logger logger = LoggerFactory.getLogger(Scheduler.class);
    private static Date date;

    @Scheduled(cron = "0 0 0 * * 5")
    public void reportCurrentData() {
        date = new Date();
        logger.info("Scheduler working: " + date);
    }
}