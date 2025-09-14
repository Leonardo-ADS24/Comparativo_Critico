package com.example.appparidade;

public class PaisLista {

    private String nome;
    private String descricao;

    public PaisLista(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }
}

