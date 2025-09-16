package com.example.appparidade;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private long tempoInicial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tempoInicial = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Switch TemaSwitch = findViewById(R.id.switch_tema);
        Button RequisicaoButton = findViewById(R.id.button_requisicao);
        Button ListaPaisButton = findViewById(R.id.button_lista_pais);
        Button RecursoNativoButton = findViewById(R.id.button_nativo);

        TemaSwitch.setChecked(modoNoturnoAtivo());


        TemaSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->{
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            recreate();
        });

        RequisicaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RequisicaoActivity.class);
                startActivity(intent);
            }
        });

        ListaPaisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListaPaisActivity.class);
                startActivity(intent);
            }
        });

        RecursoNativoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NativoActivity.class);
                startActivity(intent);
            }
        });

        Log.d("Tempo","Oncreate Iniciado");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            long tempoFinal = System.currentTimeMillis();
            long coldStart = tempoFinal - tempoInicial;
            Log.d("Tempo","Cold Start: "+ coldStart +" ms");
        }

    }
    //A função modoNoturnoAtivo verifica o tema do celular
    private boolean modoNoturnoAtivo(){
        int valorNoturno = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return valorNoturno == Configuration.UI_MODE_NIGHT_YES;
    }
}