package com.cuga.demo.core.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PersonTest {

    private Person person;

    @Before
    public void setUp() throws Exception {
        person = new Person();
    }

    @Test
    public void testGetId() {
        person.setId("bar");
        assertEquals("bar", person.getId());
    }

    @Test
    public void testGetName() {
        person.setName("foo");
        assertEquals("foo", person.getName());
    }

    @Test
    public void testGetAge() {
        person.setAge(12);
        assertEquals(12, person.getAge());
    }

}
