package com.cuga.demo.cli.time;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TimePrinter {

    private static final Logger log = LoggerFactory.getLogger(TimePrinter.class);

    /** Prints the time once a second for 10 seconds */
    public void printTime() {
        long end = System.currentTimeMillis() + 10000;

        while (end >= System.currentTimeMillis()) {
            log.info(String.format("It is now %s", LocalTime.now()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }

    }
}
