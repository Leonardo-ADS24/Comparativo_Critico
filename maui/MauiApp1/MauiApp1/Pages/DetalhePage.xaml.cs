using MauiApp1.Models;

namespace MauiApp1.Pages;

public partial class DetalhePage : ContentPage
{
    public DetalhePage(Pais pais)
    {
        InitializeComponent();
        BindingContext = pais;
    }
}
