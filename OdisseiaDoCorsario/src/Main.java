import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    static JTextField playerNameField;
    static ArrayList<String> playerNames;
    static JLabel foodLabel;
    static JLabel lifeLabel;
    static JLabel coinCounter;

    public static void main(String[] args) {
        int coin = 0;
        int food = 100;
        int life = 100;

        JFrame frame = new JFrame("Velas do Destino: A Odisséia do Corsário");
        frame.setSize(600, 500);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\mayar\\Documents\\Grupo-1---Turma-A\\imagem menu\\IMG_1347.PNG");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        frame.add(panel);

        JButton instructionsButton = new JButton("Instruções");
        instructionsButton.setBackground(Color.gray);
        instructionsButton.setBounds(100, 400, 100, 30);
        panel.add(instructionsButton);

        JButton startButton = new JButton("Jogar");
        startButton.setBackground(Color.gray);
        startButton.setBounds(250, 400, 100, 30);
        panel.add(startButton);

        JButton closeButton = new JButton("Sair");
        closeButton.setBackground(Color.gray);
        closeButton.setBounds(400, 400, 100, 30);
        panel.add(closeButton);

        JButton creditsButton = new JButton("Créditos");
        creditsButton.setBackground(Color.gray);
        creditsButton.setBounds(470, 10, 100, 30);
        panel.add(creditsButton);

        playerNameField = new JTextField();
        playerNameField.setBounds(200, 350, 200, 30);
        panel.add(playerNameField);

        coinCounter = new JLabel("Moedas: 0");
        coinCounter.setForeground(Color.YELLOW);
        coinCounter.setBounds(10, 10, 100, 20);
        panel.add(coinCounter);

        foodLabel = new JLabel("Comida: 100");
        foodLabel.setForeground(Color.red);
        foodLabel.setBounds(10, 30, 100, 20);
        panel.add(foodLabel);

        lifeLabel = new JLabel("Vida: 100");
        lifeLabel.setForeground(Color.green);
        lifeLabel.setBounds(10, 50, 100, 20);
        panel.add(lifeLabel);

        playerNames = new ArrayList<>();

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int escolha = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim\n", "Não"}, null);
                switch(escolha) {
                    case JOptionPane.YES_OPTION:
                        frame.dispose();
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            final int[] coins = {0};
            final int[] food = {0};
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText();
                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira um nome de usuário.");
                    return;
                }
                if (playerNames.contains(playerName)) {
                    JOptionPane.showMessageDialog(frame, "Este nome de usuário já existe, por favor, escolha outro.");
                } else {
                    playerNames.add(playerName);
                    JOptionPane.showMessageDialog(frame, "Bem-vindo, marujo! Hoje será seu dia à caçada ao tesouro, " + playerName + "!");
                }
                int opcao = JOptionPane.showConfirmDialog(frame, "Você está pronto para começar? A Sarah irá te ajudar nessa nova jornada épica, " + playerName + "!");
                if (opcao != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Opção cancelada. Retornando ao menu");
                    return;
                }
                JOptionPane.showMessageDialog(frame, "Narrador: Você está preso na cela do pirata Barba Negra, pois você é um amotinado que seguiu contra as regras do seu capitão.\n Mas de repente você ouviu um barulho estranho, e quando menos percebe, escuta um tiro de canhão atingir o navio.");
                TEXTOS(playerName, coinCounter, foodLabel);
                TEXTOS2(playerName, coinCounter);
                TEXTOS3(playerName, coinCounter);
                TEXTOS4(playerName, coinCounter);
                TEXTOS5(playerName, coinCounter);
                TEXTOS7(playerName, coinCounter);

                coins[0] = 50;
                coinCounter.setText("Moedas: " + coins[0]);
                food[0] = 80;
                foodLabel.setText("Comida: " + food[0]);
            }
        });
        frame.setVisible(true);
        int percavida;
    }

    public static void TEXTOS(String playerName, JLabel coinCounter, JLabel foodLabel) {
        Random rand = new Random(1);
        if (playerNames.contains(playerName))
            JOptionPane.showMessageDialog(null, "Narrador: Você fica assustado pois poderia ter morrido, mas ao mesmo tempo agradece ao perceber que a bala de canhão atingiu uma parte da cela do navio, e agora você poderá fugir.");
        JOptionPane.showMessageDialog(null, "Narrador: Assim que você sai do porão do navio, você percebe que ele esta sendo invadido por Sarah Fortune, a capitã do Pérola Negra!\n E agora você finalmente terá a chance de começar do zero e se vingar, podendo administrar sua própria vida. ");
        JOptionPane.showMessageDialog(null, "Narrador: Enquanto o Navio está sendo atacado, você consegue uma espada e começa a lutar contra sua antiga tripulação, deixando claro qual lado você está. \n E é assim que a história do maior pirata dos sete mares começa... ");
        JOptionPane.showMessageDialog(null, "Narrador: Depois de todo o conflito, você se aliou a Capitã Fortune e agora faz parte da tripulação... \n Agora nesse momento você estão indo para uma caça ao tesouro, e todos os outros piratas estão se preparando para a busca do ouro enquanto seguem ordens da Capitã.");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Se apresentem, Marujos! \n Hoje temos uma missão mais tranquila, mas não menos importante!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Hoje eu irei escolher um de vocês para capturar algo muito importante... Algo valioso!");
        JOptionPane.showMessageDialog(null, "Narrador: A capitã olha para todos os tripulantes do navio, mas seus olhos pousam sobre voceê. ");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Aha! É você mesmo que eu estava procurando, " + playerName);
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Você precisa capturar um tesouro pra mim! MAS... não é qualquer tesouro, marujo... \n É um baú cheio de joias e ouro!!!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Você terá que enfrentar um tubarão... Mas pelo o que eu ja ouvi de você, isso será moleza... \n De qualquer modo, eu não estou te dando escolha! Só tome cuidado. Eu odiaria perder minha fortuna por erro seu... ");
        int escolha = JOptionPane.showOptionDialog(null, "Como você prefere enfrentar o tubarão?", "Escolha sua ação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Atacar o tubarão", "Tentar modo furtivo"}, null);
        if (escolha == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Você decide atacar o tubarão! Corajoso, " + playerName + "!");

            int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
            int totalCoins = currentCoins + 50;
            coinCounter.setText("Moedas: " + totalCoins);
        } else {
            JOptionPane.showMessageDialog(null, "Você decide tentar modo furtivo para passar pelo tubarão distraindo ele com pote de comida velha.");
            modoFurtivo(playerName, coinCounter, foodLabel);
        }
    }

    public static void TEXTOS2(String playerName, JLabel coinCounter) {
        Random rand = new Random(1);


        if (playerNames.contains(playerName))
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Muito bom marujo. \n Não imaginava que você conseguiria equilibrar as situações... \n Se você continuar desse jeito, terei que promover você para um cargo mais importante... ");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Te darei uma pequena recompensa por ter resgatado nosso tesouro perdido! \n Mas não saia gastando por ai, ouro é uma coisa muito valiosa, " + playerName + ". Administre muito bem...");
    }


    public static void modoFurtivo(String playerName, JLabel coinCounter, JLabel foodLabel) {

        int coinsEarned = 50;
        int foodLost = 20;

        int currentFood = Integer.parseInt(foodLabel.getText().split(": ")[1]); // Alterei para comidaLabel
        int totalFood = currentFood - foodLost;
        foodLabel.setText("Comida: " + totalFood); // Alterei para comidaLabel


        JOptionPane.showMessageDialog(null, "Você está no modo furtivo. Ganhou " + coinsEarned + " moedas, mas perdeu " + foodLost + " de comida.");

        int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
        int totalCoins = currentCoins + coinsEarned;
        coinCounter.setText("Moedas: " + totalCoins);
    }



    public static void TEXTOS3(String playerName, JLabel coinCounter) {
        Random rand = new Random(1);

        JOptionPane.showMessageDialog(null, "Narrador: Depois da sua primeira missão, todos no navio te prestigiaram, te tornando o queridinho da capitã...");
        JOptionPane.showMessageDialog(null, "Narrador: Agora a Capitã foi convidada para Tortuga, pelo pirata Barbosa. \n Chegando la vocês descobrem uma nova expedissão sendo feita pela Coroa Britanica, \n cheia de joias, rum, especiarias e ouro para ser saqueado!");
        JOptionPane.showMessageDialog(null, "Narrador: Sarah e Barbosa se encontram em uma taverna junto com sua tripulação reunida para conversarem sobre negócios...");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Devo dizer que fiquei surpresa com seu convite... \n Depois da párola achei que nunca mais daria as caras por aqui...");
        JOptionPane.showMessageDialog(null, "Barbosa: Ah por favor, não vamos entrar nesse assunto, a convidei amigavelmente para um jantar de negócios...");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Negócios é? E de que tipo...?");
        JOptionPane.showMessageDialog(null, "Barbosa: Grandes negócios minha cara Sarah Fortune... \n muito tesouro, rum, joias e especiarias confiscadas pela coroa real...");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Vocês homens... só pensam em rum e mulheres! \n isso não me interessa, quero saber o quanto de dinheiro vamos ganhar nessa brincadeira!");
        JOptionPane.showMessageDialog(null, "Barbosa: Muito mais do que você já ganhou ou imaginou ter, e então? Está de acordo? \n Podemos nos unir, eu te garanto que a nossa tripulação vai adorar a ideia... ");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Mas é ovio, se envolve dinheiro é claro que vou aceitar, um dia serei feito de ouro!");
        JOptionPane.showMessageDialog(null, "Narrador: Sarah Fortune aceita a missão em Tortuga. Você junto com o resto da tripulação e com a capitã, se preparam pensando em um plano para atacar os britanicos. ");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Ok homens, e uquero todos em meu convés! Barbosa, me empreste todos os seus homens. \n Vamos expulsar esses carapaças daqui!");
        JOptionPane.showMessageDialog(null, "Barbosa: Ficou louca? não deixarei meus homens morrerem pela sua investida burra e precipitada de lutar frente a frente com os britanicos! \n Está vendo a sua direita? tem umas rochas cobrindo a visão deles. Vamos encurála-los pelas costas e trucidá-llos!");
        JOptionPane.showMessageDialog(null, playerName + ": Eu tenho uma ideia, sei que sou novo ainda, mas tenho experiencia em batalha, posso dizer com confiança que eu poderei os ajudar com isso. \n Minha tripulação será conhecida por afundar a terrivel coroa britanica no fundo do mar! ");
        JOptionPane.showMessageDialog(null, "Narrador: O destino dessa missão está novamente em suas mãos \n você agora deve usar seu modo tático, pense em em como atacar, analise bem antes da implementação da estrategia!");

        int escolha = JOptionPane.showOptionDialog(null, "Como você irá resolver essa situação?", "Escolha sua ação: ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Deveremos atacar. \n Pois a coroa britanica está com bens que não os peretencem", "Deveremos seguir no modo furtivo. \n Sendo mais racional e priorizar a captura do que foi ampreendido."}, null);
        if (escolha == JOptionPane.YES_OPTION) {
            int lifeLost = 30;
            int foodLost = 10;

            int currentLife = Integer.parseInt(lifeLabel.getText().split(": ")[1]);
            int totalLife = currentLife - lifeLost;
            lifeLabel.setText("Vida: " + totalLife);

            int currentFood = Integer.parseInt(foodLabel.getText().split(": ")[1]);
            int totalFood = currentFood - foodLost;
            foodLabel.setText("Comida: " + totalFood);

            JOptionPane.showMessageDialog(null, "Vocês decidem atacar, porém perdem " + lifeLost + " de vida e " + foodLost + " de comida. Não foi a melhor opção. \n Pense melhor na próxima missão, quem sabe você ganha uma parte recompensa, " + playerName + "!");
        } else {
            int coinsEarned = 100;
            int foodEarned = 10;

            int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
            int totalCoins = currentCoins + coinsEarned;
            coinCounter.setText("Moedas: " + totalCoins);

            int currentFood = Integer.parseInt(foodLabel.getText().split(": ")[1]);
            int totalFood = currentFood + foodEarned;
            foodLabel.setText("Comida: " + totalFood);

            JOptionPane.showMessageDialog(null, "Vocês escolhem se infiltrar na expedição e conseguem roubar 70% do ouro e comida, você recebeu uma parte!");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Por respeito a você ter me dado um báu cheio de outro");
        }
    }

    public static void TEXTOS4(String playerName, JLabel coinCounter) {
        Random rand = new Random(1);
        JOptionPane.showMessageDialog(null, "Narrador: Depois da captura dos tesouros, a tripulação passa por um momento de conflito. \n Agora nas mãos da nossa capitã estavam uma quantidade estrondosa de tesouros e moedas de ouro.");
        JOptionPane.showMessageDialog(null, "Narrador: Os tripulantes se revoltam pela repartição injusta dos tesouros. Todos trabalham e se esforçam pelo sucesso do navio e da capitã, sendo assim devem ser reconhecidos e pagos pela produtividade.");
        JOptionPane.showMessageDialog(null, "Narrador: O vislumbre atacou a capitã, e a distribuição das recompensas entre os tripulantes não os agrada muito.");
        JOptionPane.showMessageDialog(null, "Narrador: Você percebe que hã um conflito com a administração classica, onde todos se esforçam pelo navio da capitã, sendo assim devem ser reconhecidos. no entanto, isso não tem acontecido direito...");
        JOptionPane.showMessageDialog(null, "Tripulante 1: Capitã, não acho que esteja sendo justa com a nossa recompensa!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Oh marujo, ao meu ver isto é mais que o suficiente para vocês. Se não fosse por mim, ao menos iriam saber a localização da coroa.");
        JOptionPane.showMessageDialog(null, "Tripulante 2: Está sendo injusta, capitã! estamos com você a anos, esta mudada, não costumava ser tão mesquinha desta forma!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Eu sou a ponta deste navio. EU quem mando aqui, e vocês não seriam nada sem mim! Se contentem com o que tem, se não os faço andar na prancha!");
        JOptionPane.showMessageDialog(null, "Narrador: Você reconhece a mesma atitude de Barba Negra nos olhos da capitã, temia que aquele navio virasse novamente a mesma prisão de que havia saido. \n Sendo assim, você decide fazer algo por você e pelos outros tripulantes do navio...");

        int escolha = JOptionPane.showOptionDialog(null, "Como você prefere resolver essa situação?", "Escolha sua ação: ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Conversar com a capitã de forma pacífica", "Gritar com a capitã na frente de todos"}, null);
        if (escolha == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Você decide conversar com a capitã de forma pacífica.");
            JOptionPane.showMessageDialog(null, playerName + ": Escuta, capitã... Nós somos seus companheiros, eles não querem te arruinar, estão todos do seu lado. os remunere como deve, pois você sabe que merecem.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Quem você pensa que é " + playerName + "? Eu sei bem o que merecem. não sei se devo te lembrar mas eu sou a capitã do navio!");
            JOptionPane.showMessageDialog(null, playerName + ": Sabe que eles acabariam com você, não sabe? só não fazem isso porque são como cães fieis a você. entregue a eles o que pediram a você!");
            JOptionPane.showMessageDialog(null, "Narrador: Sarah te ouviu e distribuiu mais 300 moedas entre os tripulantes incluindo você.");


            int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
            int totalCoins = currentCoins + 300;
            coinCounter.setText("Moedas: " + totalCoins);
        } else {
            JOptionPane.showMessageDialog(null, "Você decide gritar com a capitã na frente de todos!");
            JOptionPane.showMessageDialog(null, playerName + ": PAGUE LOGO O QUE NOS DEVE CAPITÃ, NÃO ESTAMOS AQUI PARA FAZER FAVOR!");
            JOptionPane.showMessageDialog(null, "Tripulante 1: Nos arriscamos por todos capitã! perdemos alguns homens! apenas queremos nosso dinheiro, se não a coisa vai ficar seria!");
            JOptionPane.showMessageDialog(null, "Narrador: Os tripulantes começam a gritar em uníssono, cobrando a capitã.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Tudo bem! Tudo bem! Parem com essa gritaria, seus animais! darei moedas a mais para vocês, mas parem com essa algazarra que aqui não é circo!");
            JOptionPane.showMessageDialog(null, "Narrador: Sarah te ouviu e distribuiu mais 300 moedas entre os tripulantes incluindo você, mas ela não ficou nem um pouco contente com suas atitudes... \n você deixou de ser o queridinho da capitã...");


            int currentCoins = Integer.parseInt(coinCounter.getText().split(": ")[1]);
            int totalCoins = currentCoins + 300;
            coinCounter.setText("Moedas: " + totalCoins);
        }
    }

    public static void TEXTOS5(String playername, JLabel coinCounter) {
        JOptionPane.showMessageDialog(null, "");

    }

    public static void TEXTOS7(String playerName, JLabel coinCounter) {
        Random rand = new Random(1);
        JOptionPane.showMessageDialog(null, "Narrador: A tensão atinge o navio Pérola Negra. A missão culmina em um ponto crucial, onde você determina o comando do navio, moldando assim o curso da odisseia do corsário.");

        int escolha = JOptionPane.showOptionDialog(null, "Como você prefere resolver essa situação?", "Escolha sua ação: ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Confrontar diretamente a capitã Sarah Fortune\n", "Optar por uma abordagem mais pacífica"}, null);
        if (escolha == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, "Você decide confrontar diretamente a capitã");
            JOptionPane.showMessageDialog(null, playerName + ": Capitã Sarah, chegou o momento de acertarmos nossas contas. Você traiu a confiança da tripulação e tentou me eliminar. Não posso permitir que continue no comando deste navio.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Você ousa desafiar minha autoridade? Sou a capitã desse navio e não permitirei que um insolente como você me subjugue!");
            JOptionPane.showMessageDialog(null, playerName + ": Suas palavras não mudarão a situação. Seu reinado termina aqui. Prepare-se para enfrentar as consequências de suas ações.");
            JOptionPane.showMessageDialog(null, "Narrador: Determinado a não ceder às manipulações da capitã, o jogador enfrenta Sarah Fortune em um duelo mortal.\n Com habilidade e determinação, o jogador emerge vitorioso, assumindo o comando do navio Pérola Negra como o novo capitão.");
        }
        else {
            JOptionPane.showMessageDialog(null, "Você opta por uma abordagem mais pacífica.");
            JOptionPane.showMessageDialog(null, playerName + "Capitã Sarah, suas ações prejudicaram a todos nós. É hora de partir, seus dias como líder deste navio acabaram.\n Não desejo derramar sangue, mas não permitirei que continue a semear discórdia entre nós.");
            JOptionPane.showMessageDialog(null, "Sarah Fortune: Você pensa que pode me expulsar? Eu sou a capitã desse navio, e vocês todos me devem lealdade!");
            JOptionPane.showMessageDialog(null, playerName + ": Ninguém é dono da lealdade de ninguém. Você abusou de sua posição e agora está colhendo as consequências. Parta agora, e não farei nada para impedi-la.");
            JOptionPane.showMessageDialog(null, "Narrador: Com relutância, a capitã Sarah Fortune parte do navio Pérola Negra, deixando para trás sua antiga posição de poder.\n Você assume o comando, guiando a tripulação em direção a um novo futuro, baseado na cooperação e no respeito mútuo.");
        }
    }

    public static void TEXTOS8(String playerName, JLabel coinCounter) {
        JOptionPane.showMessageDialog(null, "");
    }
}