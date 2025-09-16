package com.example.appparidade;

import android.content.Context;

import java.util.ArrayList;

import androidx.test.ext.junit.runners.AndroidJUnit4;
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
        listaOriginal.add(new PaisLista("Peru", "O Peru guarda heranças históricas únicas, como Machu Picchu, símbolo da civilização inca. Além da relevância cultural, tem crescimento no turismo e na exploração de recursos minerais."));
        listaOriginal.add(new PaisLista("México", "Com rica herança indígena e colonial, o México é potência cultural e econômica. Destaca-se na culinária reconhecida pela UNESCO e na indústria automobilística, com projeção global."));
        listaOriginal.add(new PaisLista("Espanha", "Nação europeia marcada pela diversidade regional, a Espanha exerce forte influência cultural no mundo, seja pela língua, pelas artes ou pelo turismo, sendo um dos destinos mais visitados do planeta."));
        listaOriginal.add(new PaisLista("França", "Símbolo global de moda, gastronomia e patrimônio cultural, a França também se consolida como potência econômica e política na União Europeia, com Paris como epicentro de sua projeção mundial."));
        listaOriginal.add(new PaisLista("Itália", "Berço de impérios e do Renascimento, a Itália alia tradição histórica e inovação. Reconhecida por sua gastronomia e arte, também tem forte papel econômico na indústria e no design."));
        listaOriginal.add(new PaisLista("Japão", "Combinando tecnologia de ponta e preservação de tradições milenares, o Japão é potência global. Sua economia é uma das maiores do mundo, e sua cultura exerce fascínio internacional."));
        listaOriginal.add(new PaisLista("Canadá", "O Canadá, uma das maiores economias e nações mais desenvolvidas do planeta, ocupa a vasta porção norte da América do Norte. Com uma área que o torna o segundo maior país do mundo, sua paisagem e clima são extremamente diversos, variando de zonas temperadas no sul a regiões árticas ao norte. Apesar de seu tamanho, a população de 37,7 milhões de habitantes se concentra nas áreas mais meridionais, onde os indicadores sociais e econômicos são excepcionalmente elevados."));
        listaOriginal.add(new PaisLista("Estados Unidos", "Os Estados Unidos, uma superpotência global, exercem uma influência sem precedentes na política, economia e cultura mundial. Com a terceira maior população do planeta e uma economia diversificada e inovadora, o país é um polo de atração para talentos e negócios, impulsionando avanços tecnológicos e dominando mercados em diversos setores."));
        listaOriginal.add(new PaisLista("Alemanha", "Como um motor da economia europeia, a Alemanha é sinônimo de excelência em engenharia e inovação tecnológica. Sua economia robusta e exportadora, combinada com uma infraestrutura de ponta e uma força de trabalho altamente qualificada, garante ao país uma posição de liderança industrial e um papel crucial na estabilidade econômica da União Europeia."));
        listaOriginal.add(new PaisLista("Portugal", "Portugal, uma nação com uma rica herança de explorações marítimas, hoje se destaca como um vibrante centro de inovação e turismo. Com um clima ameno, paisagens deslumbrantes e uma cultura acolhedora, o país tem atraído cada vez mais investidores e nômades digitais, revitalizando sua economia e estabelecendo-se como uma ponte entre a Europa e o restante do mundo."));
        listaOriginal.add(new PaisLista("Índia", "A Índia se projeta no cenário global como um dos países de maior crescimento econômico e uma potência tecnológica emergente. Com uma população que ultrapassa 1,4 bilhão de pessoas, o país é um caldeirão de diversidade cultural, história milenar e inovação moderna, com um setor de tecnologia em rápida expansão que atrai investimentos e talentos do mundo inteiro."));
        listaOriginal.add(new PaisLista("Austrália", "Conhecida por sua vida selvagem única e vastas paisagens, a Austrália é uma nação insular que combina um estilo de vida descontraído com uma economia forte e próspera. Rica em recursos naturais e com cidades cosmopolitas que oferecem uma alta qualidade de vida, o país se destaca por sua estabilidade política e econômica, além de ser um destino popular para estudantes e imigrantes."));
        listaOriginal.add(new PaisLista("África do Sul", "A África do Sul, com uma história complexa e uma impressionante diversidade natural e cultural, é uma das nações mais influentes do continente africano. Suas paisagens variam de savanas a montanhas e praias, enquanto sua economia se destaca pela mineração e finanças. O país enfrenta desafios, mas continua a ser um polo de inovação e um exemplo de resiliência em meio às transformações sociais."));
        listaOriginal.add(new PaisLista("Rússia", "A Rússia, o maior país do mundo em área, possui um papel geopolítico e cultural de grande relevância. Com vastos recursos naturais, incluindo petróleo e gás, sua economia é uma das maiores do mundo. O país é um polo de poder global, com uma rica tradição em artes, ciência e literatura que moldou a história e a cultura do leste europeu e da Ásia."));
        listaOriginal.add(new PaisLista("Grécia", "Considerada o berço da civilização ocidental, a Grécia é uma nação cuja influência na filosofia, democracia e artes é sentida até hoje. Com uma história milenar e ilhas paradisíacas que atraem milhões de turistas, o país tem se recuperado economicamente, focando no turismo e na inovação, enquanto preserva um legado cultural que é patrimônio da humanidade."));
        listaOriginal.add(new PaisLista("Turquia", "A Turquia, estrategicamente localizada entre a Europa e a Ásia, é uma ponte cultural e econômica entre os dois continentes. Com uma herança histórica que mistura impérios e tradições, o país é um importante centro comercial e turístico. Sua economia dinâmica e sua crescente influência regional a tornam uma nação-chave para o comércio e as relações internacionais na Eurásia."));
        listaOriginal.add(new PaisLista("China", "País mais populoso do planeta, a China é hoje uma das maiores potências econômicas globais. Lidera em tecnologia, produção industrial e comércio, moldando a geopolítica contemporânea."));


        adapter = new PaisAdapter(context,listaOriginal);
    }

    @Test
    public void TesteFiltrarPaises(){
        adapter.filtrar("Bra");

        assertEquals(1,adapter.getItemCount());
        assertEquals("Brasil",adapter.getLista().get(0).getNome());

        adapter.filtrar("");
        assertEquals(20,adapter.getItemCount());

        adapter.filtrar("Nepal");
        assertEquals(0,adapter.getItemCount());
    }
}
