package com.example.appparidade;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
//import androidx.appcompat.widget.SearchView;
import android.view.View;
import android.widget.SearchView;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;

import android.os.Handler;
import android.util.Log;
import java.lang.Runtime;

public class ListaPaisActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    List<PaisLista> lista = new ArrayList<>();
    PaisAdapter adapter;

    ImageButton btnVoltar;

    private long tempoInicial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_pais);

        tempoInicial = getIntent().getLongExtra("tempoInicialApp", -1);

        btnVoltar = findViewById(R.id.btn_voltar_requisicao);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        carregarDadosJSON();

        adapter = new PaisAdapter(this, lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filtrar(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filtrar(newText);
                return false;
            }
        });

        new Handler().postDelayed(() -> {
            Runtime runtime = Runtime.getRuntime();
            long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
            Log.d("MemoryUsage", "Memória em repouso após 30s: " + usedMemory + " MB");
        }, 30000);
    }


    private void carregarDadosJSON() {
        try {
            InputStream is = getAssets().open("dados_paises.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String nome = obj.getString("nome");
                String descricao = obj.getString("descricao");
                lista.add(new PaisLista(nome, descricao));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && tempoInicial > 0){
            long tempoFinal = System.nanoTime();
            long tempoTotal = (tempoFinal - tempoInicial) /1_000_000;
            Log.d("Tempo - Lista", "Tempo Stopwatch até abrir ListaPaisActivity: " + tempoTotal + " ms");
        }
    }
}