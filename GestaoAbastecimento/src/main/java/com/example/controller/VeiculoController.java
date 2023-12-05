package com.example.controller;

import com.example.model.Veiculo;
import com.example.service.CadastroService;

public class VeiculoController {

    private CadastroService cadastroService = new CadastroService();

    public void cadastrarVeiculo(String placa, String marca, String modelo) {
        Veiculo veiculo = new Veiculo(placa, marca, modelo);
        cadastroService.cadastrarVeiculo(veiculo);
        System.out.println("Veículo cadastrado com sucesso!");
    }
}
