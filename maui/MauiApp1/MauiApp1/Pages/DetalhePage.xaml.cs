using MauiApp1.Models;

namespace MauiApp1.Pages;

public partial class DetalhePage : ContentPage
{
    public DetalhePage(Pais pais)
    {
        PageLoadLogger.Start(nameof(DetalhePage));
        InitializeComponent();
        BindingContext = pais;

        
    }

    protected override void OnAppearing()
    {
        base.OnAppearing();
        PageLoadLogger.Stop(nameof(DetalhePage));
    }
}

