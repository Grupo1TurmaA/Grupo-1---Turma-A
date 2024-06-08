import javax.swing.*; //Importa as classes para criar interfaces gráficas
import java.awt.*; //Importa classes para gerenciar componentes gráficos
import java.awt.event.ActionEvent; //Importa classes para gerenciar eventos de ação
import java.awt.event.ActionListener; //Importa a interface para gerenciar ações de eventos
import java.util.ArrayList; //Importa a classe ArrayList para listas dinâmicas
import java.util.Random; //Importa a classe Random para gerar números aleatórios
import java.io.IOException; //Importa a classe IOException para gerenciar erros de I/O
import javax.sound.sampled.*; // Importa classes para tocar sons
import java.io.File; //Importa a classe File para manipulação de arquivos

/* Projeto Integrador - Desenvolvimento de lógica */
/* Grupo 1 */
/* Integrantes: Edward Leal, Gabriel Santos, Yasmin Oliveira e Mayara Lupeti */

public class Main {
    //OBS: Com o JOptionPane, cada vez que nos referimos à uma mensagem, significa que uma janela com texto e apenas um botão de "ok"
    //Declaração de componentes da interface gráfica como campos estáticos para acesso em outros métodos
    static JTextField playerNameField;
    static ArrayList<String> playerNames;
    static JLabel foodLabel;
    static JLabel lifeLabel;
    static JLabel coinCounter;

    public static void main(String[] args) {
        //Inicializa variáveis do jogo
        int coin = 0;
        int food = 100;
        int life = 100;

        //Cria a janela principal do jogo
        JFrame frame = new JFrame("Velas do Destino: A Odisséia do Corsário");
        frame.setSize(600, 500);

        //Cria um painel com um fundo personalizado
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\gabriel.rsantos63\\Documents\\Grupo-1---Turma-A\\itens menu\\IMG_1347.PNG");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        frame.add(panel);

        //Botão para mostrar as instruções do jogo
        JButton instructionsButton = new JButton("Instruções");
        instructionsButton.setBackground(Color.gray);
        instructionsButton.setBounds(100, 400, 100, 30);
        panel.add(instructionsButton);

        //Botão para mostrar as instruções do jogo
        JButton startButton = new JButton("Jogar");
        startButton.setBackground(Color.gray);
        startButton.setBounds(250, 400, 100, 30);
        panel.add(startButton);

        //Botão para fechar o jogo
        JButton closeButton = new JButton("Sair");
        closeButton.setBackground(Color.gray);
        closeButton.setBounds(400, 400, 100, 30);
        panel.add(closeButton);

        //Botão para mostrar os créditos do jogo
        JButton creditsButton = new JButton("Créditos");
        creditsButton.setBackground(Color.gray);
        creditsButton.setBounds(470, 10, 100, 30);
        panel.add(creditsButton);

        //Campo de texto para inserir o nome do jogador
        playerNameField = new JTextField();
        playerNameField.setBounds(200, 350, 200, 30);
        panel.add(playerNameField);

        //Rótulo para mostrar a contagem de moedas
        coinCounter = new JLabel("Moedas: 0");
        coinCounter.setForeground(Color.YELLOW);
        coinCounter.setBounds(10, 10, 100, 20);
        panel.add(coinCounter);

        //Rótulo para mostrar a quantidade de comida
        foodLabel = new JLabel("Comida: 100");
        foodLabel.setForeground(Color.red);
        foodLabel.setBounds(10, 30, 100, 20);
        panel.add(foodLabel);

        //Rótulo para mostrar a quantidade de vida
        lifeLabel = new JLabel("Vida: 100");
        lifeLabel.setForeground(Color.green);
        lifeLabel.setBounds(10, 50, 100, 20);
        panel.add(lifeLabel);

        //Inicializa a lista de nomes de jogadores
        playerNames = new ArrayList<>();

        //Adiciona ação ao botão de créditos
        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Mostra uma mensagem com os créditos do jogo
                JOptionPane.showMessageDialog(null, "Desenvolvido por: \nEdward Leal Borges, \nGabriel Rodrigues dos Santos, \nYasmim Oliveira Soares, \nIsabela Miranda Pereira, \nMayara Lupeti Turbiani \n\nRoteirizado por: \nIsabela Miranda Pereira, \nEdward Leal Borges, \nYasmim Oliveira Soares");
            }
        });

        //Adiciona ação ao botão de instruções
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Mostra uma mensagem com as instruções do jogo
                JOptionPane.showMessageDialog(null, "Esse é um jogo de RPG, ou em inglês, Role-Playing Game, nessa modalidade os jogadores assumem papéis fictícios e interagem em um mundo imaginário. "
                        + "\nEm Velas do Destino: A Odisséia do Corsário, você assume o papel de um marujo que foi exilado por seu capitão e tripulantes antigos, "
                        + "\numa oportunidade surge para embarcar numa aventura com uma capitã famosa e ambiciosa, \nem um novo navio e com "
                        + "\numa tripulação VOCÊ decide o seu destino!"
                        + "\n\n\nNesse jogo suas escolhas IMPORTAM, você inicia essa jornada com 100 de vida e comida e 0 moedas! Pense antes de tomar cada decisão durante "
                        + "\nas missões, pois dependendo do caminho escolhido você pode ganhar ou perder moedas, comida e vida.");
            }
        });

        // Adiciona ação ao botão de sair
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Mostra uma mensagem de confirmação para sair do jogo
                int escolha = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair?", "Sair",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim\n", "Não"}, null);
                switch(escolha) {
                    case JOptionPane.YES_OPTION:
                        frame.dispose();
                        System.exit(0);
                        break;//Fecha a janela
                    case JOptionPane.NO_OPTION:
                        break; //Não faz nada se a opção for "Não"
                }
            }
        });

        //Adiciona ação ao botão de jogar
        startButton.addActionListener(new ActionListener() {
            final int[] coins = {0}; //Variável para contagem de moedas
            final int[] foods = {0}; //Variável para contagem de comida
            @Override
            public void actionPerformed(ActionEvent e) {
                //Obtém o nome do jogador do campo de texto
                String playerName = playerNameField.getText();
                if (playerName.isEmpty()) {
                    //Mostra mensagem se o campo estiver vazio
                    JOptionPane.showMessageDialog(frame, "Por favor, insira um nome de usuário: ");
                    return;
                }
                if (playerNames.contains(playerName)) {
                    //Mostra mensagem se o nome já existir na lista
                    JOptionPane.showMessageDialog(frame, "Este nome de usuário já existe, por favor, escolha outro.");
                } else {
                    //Adiciona o nome à lista de jogadores e mostra mensagem de boas-vindas
                    playerNames.add(playerName);
                    JOptionPane.showMessageDialog(frame, "Bem-vindo, marujo! Hoje será seu dia à caçada ao tesouro, " + playerName + "!");
                }
                //Pergunta ao jogador se ele está pronto para começar
                int opcao = JOptionPane.showConfirmDialog(frame, "Você está pronto para começar? A Sarah irá te ajudar nessa nova jornada épica, " + playerName + "!");
                if (opcao != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Opção cancelada. Retornando ao menu");
                    return;
                }
                //Mostra a introdução do jogo
                JOptionPane.showMessageDialog(frame, "Narrador: Você está preso na cela do pirata Barba Negra, pois você é um amotinado que seguiu contra as regras do seu capitão."
                        + "\nMas de repente você ouviu um barulho estranho, e quando menos percebe, escuta um tiro de canhão atingir o navio.");
                // Chama métodos que contêm o restante da história e lógica do jogo
                TEXTOS(playerName, coinCounter, foodLabel);
                TEXTOS2(playerName, coinCounter);
                TEXTOS3(playerName, coinCounter);
                TEXTOS4(playerName, coinCounter);
                TEXTOS5(playerName, coinCounter);
                TEXTOS6(playerName, coinCounter);
                TEXTOS7(playerName, coinCounter);
                TEXTOS8(playerName, coinCounter);

                //Atualiza a contagem de moedas e comida
                coins[0] = 50;
                coinCounter.setText("Moedas: " + coins[0]);
                foods[0] = 80;
                foodLabel.setText("Comida: " + foods[0]);
            }
        });

        frame.setVisible(true);

        //Toca a música tema do jogo de fundo
        playSound("C:\\Users\\gabriel.rsantos63\\Documents\\Grupo-1---Turma-A\\itens menu\\Pirates of the Caribbean (Auckland Symphony Orchestra) 1080p.wav");
    }

    //Método para tocar um som a partir de um arquivo
    public static void playSound(String filePath) {
        try {
            // Carrega o arquivo de áudio
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Obtém um clip de áudio e o abre com o stream de áudio carregado
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Loop para tocar a música repetidamente
            while (true) {
                clip.start();
                // Espera até a música acabar
                while (!clip.isRunning()) {
                    Thread.sleep(100); // Espera um pouco antes de verificar novamente
                }
                while (clip.isRunning()) {
                    Thread.sleep(100); // Espera até que a música termine
                }
                // Reinicia a música
                clip.setFramePosition(0);
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException e) {
            // Captura e imprime qualquer exceção que ocorra durante a reprodução do som
            e.printStackTrace();
        }
    }


    //Método para mostrar uma sequência de diálogos da FASE 1
    public static void TEXTOS(String playerName, JLabel coinCounter, JLabel foodLabel) {
        Random rand = new Random(1); // Cria um objeto Random para possíveis usos
        if (playerNames.contains(playerName))
            //Mensagem inicial sobre a fuga do jogador
            JOptionPane.showMessageDialog(null, "Narrador: Você fica assustado pois poderia ter morrido, mas ao mesmo tempo agradece ao perceber "
                    + "\nque a bala de canhão atingiu uma parte da cela do navio, e agora você poderá fugir.");
        //Série de mensagens para avançar a história do jogo
        JOptionPane.showMessageDialog(null, "Narrador: Assim que você sai do porão do navio, você percebe que ele esta sendo invadido por Sarah Fortune, a capitã do Pérola Negra! "
                + "\nE agora você finalmente terá a chance de começar do zero e se vingar, podendo administrar sua própria vida. ");
        JOptionPane.showMessageDialog(null, "Narrador: Enquanto o Navio está sendo atacado, você consegue uma espada e começa a lutar contra sua antiga tripulação, "
                + "\ndeixando claro qual lado você está. E é assim que a história do maior pirata dos sete mares começa... ");
        JOptionPane.showMessageDialog(null, "Narrador: Depois de todo o conflito, você se aliou a Capitã Fortune e agora faz parte da tripulação... Neste momento você "
                + "\nestá indo para uma caça ao tesouro, e todos os outros piratas estão se preparando "
                + "\npara a busca do ouro enquanto seguem as ordens da Capitã.");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Se apresentem, Marujos! \nHoje temos uma missão mais tranquila, mas não menos importante!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Hoje eu irei escolher um de vocês para capturar algo muito importante... Algo valioso!");
        JOptionPane.showMessageDialog(null, "Narrador: A capitã olha para todos os tripulantes do navio, mas seus olhos pousam sobre você. ");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Aha! É você mesmo que eu estava procurando, " + playerName);
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Você precisa capturar um tesouro pra mim! MAS... não é qualquer tesouro, marujo... "
                + "\nÉ um baú cheio de joias e ouro!!!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Você terá que enfrentar um tubarão... Mas pelo o que eu ja ouvi de você, "
                + "\nisso será moleza... De qualquer modo, eu não estou te dando escolha! "
                + "\nSó tome cuidado. Eu odiaria perder minha fortuna por um erro seu... ");

        //Abre uma janela para apresentar as duas opções de escolha para o jogador
        int escolha = JOptionPane.showOptionDialog(null, "Como você prefere enfrentar o tubarão?", "Escolha sua ação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Atacar o tubarão", "Tentar modo furtivo"}, null);
        if (escolha == JOptionPane.YES_OPTION) {
            //Caso o jogador escolha atacar o tubarão
            JOptionPane.showMessageDialog(null, "Você decide atacar o tubarão! Corajoso, " + playerName + "!");

            //Atualiza o contador de moedas
            int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
            int totalCoins = currentCoins + 50;
            coinCounter.setText("Moedas: " + totalCoins);
        } else {
            //Caso o jogador escolha o modo furtivo
            JOptionPane.showMessageDialog(null, "Você decide tentar modo furtivo para passar pelo tubarão distraindo ele com pote de comida velha.");
            //Chama o método para a ação furtiva
            modoFurtivo(playerName, coinCounter, foodLabel);
        }
    }

    // Método que apresenta uma sequência de diálogos da continuação da FASE 1
    public static void TEXTOS2(String playerName, JLabel coinCounter) {
        Random rand = new Random(1); // Cria um objeto Random para possíveis usos

        //Se o nome do jogador está na lista de nomes de jogadores
        if (playerNames.contains(playerName))
            //Mostra a continuação do diálogo depois da escolha do jogador dna FASE 1
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Muito bom marujo. Não imaginava que você conseguiria equilibrar as situações... "
                    + "\nSe você continuar desse jeito, terei que promover você para um cargo mais importante... ");
        //Mostra uma mensagem onde a capitão recompensa o jogador pela última escolha (recompensas e consequências diferente para cada escolha)
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Te darei uma pequena recompensa por ter resgatado nosso tesouro perdido! Mas não saia "
                + "\ngastando por ai, ouro é uma coisa muito valiosa, " + playerName + ". Administre muito bem...");
    }

    //Método que define a ação e lógica para o modo furtivo do final da FASE 1
    public static void modoFurtivo(String playerName, JLabel coinCounter, JLabel foodLabel) {
        int coinsEarned = 50; //Quantidade de moedas ganhas no modo furtivo
        int foodLost = 20; //Quantidade de comida perdida no modo furtivo

        //Atualiza a quantidade de comida no mostrador do menu do jogo
        int currentFood = Integer.parseInt(foodLabel.getText().split(": ")[1]);
        int totalFood = currentFood - foodLost;
        foodLabel.setText("Comida: " + totalFood);

        //Mostra uma mensagem indicando o ganho de moedas e a perda de comida
        JOptionPane.showMessageDialog(null, "Você está no modo furtivo. Ganhou " + coinsEarned + " moedas, mas perdeu " + foodLost + " de comida.");

        //Atualiza a quantidade de comida no mostrador do menu do jogo
        int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
        int totalCoins = currentCoins + coinsEarned;
        coinCounter.setText("Moedas: " + totalCoins);
    }


    //Método que apresenta uma sequência de diálogos referentes a FASE 2
    public static void TEXTOS3(String playerName, JLabel coinCounter) {
        Random rand = new Random(1); //Cria um objeto Random para possíveis usos

        //Mensagens que definem os diálogos marcando o início da fase
        JOptionPane.showMessageDialog(null, "Narrador: Depois da sua primeira missão, todos no navio te prestigiaram, te tornando o queridinho da capitã...");
        JOptionPane.showMessageDialog(null, "Narrador: Após alguns dias a Capitã foi convidada para Tortuga, pelo pirata Barbosa, chegando lá vocês descobrem uma "
                + "\nnova expedição sendo feita pela Coroa Britânica, cheia de joias, rum, especiarias "
                + "\ne ouro para ser saqueado!");
        JOptionPane.showMessageDialog(null, "Narrador: Sarah e Barbosa se encontram em uma taverna junto com sua tripulação reunida "
                + "\npara conversarem sobre negócios...");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Devo dizer que fiquei surpresa com seu convite... "
                + "\n Depois da párola achei que nunca mais daria as caras por aqui...");
        JOptionPane.showMessageDialog(null, "Barbosa: Ah por favor, não vamos entrar nesse assunto, eu a convidei amigavelmente para um jantar de negócios!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Negócios é? E de que tipo...?");
        JOptionPane.showMessageDialog(null, "Barbosa: Grandes negócios minha cara Sarah Fortune... muito tesouro, rum, joias e "
                + "\nespeciarias confiscadas pela coroa real...");
        JOptionPane.showMessageDialog(null, "Barbosa: Muito mais do que você já ganhou ou imaginou ter, e então? Está de acordo? "
                + "\nPodemos nos unir, eu te garanto que a nossa tripulação vai adorar a ideia!!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Mas é óbvio, se envolve dinheiro é claro que vou aceitar, "
                + "\num dia Pérola Negra será feito de ouro!");
        JOptionPane.showMessageDialog(null, "Narrador: Sarah Fortune aceita a missão em Tortuga. Você junto ao resto da tripulação e a capitã, "
                + "\nse preparam pensando em um plano para atacar os britânicos. ");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Ok homens, eu quero todos em meu convés! Barbosa, me empreste alguns de seus homens. "
                + "\nVamos expulsar esses carapaças daqui!");
        JOptionPane.showMessageDialog(null, "Barbosa: Ficou louca? não deixarei meus homens morrerem pela sua investida burra e precipitada de lutar frente "
                + "\na frente com os britânicos! Está vendo a sua direita? tem umas rochas cobrindo a visão deles. "
                + "\nVamos encurála-los pelas costas e trucidá-los!");
        JOptionPane.showMessageDialog(null, playerName + ": Eu tenho uma ideia, sei que sou novo ainda, mas tenho experiencia em batalha, posso dizer "
                + "\ncom confiança que eu poderei os ajudar com isso. Minha tripulação será conhecida por afundar a "
                + "\nterrivel coroa britânica no fundo do mar! ");
        JOptionPane.showMessageDialog(null, "Narrador: O destino dessa missão está novamente em suas mãos você agora deve usar seu modo tático, "
                + "\npense em em como atacar, analise bem antes da implementação da estrategia!");

        //Abre uma janela para apresentar as duas opções de escolha para o jogador
        int escolha = JOptionPane.showOptionDialog(null, "Como você irá resolver essa situação?", "Escolha sua ação: ",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Atacar, a coroa britânica está com bens que não os pertencem", "Seguir no modo furtivo, priorizando a captura do que foi apreendido."}, null);

        if (escolha == JOptionPane.YES_OPTION) {
            //Se o jogador escolhe atacar
            int lifeLost = 30; //Quantidade de vida perdida no ataque
            int foodLost = 10; //Quantidade de comida perdida no ataque

            //Atualiza a quantidade de vida no mostrador do menu do jogo
            int currentLife = Integer.parseInt(lifeLabel.getText().split(": ")[1]);
            int totalLife = currentLife - lifeLost;
            lifeLabel.setText("Vida: " + totalLife);

            //Atualiza a quantidade de comida no mostrador do menu do jogo
            int currentFood = Integer.parseInt(foodLabel.getText().split(": ")[1]);
            int totalFood = currentFood - foodLost;
            foodLabel.setText("Comida: " + totalFood);

            //Mostra uma mensagem indicando as perdas sofridas no ataque
            JOptionPane.showMessageDialog(null, "Vocês decidem atacar, porém perdem " + lifeLost + " de vida e " + foodLost + " de comida. Não foi a melhor opção. "
                    + "\nPense melhor na próxima missão, quem sabe você ganha uma parte da recompensa, " + playerName + "!");
        } else {
            //Se o jogador escolhe o modo furtivo
            int coinsEarned = 100; //Quantidade de moedas ganhas no modo furtivo
            int foodEarned = 10; //Quantidade de comida ganha no modo furtivo

            //Atualiza a quantidade de moedas no mostrador do menu do jogo
            int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
            int totalCoins = currentCoins + coinsEarned;
            coinCounter.setText("Moedas: " + totalCoins);

            //Atualiza a quantidade de comida no mostrador do menu do jogo
            int currentFood = Integer.parseInt(foodLabel.getText().split(": ")[1]);
            int totalFood = currentFood + foodEarned;
            foodLabel.setText("Comida: " + totalFood);

            //Mostra uma mensagem indicando as recompensas obtidas no modo furtivo
            JOptionPane.showMessageDialog(null, "Vocês escolhem se infiltrar na expedição e conseguem roubar 70% do ouro e comida, "
                    + "\ne você recebeu uma parte!");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Por respeito a você ter me dado um báu cheio de ouro...");
        }
    }

    //Método que apresenta uma sequência de diálogos referentes a FASE 3
    public static void TEXTOS4(String playerName, JLabel coinCounter) {
        Random rand = new Random(1); //Cria um objeto Random para possíveis usos futuros

        //Mensagens que definem os diálogos marcando o início da fase
        JOptionPane.showMessageDialog(null, "Narrador: Depois da captura dos tesouros, a tripulação passa por um momento de conflito. Agora nas "
                + "\nmãos da nossa capitã estavam uma quantidade estrondosa de tesouros e moedas de ouro.");
        JOptionPane.showMessageDialog(null, "Narrador: Os tripulantes se revoltam pela repartição injusta dos tesouros. Todos trabalham e "
                + "\nse esforçam pelo sucesso do navio e da capitã, sendo assim devem ser reconhecidos e "
                + "\npagos pela produtividade.");
        JOptionPane.showMessageDialog(null, "Narrador: O vislumbre atacou a capitã, e a distribuição das recompensas entre os tripulantes "
                + "\nnão os agrada muito.");
        JOptionPane.showMessageDialog(null, "Narrador: Você percebe que há um conflito, pois já que todos se esforçam pelo "
                + "\nnavio da capitã, todos  devem ser reconpensados de forma justa. No entanto, "
                + "\nisso não tem acontecido direito...");
        JOptionPane.showMessageDialog(null, "Tripulante 1: Capitã, não acho que esteja sendo justa com a nossa recompensa!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Oh marujo, ao meu ver isto é mais que o suficiente para vocês. Se não fosse por mim, "
                + "\nao menos iriam saber a localização da coroa.");
        JOptionPane.showMessageDialog(null, "Tripulante 2: Está sendo injusta, capitã! estamos com você a anos, esta mudada, não costumava "
                + "\nser tão mesquinha desta forma!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Eu sou a ponta deste navio. EU quem mando aqui, e vocês não seriam nada sem mim! "
                + "\nSe contentem com o que tem, se não os faço andar na prancha!");
        JOptionPane.showMessageDialog(null, "Narrador: Você reconhece a mesma atitude de Barba Negra nos olhos da capitã, temia que aquele "
                + "\nnavio virasse novamente a mesma prisão de que havia saido. Sendo assim, você decide "
                + "\nfazer algo por você e pelos outros tripulantes do navio...");

        //Abre uma janela para aapresentar as duas opções de escolha para o jogador
        int escolha = JOptionPane.showOptionDialog(null, "Como você prefere resolver essa situação?", "Escolha sua ação: ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Conversar com a capitã de forma pacífica", "Gritar com a capitã na frente de todos"}, null);
        if (escolha == JOptionPane.YES_OPTION) {
            //Mostra essas mensagens se o jogador escolhe conversar de forma pacífica
            JOptionPane.showMessageDialog(null, "Você decide conversar com a capitã de forma pacífica.");
            JOptionPane.showMessageDialog(null, playerName + ": Escute capitã, nós somos seus companheiros, eles não querem te arruinar, estão todos do seu "
                    + "\nlado. os remunere como deve, pois você sabe que merecem.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Quem você pensa que é " + playerName + "? Eu sei bem o que vocês merecem. Não sei se devo te lembrar "
                    + "\nmas eu sou a capitã do navio!");
            JOptionPane.showMessageDialog(null, playerName + ": Sabe que eles acabariam com você, não sabe? só não fazem isso porque são como cães fieis a você. "
                    + "\nEntregue a eles o que pediram a você!");
            JOptionPane.showMessageDialog(null, "Narrador: Sarah te ouviu e distribuiu mais 300 moedas entre os tripulantes incluindo você.");
            JOptionPane.showMessageDialog(null, "Narrador: Parabens marujo, você conseguiu usar o mecanismo de defesa da racionalização, adquirindo os recursos "
                    + "\nnecessários para o sustento da tripulação e o enriquecimento do capitão.");

            //Atualiza a quantidade de moedas no mostrador do menu do jogo
            int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
            int totalCoins = currentCoins + 300;
            coinCounter.setText("Moedas: " + totalCoins);
        } else {
            //Mostra essas mensagens se o jogador escolhe gritar com a capitã
            JOptionPane.showMessageDialog(null, "Você decide gritar com a capitã na frente de todos!");
            JOptionPane.showMessageDialog(null, playerName + ": PAGUE LOGO O QUE NOS DEVE CAPITÃ, NÃO ESTAMOS AQUI PARA FAZER FAVOR!");
            JOptionPane.showMessageDialog(null, "Tripulante 1: Nos arriscamos por todos capitã e ainda perdemos alguns homens, apenas "
                    + "\nqueremos nosso dinheiro! Se não a coisa vai ficar seria!");
            JOptionPane.showMessageDialog(null, "Narrador: Os tripulantes começam a gritar em uníssono, cobrando a capitã.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Tudo bem! Tudo bem! Parem com essa gritaria, seus animais! Darei moedas "
                    + "\na mais para vocês, mas parem com essa algazarra que aqui não é um maldito circo!");
            JOptionPane.showMessageDialog(null, "Narrador: Sarah te ouviu e distribuiu mais 100 moedas entre os tripulantes incluindo você, "
                    + "\nmas ela não ficou nem um pouco contente com suas atitudes...você deixou "
                    + "\nde ser o queridinho da capitã...");

            //Atualiza a quantidade de moedas no mostrador do menu do jogo
            int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
            int totalCoins = currentCoins + 100;
            coinCounter.setText("Moedas: " + totalCoins);

            //Mensagem sobre o uso de técnicas da matéria de fundamentos
            JOptionPane.showMessageDialog(null, "Narrador: Que pena marujo, você não conseguiu usar o mecanismo de defesa da racionalização! "
                    + "\nMesmo assim conseguiu adquirir poucos recursos para o sustento da tripulação e a capitã "
                    + "\nficou com a maior parte do tesouro para si.");
        }
    }

    //Método que apresenta uma sequência de diálogos referentes a FASE 4
    public static void TEXTOS5(String playerName, JLabel coinCounter) {
        //Mensagens que definem os diálogos marcando o início da fase
        JOptionPane.showMessageDialog(null, "Narrador: Depois de alguns dias em alto mar, você se deparam com um polvo gigante tentando atacar o navio!");
        JOptionPane.showMessageDialog(null, "Narrador: Sarah corre e se esconde dentro do convés, deixando os tripulantes confusos e soltos sem direção.");
        JOptionPane.showMessageDialog(null, "Narrador: Enfrentando o ataque do polvo gigante, a tripulação do navio Pérola Negra se vê diante de um "
                + "\ndesafio monumental. Diante do desespero da capitã Sarah, você assume o comando do navio e se depara "
                + "\ncom a necessidade de aplicar aprendizados de mecanismos de defesa, segundo os "
                + "\nfundamentos da psicologia e administração.");
        JOptionPane.showMessageDialog(null, "Narrador: Com a ameaça iminente do monstro marinho, você precisa tomar uma decisão estratégica para proteger "
                + "\no navio e sua tripulação, enquanto lida com suas próprias emoções e as dos demais tripulantes. Utilizando "
                + "\na sublimação, um mecanismo de defesa, você canaliza suas emoções negativas, como ansiedade e medo, para "
                + "\na ação produtiva e construtiva");
        JOptionPane.showMessageDialog(null, "Narrador: Isso significa que, ao invés de sucumbir ao pânico, ele transforma essas emoções em energia motivadora para "
                + "\nliderar a tripulação em uma batalha intensa contra o polvo gigante. Essa capacidade de sublimar as "
                + "\nemoções permitiu que " + playerName + " agisse com determinação e coragem, mesmo diante de circunstâncias "
                + "\nadversas, garantindo a liderança do navio e de seus companheiros.");

        //Abre uma janela que apresenta duas maneiras de lidar com o polvo gigante
        int escolha = JOptionPane.showOptionDialog(null, "Como você vai atacar o polvo gigante?", "Escolha sua ação: ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Atacar com os canhões", "Atacar com todas as armas do navio"}, null);
        if (escolha == JOptionPane.YES_NO_OPTION) {
            //Mostra essas mensagens se o jogador escolhe atacar com os canhões
            JOptionPane.showMessageDialog(null, "Você decidiu assumir a liderança e atacar com os canhões junto da tripulação. Aplicando uma estratégia "
                    + "\nde defesa agressiva enquanto canaliza sua ansiedade e medo para a ação combativa.");
            JOptionPane.showMessageDialog(null,  playerName + ": Companheiros, preparem os canhões! Vamos enfrentar essa ameaça \nde frente e proteger nosso navio!");
            JOptionPane.showMessageDialog(null, "Tripulantes: Estamos prontos, " + playerName + "!");
            JOptionPane.showMessageDialog(null, "Narrador: Utilizando a sublimação, você canaliza sua ansiedade e medo para a ação combativa, liderando "
                    + "\na tripulação em uma batalha intensa contra o polvo gigante. Apesar dos desafios enfrentados e dos "
                    + "\ndanos sofridos pelo navio, sua determinação e coragem e da tripulação resultam na vitória "
                    + "\nsobre a criatura marinha.");
        } else {
            //Mostra essas mensagens se o jogador escolhe atacar com todas as armas do navio
            JOptionPane.showMessageDialog(null, "Você escolheu atacar com todas as armas do navio, implementando uma estratégia defensiva baseada na "
                    + "\npreparação e na utilização eficiente dos recursos disponíveis, ao mesmo tempo em que busca "
                    + "\ncontrolar suas emoções e as dos tripulantes, canalizando-as para a "
                    + "\ncoordenação eficaz das ações.");
            JOptionPane.showMessageDialog(null,  playerName + ": Companheiros, vamos usar todas as armadilhas disponíveis! Precisamos nos defender e neutralizar "
                    + "\nessa ameaça com astúcia!");
            JOptionPane.showMessageDialog(null, "Tripulantes: Entendido, " + playerName + "!");
            JOptionPane.showMessageDialog(null, "Narrador: Após uma batalha assuida contra a fera do mar, você e os tripulantes conseguem deter a fera marinha. Você não "
                    + "\nconseguiu controlar suas emoções usando o mecanismo de defesa da sublimação, mas o tripulantes não perceberam! "
                    + "\nNo entanto você e boa parte da tripulação saiu machucada. O navio sofreu muitos danos, mas tudo bem, "
                    + "\nfoi seu primeiro ato de liderança.");
            JOptionPane.showMessageDialog(null, "Você perdeu 30 de vida!!!");

            //Atualiza a quantidade de vida no mostrador do menu do jogo
            int lifeLost = 30;
            int currentLife = Integer.parseInt(lifeLabel.getText().split(": ")[1]);
            int totalLife = currentLife - lifeLost;
            lifeLabel.setText("Vida: " + totalLife);
        }
    }

    //Método que apresenta uma sequência de diálogos referentes a FASE 5
    public static void TEXTOS6(String playernName, JLabel coinCounter) {
        //Mensagens que definem os diálogos marcando o início da fase
        JOptionPane.showMessageDialog(null, "Narrador: Com a situação se deteriorando entre a tripulação e a capitã Sarah Fortune, os ânimos estão acirrados "
                + "\na bordo do navio Pérola Negra. Enquanto isso, rumores de traição circulam pelos corredores do navio, "
                + "\nalimentando uma atmosfera de desconfiança e conspiração.");

        //Abre uma janela que apresenta duas maneiras de enfrentar o conflito
        int escolha = JOptionPane.showOptionDialog(null, "Como você irá resolver essa situação?", "Escolha sua ação: ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Confrontar e expor diretamente a capitã Sarah Fortune", "Tentar um plano mais sutil, buscando provas primeiro"}, null);

        if (escolha == JOptionPane.YES_OPTION) {
            //Mostra essas mensagens se o jogador escolhe confrontar a capitã diretamente
            JOptionPane.showMessageDialog(null, playernName +": Capitã, precisamos conversar a sós. Descobri que há um plano para me colocar na prancha. "
                    + "\nSei que as coisas não estão bem entre nós, mas traição não é o caminho.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: O quê? Como ousa espalhar essas mentiras? \nNão temos tempo para suas conspirações, estamos em alto mar!");
            JOptionPane.showMessageDialog(null, playernName+": Não são mentiras, capitã. Sei que algo mudou entre nós, mas isso não justifica tentar me eliminar. "
                    + "\nA tripulação está descontente, e se unir contra voçê não é o que desejo, "
                    + "\nmas é o que pode acontecer se não resolvemos isso agora.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Você está fora de si, "+ playernName+". Mas seja como for, não vou permitir que interfira em meus planos!");
            JOptionPane.showMessageDialog(null, "Narrador: A tensão atinge seu ápice enquanto o você confronta diretamente a capitã, desafiando sua autoridade e expondo "
                    + "\nos planos de traição. A abordagem da capitã reflete os princípios da administração clássica, onde a liderança é "
                    + "\nexercida de maneira autoritária e centralizada, sem considerar as opiniões ou preocupações dos subordinados. "
                    + "\nEnquanto isso, os canhões do navio são posicionados estrategicamente e os tripulantes recebem instruções "
                    + "\npara reforçar as barricadas, preparando-se para qualquer eventual confronto.");
        } else {
            //Mostra essas mensagens se o jogador escolhe buscar provas primeiro
            JOptionPane.showMessageDialog(null, playernName +": Tripulantes, reuni vocês aqui hoje para apresentar algo crucial. Tenho evidências de que a capitã está "
                    + "\nplanejando me colocar na prancha. \nSei que as coisas não estão bem, mas traição não é a resposta.");
            JOptionPane.showMessageDialog(null, "Tripulante 1: Evidências? Do que está falando?");
            JOptionPane.showMessageDialog(null, playernName +": Tenho testemunhos de outros membros da tripulação. Não podemos deixar que a capitã nos divida, precisamos "
                    + "\nnos unir para proteger o navio e nossos interesses.");
            JOptionPane.showMessageDialog(null, "Tripulante 2: Se isso for verdade...");
            JOptionPane.showMessageDialog(null, playernName +": A verdade está diante de nós, amigos. Precisamos agir com cautela, mas com determinação. Juntos, "
                    + "\npodemos mudar o rumo desta situação.");
            JOptionPane.showMessageDialog(null, "Narrador: Ao apresentar as evidências aos tripulantes, o jogador consegue semear dúvidas sobre a capitã e conquistar o "
                    + "\napoio da maioria da tripulação, refletindo uma quebra com os princípios da administração clássica. "
                    + "\nEnquanto isso, armadilhas são preparadas em pontos estratégicos do navio, garantindo a defesa contra "
                    + "\nqualquer tentativa de retaliação por parte da capitã.");
        }
    }

    //Método que apresenta uma sequência de diálogos referentes a FASE 6
    public static void TEXTOS7(String playerName, JLabel coinCounter) {
        //Cria um objeto Random com uma semente fixa
        Random rand = new Random(1);

        //Mensagens que definem os diálogos marcando o início da fase
        JOptionPane.showMessageDialog(null, "Narrador: A tensão atinge o navio Pérola Negra. A missão culmina em um ponto crucial, onde você "
                + "\ndetermina o comando do navio, moldando assim o curso da odisseia do corsário.");

        //Abre uma janela que apresenta duas maneiras de enfrentar o conflito
        int escolha = JOptionPane.showOptionDialog(null, "Como você prefere resolver essa situação?", "Escolha sua ação: ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Confrontar a capitã num combate mortal", "Expulsar Sarah Fortune de forma pacífica"}, null);
        if (escolha == JOptionPane.YES_OPTION){
            //Mostra essas mensagens se o jogador escolhe confrontar a capitã num combate mortal
            JOptionPane.showMessageDialog(null, "Você decide confrontar diretamente a capitã, resultando em um duelo mortal!");
            JOptionPane.showMessageDialog(null, playerName + ": Capitã Sarah, chegou o momento de acertarmos nossas contas. Você traiu a confiança da tripulação e "
                    + "\ntentou me eliminar. Não posso permitir que continue no comando deste navio.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Você ousa desafiar minha autoridade? Sou a capitã desse navio e não permitirei que "
                    + "\num insolente como você me subjugue!");
            JOptionPane.showMessageDialog(null, playerName + ": Suas palavras não mudarão a situação. Seu reinado termina aqui. Prepare-se para enfrentar as "
                    + "\nconsequências de suas ações.");
            JOptionPane.showMessageDialog(null, "Narrador: Determinado a não ceder às manipulações da capitã, o jogador enfrenta Sarah Fortune em um duelo mortal. "
                    + "\nCom habilidade e determinação, o jogador emerge vitorioso, assumindo o comando do navio Pérola Negra como o "
                    + "\nnovo capitão. Ao confrontar diretamente a capitã Sarah e assumir o comando do navio, o jogador demonstra "
                    + "\numa abordagem baseada nos princípios da administração científica. Ao identificar e lidar com problemas "
                    + "\nde forma direta, o jogador busca aumentar a eficácia e produtividade do navio, promovendo "
                    + "\numa atmosfera de trabalho mais organizada e eficiente.");
        }
        else {
            //Mostra essas mensagens e o jogador escolhe expulsar a capitã de forma pacífica
            JOptionPane.showMessageDialog(null, "Você opta por uma abordagem mais pacífica, expulsando Sarah Fortune da tripulação sem derramamento de sangue.");
            JOptionPane.showMessageDialog(null, playerName + "Capitã Sarah, suas ações prejudicaram a todos nós. É hora de partir, seus dias como líder deste "
                    + "\nnavio acabaram. Não desejo derramar sangue, mas não permitirei que continue a semear discórdia entre nós.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Você pensa que pode me expulsar? Eu sou a capitã desse navio, e vocês todos me devem lealdade!");
            JOptionPane.showMessageDialog(null, playerName + ": Ninguém é dono da lealdade de ninguém. Você abusou de sua posição e agora está colhendo as "
                    + "\nconsequências. Parta agora, e não farei nada para impedi-la.");
            JOptionPane.showMessageDialog(null, "Narrador: Com relutância, a capitã Sarah Fortune parte do navio Pérola Negra, deixando para trás sua antiga "
                    + "\nposição de poder. " + playerName + " assume o comando, guiando a tripulação em direção a um novo futuro, "
                    + "\nbaseado na cooperação e no respeito mútuo. Ao buscar resolver conflitos sem recorrer à violência, você"
                    + "\ndemonstra sua compreensão dos princípios da administração científica. Onde os interesses e bem-estar "
                    + "\nda tripulação são priorizados para aumentar a eficácia e produtividade do navio.");
        }
    }

    //Método que apresenta uma sequência de diálogos referentes a FASE 7 e fase final
    public static void TEXTOS8(String playerName, JLabel coinCounter){
        //Cria um objeto Random com uma semente fixa
        Random rand = new Random(1);

        //Apresenta uma mensagem inicial que define o cenário de reflexão sobre a jornada
        JOptionPane.showMessageDialog(null, "Após superar inúmeras adversidades e desafios, o navio Pérola Negra navega em águas tranquilas, refletindo "
                + "\nsobre as lições aprendidas ao longo da jornada. O destino final está à vista, " + playerName + ", "
                + "\nagora capitão(ã) do navio, prepara-se para o último trecho da viagem.");

        //Abre uma janela que apresenta duas maneiras de enfrentar o conflito
        int escolha = JOptionPane.showOptionDialog(null, "Qual será seu estilo de comando como capitão(ã)?", "Escolha sua ação: ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Conduzir com liderança humanísta", "Continuar com comando autoritário"}, null);

        if(escolha == JOptionPane.YES_OPTION) {
            //Mostra essas mensagens se o jogador escolhe conduzir a tripulação de forma humanista
            JOptionPane.showMessageDialog(null, "Você escolheu conduzir a tripulação de forma humanísta! Promovendo o bem-estar e a colaboração entre todos a bordo, "
                    + "\nbuscando o desenvolvimento pessoal e a valorização de cada membro da tripulação.");
            JOptionPane.showMessageDialog(null, playerName + ": Tripulação, chegamos ao momento final desta jornada. Quero expressar minha gratidão por cada um de vocês e "
                    + "\npelo esforço que dedicaram a esta viagem. Juntos, superamos desafios que pareciam insuperáveis, e é isso "
                    + "\nque nos torna uma equipe forte e unida.");
            JOptionPane.showMessageDialog(null, "Tripulantes: Capitão, estamos prontos para seguir suas ordens. Obrigado por nos liderar com respeito e compaixão.");
            JOptionPane.showMessageDialog(null, playerName+ ": Cada um de vocês tem um papel importante nesta jornada, e é meu dever garantir que todos se sintam "
                    + "\nvalorizados e ouvidos. Vamos continuar navegando com coragem e determinação, mantendo sempre em mente os "
                    + "\nvalores que nos guiaram até aqui.");
            JOptionPane.showMessageDialog(null, "Narrador: Sob a liderança de "+ playerName + ", o navio Pérola Negra segue adiante, fortalecido pela união e cooperação "
                    + "\nde sua tripulação. Cada membro é valorizado e respeitado, criando um ambiente de trabalho onde todos se "
                    + "\nsentem motivados e engajados em alcançar os objetivos comuns.");
        } else {
            //Mostra essas mensagens se o jogador escolhe continuar com um comando autoritário
            JOptionPane.showMessageDialog(null, "Você decidiu seguir com uma abordagem autoritária! Impondo sua vontade sobre a tripulação e priorizando seus "
                    + "próprios interesses, desconsiderando o aspecto humano e as necessidades indiviuais dos tripulantes.");
            JOptionPane.showMessageDialog(null, playerName + ": Tripulação, escutem-me bem. Chegamos ao final desta jornada, e quero que cada um de vocês se "
                    + "\nlembre de quem é o verdadeiro líder aqui. Minhas ordens devem ser seguidas sem questionamento, "
                    + "\ne qualquer desvio será punido com severidade.");
            JOptionPane.showMessageDialog(null, "Tripulantes: (sussurros e murmúrios de descontentamento)...");
            JOptionPane.showMessageDialog(null, playerName + ": Silêncio! Aqueles que desejam desfrutar dos tesouros desta viagem devem obedecer às minhas regras. "
                    + "\nNão tolerarei deslealdade ou insubordinação!.");
            JOptionPane.showMessageDialog(null, "Narrador: Sob sua liderança autoritária, o navio Pérola Negra avança em direção ao seu destino final. "
                    + "\nA atmosfera a bordo é tensa e hostil, com a tripulação desmotivada e desunida. O medo e a desconfiança "
                    + "\npermeiam o ar, minando qualquer esperança de um futuro próspero.");
        }

        //Mensagem final de encerramento do jogo, refletindo sobre a importância das relações humanas
        JOptionPane.showMessageDialog(null, "Narrador: A jornada do Pérola Negra chega ao fim, deixando para trás um legado de aprendizado, crescimento e, "
                + "\nacima de tudo, importância das relações humanas na busca por um objetivo em comum!");
        JOptionPane.showMessageDialog(null, "Muito obrigado por jogar!");
    }
}