package com.example.service;

import com.example.model.Posto;
import com.example.model.Veiculo;
import com.example.model.Abastecimento;
import com.example.repository.VeiculoRepository;
import com.example.repository.PostoRepository;
import com.example.repository.AbastecimentoRepository;

import java.util.List;

public class CadastroService {

    private VeiculoRepository veiculoRepository = new VeiculoRepository();
    private PostoRepository postoRepository = new PostoRepository();
    private AbastecimentoRepository abastecimentoRepository = new AbastecimentoRepository();

    public void cadastrarVeiculo(Veiculo veiculo) {
        veiculoRepository.cadastrarVeiculo(veiculo);
    }

    public void cadastrarPosto(Posto posto) {
        postoRepository.cadastrarPosto(posto);
    }

    public void cadastrarAbastecimento(Abastecimento abastecimento) {
        Veiculo veiculo = veiculoRepository.buscarVeiculoPorPlaca(abastecimento.getVeiculo().getPlaca());
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não encontrado");
        }

        Posto posto = postoRepository.buscarPostoPorNome(abastecimento.getPosto().getNome());
        if (posto == null) {
            throw new IllegalArgumentException("Posto não encontrado");
        }

        abastecimentoRepository.cadastrarAbastecimento(abastecimento);
    }

    public double calcularMediaKmPorLitroPorPlaca(String placa) {
        List<Abastecimento> abastecimentos = abastecimentoRepository.listarAbastecimentosPorPlaca(placa);
        if (abastecimentos.isEmpty()) {
            return 0.0;
        }

        double totalKm = 0.0;
        double totalLitros = 0.0;

        for (Abastecimento abastecimento : abastecimentos) {
            totalKm += abastecimento.getQuilometrosOdometro();
            totalLitros += abastecimento.getQuantidadeLitros();
        }

        return totalKm / totalLitros;
    }

    public double calcularMediaPrecoPorPlaca(String placa) {
        List<Abastecimento> abastecimentos = abastecimentoRepository.listarAbastecimentosPorPlaca(placa);
        if (abastecimentos.isEmpty()) {
            return 0.0;
        }

        double totalPreco = 0.0;

        for (Abastecimento abastecimento : abastecimentos) {
            totalPreco += abastecimento.getPreco();
        }

        return totalPreco / abastecimentos.size();
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.listarVeiculos();
    }

    public List<Posto> listarPostos() {
        return postoRepository.listarPostos();
    }

    public List<Abastecimento> listarAbastecimentos() {
        return abastecimentoRepository.listarAbastecimentos();
    }
}
