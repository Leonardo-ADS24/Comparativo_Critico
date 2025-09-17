namespace MauiApp1;

using MauiApp1.Pages;


public partial class App : Application
{
    public App()
    {
        InitializeComponent();
       
  

        MainPage = new NavigationPage(new MainPage());
    }
}

