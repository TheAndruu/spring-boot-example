package com.cuga.demo.webapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cuga.demo.core.dto.Person;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;

/**
 * DAO class responsible for purely interacting with the database. Any validation on inputs or any logic outside of reading/writing to the
 * database should happen outside this class
 * 
 * Can be tested with an integration test using Flapdoodle's embedded mongo dependency. Left out the example at this time.
 */
@Component
public class PersonDao {

    @Autowired
    private MongoClientHelper mongo;

    @Autowired
    private PersonMongoMapping mapper;

    public void save(Person person) {
        getCollection().insertOne(mapper.toDatabase(person));
    }

    public List<Person> findByName(String name) {
        Document searchDoc = new Document();
        searchDoc.append(PersonMongoMapping.NAME, name);
        final List<Person> results = new ArrayList<>();
        getCollection().find(searchDoc).forEach(convertToPerson(results));
        return results;
    }

    private Block<Document> convertToPerson(List<Person> results) {
        return new Block<Document>() {
            @Override
            public void apply(Document document) {
                results.add(mapper.toModel(document));
            }
        };
    }

    private MongoCollection<Document> getCollection() {
        return mongo.getCollection(PersonMongoMapping.COLLECTION_NAME);
    }

}