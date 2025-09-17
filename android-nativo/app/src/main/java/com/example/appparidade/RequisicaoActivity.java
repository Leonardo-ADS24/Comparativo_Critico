package com.example.appparidade;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RequisicaoActivity extends AppCompatActivity {

    private EditText PaisIDEditText;
    private Button RequisicaoAPIButton;

    private TextView APIRespostaTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_requisicao);

        PaisIDEditText = findViewById(R.id.edit_text_pais_id);
        RequisicaoAPIButton = findViewById(R.id.button_requisicao_api);
        APIRespostaTextView = findViewById(R.id.textview_resposta_api);

        RequisicaoAPIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PaisID = PaisIDEditText.getText().toString();

                if(PaisID.isEmpty()){
                    Toast.makeText(RequisicaoActivity.this,"Digite a ID de um País",Toast.LENGTH_SHORT).show();
                }else{
                    fetchDataFromApi(PaisID);
                }
            }
        });
    }

    private void fetchDataFromApi(String PaisID){
        APIRespostaTextView.setText("Carregando...");

        String url = "https://servicodados.ibge.gov.br/api/v1/paises/" + PaisID;
        Log.d("API_CALL","URL da requisição: "+ url);

        RequisicaoAPI requisicaoAPI = new RequisicaoAPI();

        requisicaoAPI.fetchData(url, new RequisicaoAPI.ApiRequestListener() {
            @Override
            public void onSuccess(String responseData) {
                Log.d("API_CALL", "Requisição bem-sucedida. Resposta: " + responseData);
                runOnUiThread(()->{
                    APIRespostaTextView.setText(responseData);
                });
            }
            @Override
            public void onFailure(String errorMessage) {
                Log.e("API_CALL", "Erro: " + errorMessage);
                runOnUiThread(()->{
                    APIRespostaTextView.setText("Erro: "+ errorMessage);
                });

            }
        });
    }
}