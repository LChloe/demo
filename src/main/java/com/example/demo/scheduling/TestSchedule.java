package com.example.demo.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@EnableScheduling
@Configuration
public class TestSchedule {
    public static final Logger LOGGER = LoggerFactory.getLogger(TestSchedule.class);

    @Scheduled(cron = "0 0 11 * * ?")//  */15 * * * * ? 每15s
    public void cleanHistory(){
        LOGGER.info("test");
    }

}
