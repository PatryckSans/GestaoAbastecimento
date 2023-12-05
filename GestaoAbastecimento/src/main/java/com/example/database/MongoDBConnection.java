package com.example.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static final String CONNECTION_STRING = "mongodb+srv://patrycksans:BGwEqFbH8eTqJIzC@gestaoabastecimento.pw31dpl.mongodb.net/?retryWrites=true&w=majority";
    private static final String DATABASE_NAME = "gestao_abastecimento";
    private static MongoClient mongoClient;

    static {
        ConnectionString connectionString = new ConnectionString(CONNECTION_STRING);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        mongoClient = MongoClients.create(settings);
    }

    public static MongoDatabase connect() {
        return mongoClient.getDatabase(DATABASE_NAME);
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}