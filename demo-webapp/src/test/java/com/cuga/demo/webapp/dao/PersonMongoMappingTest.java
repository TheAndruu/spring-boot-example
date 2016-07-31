package com.cuga.demo.webapp.dao;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import com.cuga.demo.core.dto.Person;

public class PersonMongoMappingTest {

    private PersonMongoMapping mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new PersonMongoMapping();
    }

    @Test
    public void testToDatabaseToModel() {
        Person person = new Person();
        ObjectId id = new ObjectId();
        person.setId(id.toHexString());
        person.setName("foo");
        person.setAge(22);

        Person reconstituted = mapper.toModel(mapper.toDatabase(person));
        assertEquals(id.toHexString(), reconstituted.getId());
        assertEquals("foo", reconstituted.getName());
        assertEquals(22, reconstituted.getAge());
    }

    @Test
    public void testFieldNames() {
        assertEquals("Person", PersonMongoMapping.COLLECTION_NAME);
        assertEquals("_id", PersonMongoMapping.ID);
        assertEquals("name", PersonMongoMapping.NAME);
        assertEquals("age", PersonMongoMapping.AGE);

    }

}
