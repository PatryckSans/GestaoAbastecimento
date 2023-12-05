package com.example.repository;

import com.example.database.MongoDBConnection;
import com.example.model.Veiculo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository {

    private static final String COLLECTION_NAME = "veiculos";

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = MongoDBConnection.connect();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document query = new Document("placa", placa);
            Document result = collection.find(query).first();

            if (result != null) {
                return new Veiculo(
                        result.getString("placa"),
                        result.getString("marca"),
                        result.getString("modelo")
                );
            }
        } finally {
            // MongoDBConnection.close();
        }

        return null;
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = MongoDBConnection.connect();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document document = new Document("placa", veiculo.getPlaca())
                    .append("marca", veiculo.getMarca())
                    .append("modelo", veiculo.getModelo());

            collection.insertOne(document);
        } finally {
            // MongoDBConnection.close();
        }
    }

    public List<Veiculo> listarVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = MongoDBConnection.connect();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            for (Document document : collection.find()) {
                veiculos.add(new Veiculo(
                        document.getString("placa"),
                        document.getString("marca"),
                        document.getString("modelo")
                ));
            }
        } finally {
            // MongoDBConnection.close();
        }

        return veiculos;
    }
}
