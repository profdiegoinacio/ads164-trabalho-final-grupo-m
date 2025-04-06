 Meu App - Projeto Final ADS164

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

O aplicativo tenta utilizar o Room para armazenamento local de dados.


**Foi utilizado AI para: corrigir erros, dar ideias de layout, e ajudar a construir o ROOM, além de ajudar adicionar métodos que eu desconhecia.*