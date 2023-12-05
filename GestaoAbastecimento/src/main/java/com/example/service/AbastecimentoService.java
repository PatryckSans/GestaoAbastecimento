package com.example.service;

import com.example.model.Abastecimento;
import com.example.repository.AbastecimentoRepository;

public class AbastecimentoService {

    private AbastecimentoRepository abastecimentoRepository = new AbastecimentoRepository();

    public void cadastrarAbastecimento(Abastecimento abastecimento) {
        abastecimentoRepository.cadastrarAbastecimento(abastecimento);
    }
}
