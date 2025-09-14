package com.example.appparidade;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class NativoActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private ActivityResultLauncher<String[]> locationPermissionLauncher;

    private TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nativo);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationTextView = findViewById(R.id.textView_Localizacao);


        locationPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), permissions -> {
                    if (Boolean.TRUE.equals(permissions.get(android.Manifest.permission.ACCESS_FINE_LOCATION))) {
                        getLastKnownLocation();
                    } else {
                        Log.e("Permissão", "Permissão de localização negada pelo usuário.");
                    }
                });
        checkAndRequestLocationPermissions();
    }

    private void checkAndRequestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastKnownLocation();
        } else {
            locationPermissionLauncher.launch(new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
    }

    private void getLastKnownLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();

                        String locationInfo = "Latitude: " + latitude + "\n" + "Longitude: " + longitude;

                        locationTextView.setText(locationInfo);

                        Log.d("Localização", "Latitude: " + latitude + ", Longitude: " + longitude);
                    } else {
                        locationTextView.setText("Localização não disponível.");
                        Log.e("Localização", "Última localização conhecida é nula.");
                    }
                }
            });
        }
    }
}