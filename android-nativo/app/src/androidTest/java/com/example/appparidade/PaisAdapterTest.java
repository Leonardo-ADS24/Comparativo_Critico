package com.example.appparidade;

import android.content.Context;

import java.util.ArrayList;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import androidx.test.platform.app.InstrumentationRegistry;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)

public class PaisAdapterTest {


    private PaisAdapter adapter;
    private List<PaisLista> listaOriginal;
    private Context context;

    @Before
    public void setup(){
        // Obtém o contexto do aplicativo para o teste instrumentado

        context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        listaOriginal = new ArrayList<>();
        listaOriginal.add(new PaisLista("Brasil","O Brasil, maior país da América do Sul, destaca-se por sua vasta extensão territorial e diversidade natural e cultural. Com mais de 213 milhões de habitantes, sendo a maioria em áreas urbanas, o país combina modernidade e tradição. Sua economia, embora liderada pelo setor de serviços, mantém relevância mundial em segmentos como a indústria petroquímica, automobilística e, sobretudo, na produção agropecuária, onde a soja se consolidou como principal destaque."));
        listaOriginal.add(new PaisLista("Argentina","Reconhecida pelo tango e pela paixão pelo futebol, a Argentina exerce grande influência cultural e esportiva na América Latina. Sua produção agropecuária e vinícola é destaque global."));
        listaOriginal.add(new PaisLista("Chile","País andino de geografia singular, o Chile se notabiliza pela estabilidade econômica e pelas exportações de cobre e vinhos. Sua cordilheira é símbolo natural de impacto mundial."));

        adapter = new PaisAdapter(context,listaOriginal);
    }

    @Test
    public void TesteFiltrarPaises(){
        adapter.filtrar("Bra");

        assertEquals(1,adapter.getItemCount());
        assertEquals("Brasil",adapter.getLista().get(0).getNome());

        adapter.filtrar("");
        assertEquals(3,adapter.getItemCount());

        adapter.filtrar("Nepal");
        assertEquals(0,adapter.getItemCount());
    }
}
