package com.example.appparidade;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;

public class DetalheActivity extends AppCompatActivity {

    TextView textNome, textDescricao;
    ImageButton btnVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhe);


        textNome = findViewById(R.id.text_view_nome_pais);
        textDescricao = findViewById(R.id.text_view_descricao_pais);

        btnVoltar = findViewById(R.id.btn_voltar_requisicao);

        String nome = getIntent().getStringExtra("nome");
        String descricao = getIntent().getStringExtra("descricao");

        textNome.setText(nome);
        textDescricao.setText(descricao);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            long tempoFinal = System.currentTimeMillis();
            long tempoTotal = tempoFinal - MainActivity.tempoInicial;
            Log.d("Tempo - Detalhe", "Tempo Stopwatch até abrir DetalheActivity: " + tempoTotal + " ms");
        }
    }
}