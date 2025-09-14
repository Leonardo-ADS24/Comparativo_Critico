package com.example.appparidade;

import androidx.annotation.NonNull;

import okhttp3.OkHttpClient;
import com.google.gson.Gson;
import okhttp3.Request;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
public class RequisicaoAPI {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    public void fetchData(String url, final ApiRequestListener listener){
        Request request = new Request.Builder().url(url).build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if(listener != null){
                    listener.onFailure(e.getMessage());
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful() && response.body() != null){
                    String jsonData = response.body().string();

                    Type TypeListaPais = new TypeToken<List<RetornoPaisApi>>(){}.getType();
                    List<RetornoPaisApi> paises = gson.fromJson(jsonData, TypeListaPais);

                    if(!paises.isEmpty()){
                        RetornoPaisApi pais = paises.get(0);

                        String nomePais = pais.getNome().getAbreviado();
                        String historicoPais = pais.getHistorico();

                        String retornoFormatado = "País: " + nomePais + "\n\nHistórico:\n"+historicoPais;

                        if(listener != null){
                            listener.onSuccess(retornoFormatado);
                        }
                    }else{
                        if(listener != null){
                            listener.onFailure("ID do país não encontrado");
                        }
                    }

                }else {
                    if(listener != null){
                        listener.onFailure("Requisição falhou "+response.code());
                    }
                }

            }
        });
    }

    public interface ApiRequestListener {
        void onSuccess(String responseData);
        void onFailure(String errorMessage);
    }
}
