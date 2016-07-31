package com.cuga.demo.webapp.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cuga.demo.core.dto.Person;
import com.cuga.demo.webapp.dao.PersonDao;

/**
 * Very handy design to handle all our business or processing logic at a level outside the DAO lavel, which aids testing and lets our DAOs
 * only need to worry about simple interactions with the database
 */
@Component
public class ProcessingEngine {

    @Autowired
    private PersonDao dao;

    public boolean savePerson(Person person) {
        try {
            // Could do other stuff here like a retry attempt to save if there is an error on the first attempt, etc
            dao.save(person);
            return true;
        } catch (RuntimeException e) {
            // This IllegalArgumentException will be picked up by our custom error handler in RestErrorHandler
            throw new IllegalArgumentException(String.format("Could not save the person: %s", e.getMessage()));
        }
    }

    public List<Person> findPeople(String name) {
        return dao.findByName(name);
    }
}