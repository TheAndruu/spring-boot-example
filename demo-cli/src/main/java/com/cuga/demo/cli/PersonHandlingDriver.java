package com.cuga.demo.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cuga.demo.cli.person.PersonHandler;

/**
 * Checks runtime arguments to decide if we should execute the time printing. Modular approach allows any other drivers to be added
 * independently of this one needing to change
 */
@Component
public class PersonHandlingDriver implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(PersonHandlingDriver.class);

    @Value("${person.driver.flag}")
    private String personDriverFlag;

    @Autowired
    private PersonHandler handler;

    @Override
    public void run(String... args) throws Exception {
        log.info("Checking PersonPrinterDriver");

        if (args.length != 3) {
            log.info(String.format("PersonPrinter executes when given arguments: %s <name> <age>", personDriverFlag));
            return;
        }

        String runType = args[0].trim();
        if (!runType.equalsIgnoreCase(personDriverFlag)) {
            log.info("Not running PersonPrinter because the first argument wasn't " + personDriverFlag);
            return;
        }

        String name = args[1];
        int age = Integer.parseInt(args[2]);

        handler.handle(name, age);
    }

}
