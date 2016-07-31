package com.cuga.demo.cli.person;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cuga.demo.core.dto.Person;
import com.cuga.demo.core.dto.ValueDto;

@Component
public class PersonHandler {

    private static final Logger log = LoggerFactory.getLogger(PersonHandler.class);

    @Value("${person.service.endpoint}")
    private String personService;

    private final RestTemplate rest = new RestTemplate();

    @SuppressWarnings("unchecked")
    public void handle(String name, int age) {

        // Demonstrates the ability to use a Person object from the `demo-core` project
        Person person = new Person();
        person.setName(name);
        person.setAge(age);

        ValueDto<Boolean> result = rest.postForObject(personService + "/save", person, ValueDto.class);
        log.info(String.format("Attempt to save person resulted in: %s", result.getValue()));

        String findUrl = personService + "/find/" + name;
        List<?> results = rest.getForObject(findUrl, List.class);
        log.info("Found " + results.size() + " results");
        results.forEach((personFound) -> log.info("..." + personFound));
    }
}
