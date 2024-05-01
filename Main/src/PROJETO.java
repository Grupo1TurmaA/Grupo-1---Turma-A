import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
        
public class PROJETO {
    static JTextField playerNameField;
    static ArrayList<String> playerNames;
    
    public static void main(String[] args) {
        int coin = 0;
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

        startButton.setBounds(250, 400, 100, 30);

        panel.add(startButton);

        playerNameField = new JTextField();
        playerNameField.setBounds(200, 350, 200, 30);
        panel.add(playerNameField);

        JTextField Narrador = new JTextField();
        Narrador.setBounds(200, 350, 200, 30);
        panel.add(Narrador);

        JLabel coinCounter = new JLabel("Moedas: 0");
        coinCounter.setForeground(Color.YELLOW);
        coinCounter.setBounds(10, 10, 100, 20);
        panel.add(coinCounter);

        final int[] coins = {0};

        playerNames = new ArrayList<>();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coins[0]++;
                coinCounter.setText("Moedas: " + coins[0]);

                String playerName = playerNameField.getText();

                if (playerNames.contains(playerName)) {
                    JOptionPane.showMessageDialog(frame, "Este nome de usuário já existe, por favor, escolha outro.");
                } else {
                    playerNames.add(playerName);
                    JOptionPane.showMessageDialog(frame, "Bem-vindo, marujo! Hoje será seu dia à caçada ao tesouro, ");
                }

                int opcao = JOptionPane.showConfirmDialog(frame, "Você está pronto para começar? A Sarah irá te ajudar nessa nova jornada épica, " + playerName + "!");
                if (opcao != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Opção cancelada. Retornando ao menu");
                    return; 
                }

                JOptionPane.showMessageDialog(frame, "Narrador: Você está preso na cela do pirata Barba Negra, pois você é um amotinado que seguiu contra as regras do seu capitão.\n Mas de repente você ouviu um barulho estranho, e quando menos percebe, escuta um tiro de canhão atingir o navio.");

                NAOSEI();
                TelaTexto1();
                TelaTexto2();
                TelaTexto3();
                TelaTexto4();
                TelaTexto5();
            }
        });

        frame.setVisible(true);
    }
    
    public static void NAOSEI() {
        String playerName = playerNameField.getText();
        if (playerNames.contains(playerName)) {
            JOptionPane.showMessageDialog(null, "Narrador: Você fica assustado pois poderia ter morrido, mas ao mesmo tempo agradece ao perceber que a bala de canhão atingiu uma parte da cela do navio, e agora você poderá fugir.");
        } else {
            JOptionPane.showMessageDialog(null, "?????");
        }
    }

    public static void TelaTexto1() {
        JOptionPane.showMessageDialog(null, "Narrador: Assim que você sai do porão do navio, você percebe que ele esta sendo invadido por Sarah Fortune, a capitã do Pérola Negra!\n E agora você finalmente terá a chance de começar do zero e se vingar, podendo administriar sua própria vida. ");
    }

    public static void TelaTexto2() {
        JOptionPane.showMessageDialog(null, "Enquanto o Navio está sendo atacado, você consegue uma espada e começa a lutar contra sua antiga tripulação, deixando claro qual lado você está. \n E é assim que a história do maior pirata dos sete mares começa... ");
    }

    public static void TelaTexto3() {
        JOptionPane.showMessageDialog(null, "Depois de todo o conflito, você se aliou a Capitã Fortune e agora faz parte da tripulação... \n Agora nesse momento você estão indo para uma caça ao tesouro, e todos os outros piratas estão se preparando para a busca do ouro enquanto seguem ordens da Capitã.");
    }

    public static void TelaTexto4() {
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Se apresentem, Marujos! \n Hoje temos uma missão mais tranquila, mas não menos importante!");
    }
    
    public static void TelaTexto5() {
        JOptionPane.showMessageDialog(null, "Sarah Fortune: Se apresentem, Marujos! \n Hoje temos uma missão mais tranquila, mas não menos importante!");
    }
}
