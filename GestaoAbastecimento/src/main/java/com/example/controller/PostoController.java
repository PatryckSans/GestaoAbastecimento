package com.example.controller;

import com.example.model.Posto;
import com.example.service.CadastroService;

public class PostoController {

    private CadastroService cadastroService = new CadastroService();

    public void cadastrarPosto(String nome, String localizacao) {
        Posto posto = new Posto(nome, localizacao);
        cadastroService.cadastrarPosto(posto);
        System.out.println("Posto cadastrado com sucesso!");
    }
}
