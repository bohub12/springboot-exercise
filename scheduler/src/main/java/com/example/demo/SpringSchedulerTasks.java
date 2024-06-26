package com.example.demo;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringSchedulerTasks {
    private static final Logger log = LoggerFactory.getLogger(SpringSchedulerTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTimes() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
