package com.cuga.demo.webapp.dao;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.cuga.demo.core.dto.Person;

@Component
public class PersonMongoMapping {
    public static final String COLLECTION_NAME = "Person";

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    public Document toDatabase(Person person) {
        Document doc = new Document();

        String id = person.getId();
        if (id != null && id.length() > 0) {
            doc.append(ID, new ObjectId(id));
        }

        doc.append(NAME, person.getName());
        doc.append(AGE, person.getAge());

        return doc;
    }

    public Person toModel(Document document) {
        Person person = new Person();
        person.setId(document.getObjectId(ID).toHexString());
        person.setName(document.getString(NAME));
        person.setAge(document.getInteger(AGE, 0));

        return person;
    }
}
