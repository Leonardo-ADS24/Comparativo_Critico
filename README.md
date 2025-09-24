# 📱 Comparativo Crítico: .NET MAUI vs Android Nativo  

Este repositório contém o projeto da atividade acadêmica **Comparativo Crítico: .NET MAUI vs Android Nativo**, cujo objetivo é analisar a construção do mesmo aplicativo em duas plataformas e linguagens diferentes:  

- **.NET MAUI** (Visual Studio 2022 Community)  
- **Android Nativo** (Android Studio – Kotlin/Java)  

---

## 🎯 Objetivo  

Avaliar **desempenho, produtividade, realização de testes e experiência de desenvolvimento** ao desenvolver o mesmo conjunto de funcionalidades em duas plataformas diferentes.  

---

## 📂 Estrutura do Repositório  
<pre>  Comparativo_Critico/ 
          	│── /maui # Projeto .NET MAUI 
          	│── /android-nativo # Projeto Android (Kotlin/Java) 
</pre>

## 📌 Funcionalidades do “App Paridade”  

As mesmas features foram implementadas nas duas versões:  

1. **Navegação básica**: Lista → Detalhe (com voltar).  
2. **Lista filtrável**: 20 itens em JSON local, busca em tempo real.  
3. **Requisição HTTP**: consumo da consumo da [API IBGE](https://servicodados.ibge.gov.br/api/docs/).  
4. **Recurso nativo**: leitura de geolocalização.
5. **Tema**: suporte a **dark mode** e **light mode**.

## 🚀 Como Executar  

### 🔹 Projeto .NET MAUI  

1. Abra a pasta `/maui` no **Visual Studio 2022**.  
2. Execute `dotnet restore`.  
3. Selecione **Android Emulator (Pixel 6 / API 34) ou dispositivo móvel conectado**.  
4. Pressione `Ctrl + F5` para executar sem debug (ou F5 para rodar com debug).

### 🔹 Projeto Android Nativo  

1. Abra a pasta `/android-nativo` no **Android Studio**.  
2. Sincronize o Gradle.  
3. Selecione **Pixel 6 / API 34 ou dispositivo Android conectado**.  
4. Clique no botão Run ▶️ da barra superior `(atalho: Shift + F10)`.




