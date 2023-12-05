package com.example.repository;

import com.example.database.MongoDBConnection;
import com.example.model.Abastecimento;
import com.example.model.Posto;
import com.example.model.Veiculo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbastecimentoRepository {

    private static final String COLLECTION_NAME = "abastecimentos";

    public void cadastrarAbastecimento(Abastecimento abastecimento) {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = MongoDBConnection.connect();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document document = new Document("veiculo", abastecimento.getVeiculo().getPlaca())
                    .append("posto", abastecimento.getPosto().getNome())
                    .append("data", abastecimento.getData())
                    .append("quantidadeLitros", abastecimento.getQuantidadeLitros())
                    .append("quilometrosOdometro", abastecimento.getQuilometrosOdometro())
                    .append("preco", abastecimento.getPreco())
                    .append("tipoCombustivel", abastecimento.getTipoCombustivel());

            collection.insertOne(document);
        } finally {
            // MongoDBConnection.close();
        }
    }

    public List<Abastecimento> listarAbastecimentos() {
        List<Abastecimento> abastecimentos = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = MongoDBConnection.connect();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            for (Document document : collection.find()) {
                Veiculo veiculo = buscarVeiculoPorPlaca(document.getString("veiculo"));
                Posto posto = buscarPostoPorNome(document.getString("posto"));

                abastecimentos.add(new Abastecimento(
                        veiculo,
                        posto,
                        document.getDate("data"),
                        document.getDouble("quantidadeLitros"),
                        document.getInteger("quilometrosOdometro"),
                        document.getDouble("preco"),
                        document.getString("tipoCombustivel")
                ));
            }
        } finally {
            // MongoDBConnection.close();
        }

        return abastecimentos;
    }

    public double calcularMediaKmPorLitroPorPlaca(String placa) {
        List<Abastecimento> abastecimentos = listarAbastecimentosPorPlaca(placa);
    
        if (abastecimentos.isEmpty()) {
            return 0.0;
        }
    
        double somaKmPorLitro = abastecimentos.stream()
                .mapToDouble(abastecimento -> (double) abastecimento.getQuilometrosOdometro() / abastecimento.getQuantidadeLitros())
                .sum();
    
        return somaKmPorLitro / abastecimentos.size();
    }
    
    public double calcularMediaPrecoPorPlaca(String placa) {
        List<Abastecimento> abastecimentos = listarAbastecimentosPorPlaca(placa);
    
        if (abastecimentos.isEmpty()) {
            return 0.0;
        }
    
        double somaPreco = abastecimentos.stream()
                .mapToDouble(Abastecimento::getPreco)
                .sum();
    
        return somaPreco / abastecimentos.size();
    }

    public List<Abastecimento> listarAbastecimentosPorPlaca(String placa) {
        List<Abastecimento> abastecimentos = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = MongoDBConnection.connect();
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            Document query = new Document("veiculo", placa);
            for (Document document : collection.find(query)) {
                Veiculo veiculo = buscarVeiculoPorPlaca(document.getString("veiculo"));
                Posto posto = buscarPostoPorNome(document.getString("posto"));

                abastecimentos.add(new Abastecimento(
                        veiculo,
                        posto,
                        document.getDate("data"),
                        document.getDouble("quantidadeLitros"),
                        document.getInteger("quilometrosOdometro"),
                        document.getDouble("preco"),
                        document.getString("tipoCombustivel")
                ));
            }
        } finally {
            // MongoDBConnection.close();
        }

        return abastecimentos;
    }

    private Veiculo buscarVeiculoPorPlaca(String placa) {
        VeiculoRepository veiculoRepository = new VeiculoRepository();
        return veiculoRepository.buscarVeiculoPorPlaca(placa);
    }

    private Posto buscarPostoPorNome(String nome) {
        PostoRepository postoRepository = new PostoRepository();
        return postoRepository.buscarPostoPorNome(nome);
    }
}
