using MauiApp1.Pages;
using Microsoft.Maui.Controls;

namespace MauiApp1;

public partial class MainPage : ContentPage
{

    public MainPage()
    {
        InitializeComponent();
    }
    //botões Lista Json e Requisição HTTP
    private async void OnPaginaListaClicked(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new ListaPage());
    }

    private async void OnPaginaRequisicaoClicked(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new RequisicaoPage());
    }


    //botão recurso nativo geolocalização
    private async void OnRecursoNativoClicked(object sender, EventArgs e)
    {
        try
        {
            var status = await Permissions.CheckStatusAsync<Permissions.LocationWhenInUse>();

            if (status != PermissionStatus.Granted)
            {
                status = await Permissions.RequestAsync<Permissions.LocationWhenInUse>();
            }

            if (status == PermissionStatus.Granted)
            {
                var location = await Geolocation.GetLocationAsync();
                if (location != null)
                {
                    await DisplayAlert("Localização Obtida", $"Latitude: {location.Latitude}, Longitude: {location.Longitude}", "OK");
                }
                else
                {
                    await DisplayAlert("Erro", "Não foi possível obter a localização.", "OK");
                }
            }
            else
            {
                await DisplayAlert("Permissão Negada", "O aplicativo não tem permissão para acessar a localização.", "OK");
            }
        }
        catch (Exception ex)
        {
            await DisplayAlert("Erro Inesperado", $"Ocorreu um erro: {ex.Message}", "OK");
        }
    }

    // tema dark ou light 
    private void OnThemeToggled(object sender, ToggledEventArgs e)
    {
        if (e.Value)
        {
            Application.Current.UserAppTheme = AppTheme.Dark;
        }
        else
        {
            Application.Current.UserAppTheme = AppTheme.Light;
        }
    }
}

