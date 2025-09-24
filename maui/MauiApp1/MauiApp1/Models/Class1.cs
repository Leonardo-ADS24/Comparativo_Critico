using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;

public static class PageLoadLogger
{
    private static Stopwatch? _stopwatch;

    public static void Start(string pageName)
    {
        _stopwatch = new Stopwatch();
        _stopwatch.Start();
        Console.WriteLine($"⏱️ Navegação iniciada para {pageName}...");
    }

    public static void Stop(string pageName)
    {
        if (_stopwatch != null && _stopwatch.IsRunning)
        {
            _stopwatch.Stop();
            Console.WriteLine($"✅ {pageName} carregada em {_stopwatch.ElapsedMilliseconds} ms");
        }
    }
}
