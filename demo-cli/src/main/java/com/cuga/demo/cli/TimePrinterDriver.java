package com.cuga.demo.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cuga.demo.cli.time.TimePrinter;

/**
 * Checks runtime arguments to decide if we should execute the time printing. Modular approach allows any other drivers to be added
 * independently of this one needing to change
 */
@Component
public class TimePrinterDriver implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(TimePrinterDriver.class);

    @Value("${time.driver.flag}")
    private String timeDriverFlag;

    @Autowired
    private TimePrinter printer;

    @Override
    public void run(String... args) throws Exception {
        log.info("Checking TimePrinterDriver");

        if (args.length != 1) {
            log.info(String.format("TimePrinter executes when given argument: %s", timeDriverFlag));
            return;
        }

        String runType = args[0].trim();
        if (!runType.equalsIgnoreCase(timeDriverFlag)) {
            log.info("Not running TimePrinter because the first argument wasn't " + timeDriverFlag);
            return;
        }

        printer.printTime();

    }

}
