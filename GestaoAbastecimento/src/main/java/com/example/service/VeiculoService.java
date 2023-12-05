package com.example.service;

import com.example.model.Veiculo;
import com.example.repository.VeiculoRepository;

public class VeiculoService {

    private VeiculoRepository veiculoRepository = new VeiculoRepository();

    public void cadastrarVeiculo(Veiculo veiculo) {
        veiculoRepository.cadastrarVeiculo(veiculo);
    }
}
