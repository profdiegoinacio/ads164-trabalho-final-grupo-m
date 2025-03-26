package com.example.myapp.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
//import androidx.preference.contains
import com.example.myapp.R

object DataSource {

    val books: List<Book> = listOf(
        Book(
            id = "1",
            name = "Em águas profundas",
            author = "Patricia Highsmith",
            category = "Suspense",
            resume = "Vic e Melinda estão longe de ser um casal feliz ― seu casamento é mantido por um acordo nada convencional: " +
                    "Melinda pode ter quantos amantes quiser contanto que não arraste os dois e a filha para o caos de um divórcio. " +
                    "Tudo parece bem, mas, com o passar do tempo, Vic começa a se incomodar com os homens escolhidos pela esposa e " +
                    "adota uma estratégia inusitada para afugentá-los, assumindo a autoria do assassinato de um deles. Só que a " +
                    "notícia se espalha por toda a cidade do interior dos Estados Unidos e o antes cidadão-modelo, benfeitor, marido " +
                    "mais do que tolerante e empreendedor abnegado vira alvo da maledicência de todos.",
            imageResId = R.drawable.em_aguas_profundas
        ),
        Book(
            id = "2",
            name = "Manson: A Biografia",
            author = "Jeff Guinn",
            category = "True crime",
            resume = "Charles Manson fez de sua história a trilha sonora do fim do mundo. A metáfora favorita da América para o " +
                    "lado negro dá década de 1960, Manson foi o cabeludo que matou o sonho de Woodstock e o retrato perfeito de como " +
                    "toda aquela filosofia da geração paz e amor não funcionou.\n" +
                    "Psicopata, vigarista, racista e cafetão. Olhos em chamas, barba por fazer, cabelos despenteados e uma suástica " +
                    "tatuada na testa. A diabólica imagem de Charles Manson está gravada no inconsciente popular e é reconhecidamente " +
                    "assustadora. Após quatro décadas dos seus terríveis atos, os assassinatos orquestrados por ele continuam a exercer " +
                    "um mórbido fascínio. Dezenas de livros já foram escritos sobre Manson nesses mais de quarenta anos, e " +
                    "agora uma meticulosa pesquisa desenvolvida pelo biógrafo Jeff Guinn surge como o guia definitivo do homem que " +
                    "entrou para a história como sinônimo do mal.",
            imageResId = R.drawable.mason
        ),
        Book(
            id = "3",
            name = "Os sofrimentos do jovem Werther",
            author = "Johann Wolfgang Von Goethe",
            category = "Romance epistolar",
            resume = "Os sofrimentos do Jovem Werther é definido como um marco na literatura alemã e mundial. Escrito em 1774, foi " +
                    "uma das obras que mais influenciaram os jovens do período. Marcada por uma narração densa, lírica e essencialmente" +
                    " psicológica, a personagem atormentada de Werther tornou-se um modelo de herói pré-romântico. A obra relata a " +
                    "paixão devastadora de Werther pela bela Lotte, com tom confessional e intimista, por meio de cartas, a história " +
                    "é comovente.",
            imageResId = R.drawable.os_sofrimentos_do_jovem_werther
        ),
        Book(
            id = "4",
            name = "Ted Bundy: Um Estranho ao Meu Lado",
            author = "Ann Rule",
            category = "True Crime",
            resume = "Quando Ann Rule conheceu Ted Bundy em um centro de atendimento de prevenção ao suicídio, ela não fazia " +
                    "ideia de que aquele rapaz simpático e inteligente ― que sentava ao lado dela e de quem até chegou a receber " +
                    "um cartão de Natal ― se tornaria um dos serial killers mais proeminentes da história. Ted Bundy confessou ter " +
                    "matado ao menos 36 mulheres nos Estados Unidos durante os anos 1970. Para estudiosos do caso, a contagem final " +
                    "é ainda maior. Ele pode até ter salvado vidas pelo centro de prevenção, mas ceifou outras dezenas quando ninguém" +
                    " estava olhando. Inúmeras famílias ficaram sem respostas, e ele foi executado em 1989 na cadeira elétrica.",
            imageResId = R.drawable.ted_bundy),
        Book(
            id = "5",
            name = "A metamorfose",
            author = "Franz Kafka",
            category = "Romance",
            resume = "A metamorfose é a mais célebre novela de Franz Kafka e uma das mais importantes de toda a história da literatura." +
                    " Sem a menor cerimônia, o texto coloca o leitor diante de um caixeiro-viajante - o famoso Gregor Samsa - " +
                    "transformado em inseto monstruoso. A partir daí, a história é narrada com um realismo inesperado que associa o " +
                    "inverossímil e o senso de humor ao que é trágico, grotesco e cruel na condição humana - tudo no estilo " +
                    "transparente e perfeito desse mestre inconfundível da ficção universal.",
            imageResId = R.drawable.metamorfose),
        Book(
            id = "6",
            name = "Os abismos",
            author = "Pilar Quintana",
            category = "Ficção Literária",
            resume = "Claudia mora com os pais em Cáli, na Colômbia, em um apartamento tomado por plantas e rodeado por precipícios " +
                    "físicos e metafóricos. O ambiente, exuberante e bem-cuidado, é um contraste, uma oposição à mãe indiferente " +
                    "que está em conflito com os caminhos escolhidos e impostos para a própria vida. Como muitas famílias, a de " +
                    "Claudia passa por uma crise, e basta o casamento de seus pais estremecer para que ela comece a entender a " +
                    "fragilidade dos limites que mantêm a previsibilidade do cotidiano.\n" +
                    "\n" +
                    "A partir da expectativa e de seu olhar atento e ao mesmo tempo inocente de criança, é a menina que narra os " +
                    "acontecimentos que abriram as fendas por onde entraram seus piores medos, aqueles que são irreversíveis e " +
                    "podem levar à beira dos abismos. Nos últimos anos da infância, Claudia começa a entender que a existência chega " +
                    "ao fim e que essa ruptura pode ser uma escolha pessoal. É pelos relatos da mãe, obcecada por revistas de " +
                    "celebridades ― e em especial pelas figuras femininas com finais trágicos, como Grace Kelly ―, que ela faz a " +
                    "correspondência entre a morte e as escolhas e passa a temer pelas decisões de sua progenitora.\n" +
                    "\n" +
                    "Em meio à beleza e à violência da natureza, confrontando desejos inconfessos, criança e mãe se encontram e, " +
                    "assim, o destino da mais velha parece se debruçar sobre o abismo contra o qual tantas outras mulheres também " +
                    "já se depararam.",
            imageResId = R.drawable.os_abismos),
        Book(
            id = "7",
            name = "As outras pessoas",
            author = "C. J. Tudor",
            category = "Suspense",
            resume = "Gabe Forman está preso em um engarrafamento, atrasado para o jantar em casa com a família, quando o rosto " +
                    "de sua filha, Izzy, de cinco anos, aparece no vidro traseiro do carro à frente e balbucia “papai”. " +
                    "Três anos depois, Gabe passa seus dias e noites rodando pela estrada em que viu Izzy pela última vez.\n" +
                    "\n" +
                    "Garçonete em um dos muitos postos de gasolina por onde Gabe passa à procura da filha, Katie é solidária " +
                    "ao “homem magro”, apelido que deu a ele. Ela sabe o que é perder alguém. Há nove anos, sua família " +
                    "ficou destruída depois que seu pai foi assassinado.\n" +
                    "\n" +
                    "Fran também vive na estrada com Alice. Mas elas não estão à procura de ninguém; estão fugindo, porque " +
                    "Fran sabe que, se um dia as encontrarem, elas serão mortas.\n" +
                    "\n" +
                    "Todas essas histórias têm algo em comum: pessoas que passaram por acontecimentos terríveis que não conseguem " +
                    "deixar para trás. Elas não vão encontrar a paz no perdão ou no esquecimento. Elas só vão encontrar a paz se " +
                    "fizerem justiça.\n" +
                    "\n" +
                    "Da mesma autora do best-seller O Homem de Giz, um thriller emocionante, cuja narrativa, repleta de ameaça e " +
                    "ação, captura o leitor desde o o início e explora com habilidade a natureza da justiça e a força do luto",
            imageResId = R.drawable.as_outras_pessoas),
        Book(
            id = "8",
            name = "A guerra dos mundos",
            author = "H.G. Wells",
            category = "Ficção científica",
            resume = "Quando um exército de invasores marcianos chega à Inglaterra, o pânico e o terror tomam conta da população. " +
                    "Enquanto os alienígenas atravessam o país em enormes máquinas de três pernas, incinerando tudo em seu caminho " +
                    "com um raio de calor e espalhando gases tóxicos nocivos, os moradores da Terra devem chegar a um acordo com a" +
                    " perspectiva do fim da civilização humana e o início da era marciana.",
            imageResId = R.drawable.a_guerra_dos_mundos),
        Book(
            id = "9",
            name = "Six of crows: Sangue e mentiras",
            author = "Leigh Bardugo",
            category = "Fantasia",
            resume = "A OESTE DE RAVKA, ONDE GRISHAS SÃO ESCRAVIZADOS E ENVOLVIDOS EM JOGOS DE CONTRABANDISTAS E MERCADORES... " +
                    "fica Ketterdam, capital de Kerch, um lugar agitado onde tudo pode ser conseguido pelo preço certo. Nas ruas " +
                    "e nos becos que fervilham de traições, mercadorias ilegais e assuntos escusos entre gangues, ninguém é melhor " +
                    "negociador que Kaz Brekker, a trapaça em pessoa e o dono do Clube do Corvo.Por isso, Kaz é contratado para " +
                    "liderar um assalto improvável e evitar que uma terrível droga caia em mãos erradas, o que poderia instaurar um " +
                    "caos devastador. Apenas dois desfechos são possíveis para esse roubo: uma morte dolorosa ou uma fortuna muito " +
                    "maior que todos os seus sonhos de riqueza.Apostando a própria vida, o dono do Clube do Corvo monta a sua equipe " +
                    "de elite para a missão: a espiã conhecida como Espectro; um fugitivo perito em explosivos e com um misterioso " +
                    "passado de privilégios; um atirador viciado em jogos de azar; uma grisha sangradora que está muito longe de " +
                    "casa; e um prisioneiro que quer se vingar do amor de sua vida.O destino do mundo está nas mãos de seis foras " +
                    "da lei – isso se eles sobreviverem uns aos outros.",
            imageResId = R.drawable.six_of_crows),
        Book(
            id = "10",
            name = "Meu filho Dahmer",
            author = "Lionel Dahmer",
            category = "True crime",
            resume = "Neste controverso livro de memórias, o pai de Jeffrey Dahmer busca entender a partir do seu ponto de vista " +
                    "a origem da verdadeira fome que habitava os olhos e a mente de seu filho",
            imageResId = R.drawable.meu_filho_dahmer),
    )
    var favoriteBooks: SnapshotStateList<Book> = mutableStateListOf()
    //private val wantToReadBooks = mutableListOf<Book>()
    var wantToReadBooks: SnapshotStateList<Book> = mutableStateListOf()
    var finishedReadingBooks: SnapshotStateList<Book> = mutableStateListOf()

    fun addToFavorites(book: Book) {
        if (!favoriteBooks.contains(book)) {
            favoriteBooks.add(book)
        }
    }
    fun removeFromFavorites(book: Book) {
        favoriteBooks.remove(book)
    }
    fun isFavorite(book: Book): Boolean {
        return favoriteBooks.contains(book)
    }
    //
    fun addToWantToRead(book: Book) {
        if (!wantToReadBooks.contains(book)) {
            wantToReadBooks.add(book)
        }
    }
    fun removeFromWantToRead(book: Book) {
        wantToReadBooks.remove(book)
    }
    fun isWantToRead(book: Book): Boolean {
        return wantToReadBooks.contains(book)
    }
//
    fun addToFinishedReading(book: Book) {
        if (!finishedReadingBooks.contains(book)) {
            finishedReadingBooks.add(book)
        }
    }
    fun removeFinishedReading(book: Book) {
        finishedReadingBooks.remove(book)
    }
    fun isFinishedReading(book: Book): Boolean {
        return finishedReadingBooks.contains(book)
    }

    fun getBookById(bookId: String): Book {
        return books.first { it.id == bookId }
    }
    val user = User(
        name = "User",
        imageResId = R.drawable.user,
        favoriteBooks = favoriteBooks
    )
}