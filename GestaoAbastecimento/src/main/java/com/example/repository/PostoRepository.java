package com.example.repository;

import com.example.database.MongoDBConnection;
import com.example.model.Posto;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class PostoRepository {

    private static final String COLLECTION_NAME = "postos";

    public Posto buscarPostoPorNome(String nome) {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = MongoDBConnection.connect();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document query = new Document("nome", nome);
            Document result = collection.find(query).first();

            if (result != null) {
                return new Posto(
                        result.getString("nome"),
                        result.getString("localizacao")
                );
            }
        } finally {
            // MongoDBConnection.close();
        }

        return null;
    }

    public void cadastrarPosto(Posto posto) {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = MongoDBConnection.connect();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document document = new Document("nome", posto.getNome())
                    .append("localizacao", posto.getLocalizacao());

            collection.insertOne(document);
        } finally {
            // MongoDBConnection.close();
        }
    }

    public List<Posto> listarPostos() {
        List<Posto> postos = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = MongoDBConnection.connect();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            for (Document document : collection.find()) {
                postos.add(new Posto(
                        document.getString("nome"),
                        document.getString("localizacao")
                ));
            }
        } finally {
            // MongoDBConnection.close();
        }

        return postos;
    }
}
