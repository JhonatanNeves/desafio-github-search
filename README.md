# Desafio GitHub Search
Este projeto é um aplicativo Android nativo desenvolvido como parte de um desafio. O objetivo principal do aplicativo é permitir que os usuários pesquisem por um nome de usuário do GitHub e visualizem uma lista de seus repositórios públicos.

## ✨ Funcionalidades

O aplicativo possui uma interface limpa e funcionalidades pensadas para uma ótima experiência do usuário:

  #### • Busca de Repositórios por Usuário:  
  - Digite qualquer nome de usuário válido do GitHub para buscar seus repositórios.  
  - A busca é realizada em tempo real através da API oficial do GitHub.

  #### • Visualização em Lista:  
  -  Os repositórios encontrados são exibidos em uma lista vertical, de forma clara e organizada, utilizando RecyclerView para um desempenho eficiente.  

  #### • Interatividade com os Repositórios:  
  - Abrir no Navegador: Clique em qualquer item da lista para abrir a página oficial daquele repositório no seu navegador padrão.  
  - Compartilhar: Cada item possui uma opção para compartilhar o link do repositório com outros aplicativos (WhatsApp, E-mail, etc.).  

  #### •Persistência de Dados:  
  - O aplicativo salva automaticamente o último nome de usuário pesquisado. Ao abrir o app novamente, o campo de busca já vem preenchido, facilitando novas buscas.  
  #### Design e Usabilidade:  
  - UI Customizada: O aplicativo conta com um tema personalizado, incluindo fontes e cores customizadas para uma identidade visual única.
  - Tratamento de Feedback: O usuário é informado com mensagens (Toast) em casos de usuário não encontrado ou falhas de conexão com a internet.
  - Esconder Teclado Automático: Ao confirmar uma busca, o teclado virtual se esconde automaticamente para não obstruir a visualização dos resultados.
  
    ## 🛠️ Tecnologias Utilizadas
    Este projeto foi construído utilizando as seguintes tecnologias e conceitos do ecossistema Android:
    #### •Linguagem: Kotlin
    #### •Arquitetura de UI: Baseada em Views com XML e ConstraintLayout para layouts responsivos.
    #### •Comunicação com API (Networking): Retrofit para realizar chamadas de rede à API do GitHub de forma simples e segura.
    #### •Parsing de JSON: Gson para converter as respostas da API em objetos Kotlin.
    #### •Listas Eficientes: RecyclerView para exibir a lista de repositórios, garantindo performance mesmo com muitos itens.
    #### •Persistência de Dados Simples: SharedPreferences para armazenar o último usuário pesquisado.
    #### •Componentes de UI: Material Components for Android para um design moderno e consistente.
    # 🚀 Como Executar o Projeto
    - 1.Clone este repositório:Kotlin  
      ``` git clone https://github.com/JhonatanNeves/desafio-github-search.git ```  
    - 2.Abra o projeto no Android Studio.
    - 3.Aguarde o Gradle sincronizar todas as dependências.
    - 4.Execute o aplicativo em um emulador ou dispositivo físico.
   
      <div aling="light">
        <img width="300px" alt="Screenshot_20251027_002040" src="https://github.com/user-attachments/assets/3f9d1189-b2d2-4505-ab10-e23bce689cde" />
        <img width="300px" alt="Screenshot_20251027_001756" src="https://github.com/user-attachments/assets/68db1d70-2eb2-42b2-af3e-3ca83e7635af" />
        </div>
