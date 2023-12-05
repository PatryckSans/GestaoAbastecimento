package com.example.model;

public class Posto {
    private String nome;
    private String localizacao;


    public Posto(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "\n\nPosto{" +
                "\nnome='" + nome +
                ", \nlocalizacao='" + localizacao +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
