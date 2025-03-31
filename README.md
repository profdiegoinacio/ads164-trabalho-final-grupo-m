# Meu App - Projeto Final ADS164

Este é o projeto final do curso ADS164, desenvolvido pelo Grupo M. Trata-se de um aplicativo Android construído com Kotlin, utilizando Jetpack Compose para a interface do usuário, Room para persistência de dados local e, provavelmente, seguindo o padrão de arquitetura MVVM.

## Funcionalidades

Este aplicativo permite que os usuários naveguem e gerenciem uma biblioteca de livros.

Principais funcionalidades incluem:

    *   Navegar por uma lista de livros.
    *   Visualizar detalhes de livros individuais.
    *   Marcar livros como favoritos.
    *   Marcar livros como Quero ler.
    *   Marcar livros como Lidos.
    *   Pesquisar por livros.

## Tecnologias Utilizadas

*   **Kotlin:** A principal linguagem de programação.
*   **Jetpack Compose:** Ferramenta moderna declarativa para construção da interface do usuário.
*   **Room:** Biblioteca de persistência para gerenciamento de banco de dados local (SQLite).
*   **MVVM (Model-View-ViewModel):** Padrão arquitetural para separar a lógica da interface do usuário do gerenciamento de dados.
*   **Coroutines e Flow:** Para manipulação de operações assíncronas e fluxos de dados.

## Estrutura do Projeto

O projeto segue a estrutura padrão de um aplicativo Android:

*   `app/src/main/kotlin/com/example/myapp`: Contém o código-fonte Kotlin.
    *   `data`: Responsável pelo acesso e gerenciamento de dados.
        *   `model`: Classes de dados que representam entidades do banco de dados (ex.: `Book`).
        *   `local`: Gerencia o banco de dados local Room (`AppDatabase`, `BookDao`).
        *   `repository`: Fornece uma camada de abstração para operações de dados (`BookRepository`).
    *   `ui` ou `screens`: Composables do Jetpack Compose, organizados por tela ou funcionalidade.
    *   `viewmodel`: ViewModels que gerenciam os dados relacionados à UI e interagem com o repositório.
    *   `[Outros pacotes conforme necessário para as funcionalidades do seu app.]`
*   `app/src/main/res`: Contém recursos não relacionados a código, como layouts, strings, estilos, imagens, etc.
*   `app/src/AndroidManifest.xml`: Declara metadados do aplicativo, atividades, permissões, etc.
*   `app/build.gradle.kts`: Define as dependências do projeto e configurações de build.

## Como Começar

1.  **Clone o repositório:**
2.  **Abra no Android Studio:** Importe o projeto no Android Studio.
3.  **Compile o projeto:** Sincronize os arquivos do Gradle e compile o projeto (`Build > Make Project` ou utilize a barra do Gradle).
4.  **Execute o aplicativo:** Conecte um dispositivo Android ou emulador e execute o app (`Run > Run 'app'`).

## Banco de Dados

O aplicativo utiliza o Room para armazenamento local de dados. O esquema do banco de dados é definido pela entidade `Book`, localizada em `app/src/main/kotlin/com/example/myapp/data/model/Book.kt`. Caso faça alterações na entidade, lembre-se de incrementar a versão do banco de dados em `AppDatabase.kt` e fornecer uma estratégia de migração (se for necessário preservar os dados existentes dos usuários). [Consulte a documentação do Room para orientações sobre migração de banco de dados.]

## Arquitetura

O aplicativo segue o padrão arquitetural MVVM:

*   **Model:** Representa a camada de dados, incluindo classes de dados (entidades), DAOs, repositórios e, possivelmente, código relacionado à rede.
*   **View:** A camada de interface do usuário, construída com Jetpack Compose, responsável por exibir os dados e lidar com interações do usuário.
*   **ViewModel:** Atua como intermediário entre a View e o Model, preparando os dados para a UI e lidando com a entrada do usuário.

O ViewModel observa os dados do repositório (frequentemente usando Kotlin Flows) e os expõe para a View através de `StateFlow` ou outras estruturas reativas. A View observa esse estado e se recompõe automaticamente quando os dados mudam. Interações do usuário na View acionam métodos no ViewModel, que por sua vez interage com o repositório para atualizar os dados subjacentes.

## Contribuindo

[Adicione diretrizes de contribuição caso deseje aceitar colaborações, ou remova esta seção se o projeto for fechado.]

## Licença

[Especifique aqui a licença do projeto, como MIT, Apache 2.0, ou "Todos os direitos reservados" caso não seja um projeto de código aberto.]
