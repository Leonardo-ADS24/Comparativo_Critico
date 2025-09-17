using System.Text.Json.Serialization;

namespace MauiApp1.Models
{
    // Modelo que representa os dados do país recebidos da API do IBGE
    public class PaisIBGE
    {
        public Id? id { get; set; }          // Contém os códigos do país (ISO 2 e 3 letras)
        public Nome? nome { get; set; }      // Contém o nome abreviado e em outros idiomas
        public string? historico { get; set; } // Histórico do país
    }

    public class Id
    {
        public int M49 { get; set; }

        [JsonPropertyName("ISO-3166-1-ALPHA-2")]
        public string? ISO_3166_1_ALPHA_2 { get; set; } // Código do país 2 letras (ex: BR)

        [JsonPropertyName("ISO-3166-1-ALPHA-3")]
        public string? ISO_3166_1_ALPHA_3 { get; set; } // Código do país 3 letras (ex: BRA)
    }

    public class Nome
    {
        public string? abreviado { get; set; }      // Nome do país em português
        public string? abreviado_EN { get; set; }   // Nome do país em inglês
        public string? abreviado_ES { get; set; }   // Nome do país em espanhol
    }
}
