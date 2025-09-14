package com.example.appparidade;

public class RetornoPaisApi {

    private Nome nome;
    private String historico;


    public Nome getNome() {
        return nome;
    }

    public String getHistorico() {
        return historico;
    }

    public static class Nome{
        private String abreviado;

        public String getAbreviado(){
            return abreviado;
        }
    }
}
