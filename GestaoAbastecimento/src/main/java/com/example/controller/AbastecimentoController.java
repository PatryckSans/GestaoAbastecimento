package com.example.controller;

import com.example.model.Abastecimento;
import com.example.service.AbastecimentoService;

public class AbastecimentoController {

    private AbastecimentoService abastecimentoService = new AbastecimentoService();

    public void cadastrarAbastecimento(Abastecimento abastecimento) {
        abastecimentoService.cadastrarAbastecimento(abastecimento);
        System.out.println("Abastecimento cadastrado com sucesso!");
    }
}
