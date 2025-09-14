package com.example.appparidade;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Switch TemaSwitch = findViewById(R.id.switch_tema);

        TemaSwitch.setChecked(modoNoturnoAtivo());

        //
        TemaSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->{
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            recreate();
        });
    }


    //A função modoNoturnoAtivo verifica o tema do celular
    private boolean modoNoturnoAtivo(){
        int valorNoturno = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return valorNoturno == Configuration.UI_MODE_NIGHT_YES;
    }
}