package com.cuga.demo.webapp.dao;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class MongoClientHelper {

    @Autowired
    private MongoClient mongo;

    @Value("${mongo.db.name}")
    private String dbName;

    public MongoDatabase getDatabase() {
        return mongo.getDatabase(dbName);
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return getDatabase().getCollection(collectionName);
    }

    public void setMongoClient(MongoClient mongo) {
        this.mongo = mongo;
    }
}
