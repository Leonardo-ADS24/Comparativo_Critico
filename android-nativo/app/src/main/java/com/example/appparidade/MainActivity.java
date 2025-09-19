package com.example.appparidade;

import android.Manifest;
import android.app.AlertDialog;
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


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class MainActivity extends AppCompatActivity {

    private long tempoInicial;

    private FusedLocationProviderClient fusedLocationClient;
    private ActivityResultLauncher<String[]> locationPermissionLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tempoInicial = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Switch TemaSwitch = findViewById(R.id.switch_tema);
        Button RequisicaoButton = findViewById(R.id.button_requisicao);
        Button ListaPaisButton = findViewById(R.id.button_lista_pais);
        Button RecursoNativoButton = findViewById(R.id.button_pegar_localizacao);
        Button PegarLocalizacaoButton = findViewById(R.id.button_pegar_localizacao);



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


        PegarLocalizacaoButton.setOnClickListener(v -> checkAndRequestLocationPermissions2());


        locationPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), permissions -> {
                    if (Boolean.TRUE.equals(permissions.get(android.Manifest.permission.ACCESS_FINE_LOCATION))) {
                        getLastKnownLocation2();
                    } else {
                        showAlertDialog("Permissão Negada", "O  aplicativo não tem permissão para acessar a localização.");
                        Log.e("Permissão", "Permissão de localização negada pelo usuário.");
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

    private void checkAndRequestLocationPermissions2() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastKnownLocation2();
        } else {
            locationPermissionLauncher.launch(new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
    }

    private void getLastKnownLocation2(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        String locationInfo = "Latitude: " + latitude + "\n" + "Longitude: " + longitude;

                        showAlertDialog("Localização Obtida",locationInfo);

                        Log.d("Localização", "Latitude: " + latitude + ", Longitude: " + longitude);
                    }else{
                        showAlertDialog("Erro","Localização não disponível.");
                        Log.e("Localização", "Última localização conhecida é nula.");
                    }
                }
            });
        }
    }

    private void showAlertDialog(String title,String message){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK",((dialog, which) -> dialog.dismiss()))
                .setCancelable(true)
                .show();
    }
}