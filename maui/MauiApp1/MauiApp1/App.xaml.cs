namespace MauiApp1;

using MauiApp1.Pages;
using System.Diagnostics;


public partial class App : Application
{

    public static Stopwatch ColdStartWatch = Stopwatch.StartNew();
    public App()
    {
        InitializeComponent();
       
  

        MainPage = new NavigationPage(new MainPage());
    }
}

