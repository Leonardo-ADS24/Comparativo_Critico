using System.Collections.ObjectModel;
using System.Text.Json;
using MauiApp1.Models;
using Microsoft.Maui.Controls;

namespace MauiApp1.Pages;

public partial class ListaPage : ContentPage
{
    
    public ObservableCollection<Pais> Paises { get; set; } = new();

    
    private List<Pais> todosOsPaises = new();

    public ListaPage()
    {
        InitializeComponent();
        BindingContext = this;
        CarregarDados();
    }
   
    private async void CarregarDados()
    {
        try
        {
            using var stream = await FileSystem.Current.OpenAppPackageFileAsync("itens.json");

            // O stream pode ser nulo se o arquivo não for encontrado.
            if (stream == null)
            {
                await DisplayAlert("Erro", "O arquivo de dados (itens.json) não foi encontrado.", "OK");
                return;
            }

            // Desserializa o stream diretamente para uma lista de objetos 'Pais'
            var listaDePaises = await JsonSerializer.DeserializeAsync<List<Pais>>(stream);

            // Atribui a lista se não for nula, senão usa uma lista vazia.
            todosOsPaises = listaDePaises ?? new List<Pais>();

            // Adiciona os itens à ObservableCollection para atualizar a UI.
            Paises.Clear();
            foreach (var pais in todosOsPaises)
                Paises.Add(pais);
        }
        catch (Exception ex)
        {
            await DisplayAlert("Erro", $"Falha ao carregar dados: {ex.Message}", "OK");
        }
    }

    private void SearchBar_TextChanged(object sender, TextChangedEventArgs e)
    {
        var filtro = e.NewTextValue?.ToLower() ?? string.Empty;

        if (todosOsPaises == null)
            return;

        var filtrados = !string.IsNullOrWhiteSpace(filtro)
            ? todosOsPaises.Where(p => !string.IsNullOrEmpty(p.Nome) && p.Nome.ToLower().Contains(filtro))
            : todosOsPaises;

        Paises.Clear();
        foreach (var pais in filtrados)
            Paises.Add(pais);
    }

    private async void CollectionView_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (e.CurrentSelection.FirstOrDefault() is Pais paisSelecionado)
        {
            await Navigation.PushAsync(new DetalhePage(paisSelecionado));
            ((CollectionView)sender).SelectedItem = null; // limpa seleção
        }
    }
}