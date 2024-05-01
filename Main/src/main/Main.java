import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    static JTextField playerNameField;
    static ArrayList<String> playerNames;
    static JLabel comidaLabel;
    static JLabel lifeLabel;
    
    public static void main(String[] args) {
        int coin = 0;
        int comida = 100;
        int life = 100;

        JFrame frame = new JFrame("Pirata");

        frame.setSize(600, 500);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\edwar\\Downloads\\IMG_1347.PNG");

                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null);

        frame.add(panel);

        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.gray);

        startButton.setBounds(250, 400, 100, 30);

        panel.add(startButton);

        playerNameField = new JTextField();
        playerNameField.setBounds(200, 350, 200, 30);
        panel.add(playerNameField);

        JLabel coinCounter = new JLabel("Moedas: 0");
        coinCounter.setForeground(Color.YELLOW);
        coinCounter.setBounds(10, 10, 100, 20);
        panel.add(coinCounter);

        comidaLabel = new JLabel("Comida: 50");
        comidaLabel.setForeground(Color.red);
        comidaLabel.setBounds(10, 30, 100, 20);
        panel.add(comidaLabel);

        lifeLabel = new JLabel("Vida: 100");
        lifeLabel.setForeground(Color.green);
        lifeLabel.setBounds(10, 50, 100, 20);
        panel.add(lifeLabel);

        playerNames = new ArrayList<>();

        startButton.addActionListener(new ActionListener() {
            final int[] coins = {0};

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

                TEXTOS(playerName);
                coins[0]++;
                coinCounter.setText("Moedas: " + coins[0]);

                for (; ; ) {

                }
            }
        });

        frame.setVisible(true);
    }

    public static void TEXTOS(String playerName) {
        if (playerNames.contains(playerName))
            JOptionPane.showMessageDialog(null, "Narrador: Você fica assustado pois poderia ter morrido, mas ao mesmo tempo agradece ao perceber que a bala de canhão atingiu uma parte da cela do navio, e agora você poderá fugir.");
        JOptionPane.showMessageDialog(null, "Narrador: Assim que você sai do porão do navio, você percebe que ele esta sendo invadido por Sarah Fortune, a capitã do Pérola Negra!\n E agora você finalmente terá a chance de começar do zero e se vingar, podendo administriar sua própria vida. ");
        JOptionPane.showMessageDialog(null, "Narrador: Enquanto o Navio está sendo atacado, você consegue uma espada e começa a lutar contra sua antiga tripulação, deixando claro qual lado você está. \n E é assim que a história do maior pirata dos sete mares começa... ");
        JOptionPane.showMessageDialog(null, "Narrador: Depois de todo o conflito, você se aliou a Capitã Fortune e agora faz parte da tripulação... \n Agora nesse momento você estão indo para uma caça ao tesouro, e todos os outros piratas estão se preparando para a busca do ouro enquanto seguem ordens da Capitã.");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Se apresentem, Marujos! \n Hoje temos uma missão mais tranquila, mas não menos importante!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Hoje eu irei escolher um de vocês para capturar algo muito importante... Algo valioso!");
        JOptionPane.showMessageDialog(null, "Narrador: A capitã olha para todos os tripulantes do navio, mas seus olhos pousam sobre voceê. ");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Aha! É você mesmo que eu estava procurando, " + playerName);
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Você precisa capturar um tesouro pra mim! MAS... não é qualquer tesouro, marujo... \n É um baú cheio de joias e ouro!!!");
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Você terá que enfrentar um tubarão... Mas pelo o que eu ja ouvi de você, isso será moleza... \n De qualquer modo, eu não estou te dando escolha! Só tome cuidado. Eu odiaria perder minha fortuna por erro seu...");


        int escolha = JOptionPane.showOptionDialog(null, "Como você prefere enfrentar o tubarão?", "Escolha sua ação",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Atacar o tubarão", "Tentar modo furtivo"}, null);

        if (escolha == JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(null, "Você decide atacar o tubarão! Corajoso, " + playerName + "!");
        } else {

            JOptionPane.showMessageDialog(null, "Você decide tentar modo furtivo para passar pelo tubarão sem chamar atenção.");
        }
    }
    
}
