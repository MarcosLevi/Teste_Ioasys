![N|Solid](logo_ioasys.png)

# README #

Estes documento README tem como objetivo fornecer as informações necessárias para a execução da aplicação, possíveis melhorias e o detalhamento das bibliotecas.

### Como usar ? ###

* Tela inicial
	* Você deve inserir o usuário e a senha corretamente.
	* Usuário de Teste: testeapple@ioasys.com.br
	* Senha de Teste : 12341234
	* Caso sejam inseridos os dados errados, um erro é apresentado e o login não é permitido
	* Caso sejam inseridos os dados corretos, é feito o login
	* Nos 2 casos, enquanto existe a espera da resposta do servidor, aparece uma tela de carregamento por cima da tela, que não deixa o usuário interagir com a tela enquanto a resposta do servidor não é recebida
	* É possível também esconder e mostrar a senha do usuário

* Tela Home
	* Nessa tela os cards das empresas serão mostrados
	* Quando o botão de pesquisar é clicado, aparece uma caixa de busca que espera 2 possíveis valores, um inteiro caso deseje pesquisar pelo numero do tipo da empresa ou uma string caso deseje buscar pelo nome da empresa
	* Depois de ter inserido o texto de busca e apertado no botão do teclado de pesquisar, a busca é feita e os cards aparecem na tela

* Tela Empresa
	* Ao clicar em um dos cards, uma tela detalhando a empresa é mostrada, apresentando a logo e a descrição da empresa
	* Ao clicar na seta de voltar, é possível retornar a tela Home


### Melhorias ###

* Com mais tempo eu poderia fazer ajustar o aplicativo para ficar de acordo com o Design Pattern
* Faria mais tratamentos de erros
* E criaria testes para cada rotina

### Bibliotecas utilizadas ###

* classpath "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0" para poder passar os argumentos de uma fragment para outra com o nav_graph
* implementation 'androidx.navigation:navigation-fragment-ktx:2.3.2' e implementation 'androidx.navigation:navigation-ui-ktx:2.3.2' para o uso do navigation graph
* implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0' e implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0' para o uso de livedata no viewModel
* implementation 'com.squareup.retrofit2:retrofit:2.5.0' e implementation 'com.squareup.retrofit2:converter-gson:2.5.0' para o acesso ao banco e retorno de seus valores
* implementation 'com.squareup.picasso:picasso:2.71828' para download de imagens colocando no ImageView
