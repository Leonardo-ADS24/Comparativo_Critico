package com.example.appparidade;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalheActivity extends AppCompatActivity {

    TextView textNome, textDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhe);

        textNome = findViewById(R.id.text_view_nome_pais);
        textDescricao = findViewById(R.id.text_view_descricao_pais);

        Button ButtonTelaDetalhe = findViewById(R.id.button_telaVoltar);

        String nome = getIntent().getStringExtra("nome");
        String descricao = getIntent().getStringExtra("descricao");

        textNome.setText(nome);
        textDescricao.setText(descricao);

        ButtonTelaDetalhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalheActivity.this, ListaPaisActivity.class);
                startActivity(intent);
            }
        });






    }
}