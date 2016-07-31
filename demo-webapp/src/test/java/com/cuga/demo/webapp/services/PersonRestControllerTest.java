package com.cuga.demo.webapp.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cuga.demo.core.dto.Person;
import com.cuga.demo.core.dto.ValueDto;
import com.cuga.demo.webapp.engine.ProcessingEngine;

/**
 * Uses mocks to verify the behavior we want. As this is a REST service facade, we just need to verify we're invoking the underlying engine
 * class with the right parameters.
 */
public class PersonRestControllerTest {

    @InjectMocks
    private PersonRestController controller;

    @Mock
    private ProcessingEngine engine;

    @Before
    public void setUp() throws Exception {
        controller = new PersonRestController();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSavePerson() {
        Person person = new Person();
        // Set up the expectations
        when(engine.savePerson(person)).thenReturn(true);

        // Execute the test
        ValueDto<Boolean> result = controller.savePerson(person);

        // Verify the interaction with our mock and the results match our expectations
        verify(engine).savePerson(person);
        assertTrue(result.getValue());
    }

    @Test
    public void testFindPeopleWithName() {
        List<Person> people = new ArrayList<>();
        people.add(new Person());
        people.add(new Person());

        // Set up the expectations
        when(engine.findPeople("barf")).thenReturn(people);

        // Execute the test
        List<Person> results = controller.findPeopleWithName("barf");

        // Verify the interaction with our mock and the results match our expectations
        verify(engine).findPeople("barf");
        assertEquals(people, results);
    }

}
