using MauiApp1.Models;
using System.Net.Http;
using System.Text.Json;

namespace MauiApp1.Pages;

public partial class RequisicaoPage : ContentPage
{
    private readonly HttpClient _httpClient = new HttpClient();

    // Lista que armazenar� todos os pa�ses recebidos da API
    private List<PaisIBGE> Paises = new();

    public RequisicaoPage()
    {
        InitializeComponent(); 
        CarregarPaises();      // Carrega todos os pa�ses ao abrir a p�gina
    }

    
    private async void CarregarPaises()
    {
        try
        {
            string url = "https://servicodados.ibge.gov.br/api/v1/paises/-";
            var response = await _httpClient.GetAsync(url);
            response.EnsureSuccessStatusCode();

            var json = await response.Content.ReadAsStringAsync();

            // Desserializa o JSON em lista de objetos PaisIBGE
            Paises = JsonSerializer.Deserialize<List<PaisIBGE>>(json,
                new JsonSerializerOptions { PropertyNameCaseInsensitive = true }) ?? new List<PaisIBGE>();
        }
        catch (Exception ex)
        {
            await DisplayAlert("Erro", $"N�o foi poss�vel carregar os pa�ses: {ex.Message}", "OK");
        }
    }

    
    /// Evento chamado ao clicar no bot�o "Buscar Pa�s"
    
    private void BuscarPais_Clicked(object sender, EventArgs e)
    {
        string codigo = CodigoEntry.Text?.Trim().ToUpper() ?? "";

        if (string.IsNullOrEmpty(codigo))
        {
            ResultadoNomeLabel.Text = "";
            ResultadoHistoricoLabel.Text = "Digite um c�digo v�lido.";
            return;
        }

        // Busca o pa�s pelo c�digo ISO 2 letras
        var pais = Paises.FirstOrDefault(p => p.id?.ISO_3166_1_ALPHA_2 == codigo);

        if (pais != null)
        {
            // Exibe nome e hist�rico
            ResultadoNomeLabel.Text = $"Pa�s: {pais.nome?.abreviado}";
            ResultadoHistoricoLabel.Text = $"Hist�rico:\n{pais.historico}";
        }
        else
        {
            ResultadoNomeLabel.Text = "";
            ResultadoHistoricoLabel.Text = $"Nenhum pa�s encontrado para o c�digo '{codigo}'.";
        }
    }
}


