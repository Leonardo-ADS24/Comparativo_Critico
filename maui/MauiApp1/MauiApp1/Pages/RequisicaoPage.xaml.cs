using MauiApp1.Models;
using System.Net.Http;
using System.Text.Json;

namespace MauiApp1.Pages;

public partial class RequisicaoPage : ContentPage
{
    private readonly HttpClient _httpClient = new HttpClient();

    // Lista que armazenará todos os países recebidos da API
    private List<PaisIBGE> Paises = new();

    public RequisicaoPage()
    {
        InitializeComponent(); 
        CarregarPaises();      // Carrega todos os países ao abrir a página
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
            await DisplayAlert("Erro", $"Não foi possível carregar os países: {ex.Message}", "OK");
        }
    }

    
    /// Evento chamado ao clicar no botão "Buscar País"
    
    private void BuscarPais_Clicked(object sender, EventArgs e)
    {
        string codigo = CodigoEntry.Text?.Trim().ToUpper() ?? "";

        if (string.IsNullOrEmpty(codigo))
        {
            ResultadoNomeLabel.Text = "";
            ResultadoHistoricoLabel.Text = "Digite um código válido.";
            return;
        }

        // Busca o país pelo código ISO 2 letras
        var pais = Paises.FirstOrDefault(p => p.id?.ISO_3166_1_ALPHA_2 == codigo);

        if (pais != null)
        {
            // Exibe nome e histórico
            ResultadoNomeLabel.Text = $"País: {pais.nome?.abreviado}";
            ResultadoHistoricoLabel.Text = $"Histórico:\n{pais.historico}";
        }
        else
        {
            ResultadoNomeLabel.Text = "";
            ResultadoHistoricoLabel.Text = $"Nenhum país encontrado para o código '{codigo}'.";
        }
    }
}


