package com.cuga.demo.webapp.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cuga.demo.core.dto.Person;
import com.cuga.demo.core.dto.ValueDto;
import com.cuga.demo.webapp.engine.ProcessingEngine;

@RestController
@RequestMapping(value = "${rest.service.context}", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonRestController {

    private static final Logger log = LoggerFactory.getLogger(PersonRestController.class);

    @Autowired
    private ProcessingEngine engine;

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ValueDto<Boolean> savePerson(@RequestBody Person person) {
        log.info(String.format("Saving person object with name %s", person.getName()));
        return new ValueDto<>(engine.savePerson(person));
    }

    @RequestMapping(value = "/find/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findPeopleWithName(@PathVariable String name) {
        log.info(String.format("Searching for people with name %s", name));
        return engine.findPeople(name);
    }
}