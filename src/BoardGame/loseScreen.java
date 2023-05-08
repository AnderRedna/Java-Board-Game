package BoardGame;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;

public class loseScreen extends JPanel {

    public static void main(String[] args) throws IOException{

      JFrame loseFrame = new JFrame();
      loseFrame.setPreferredSize(new Dimension(500,380));

      loseScreen loseScreen = new loseScreen();
      loseScreen.setBackground(Color.RED);
      loseScreen.setLayout(new BorderLayout());

      JLabel name = new JLabel("DERROTA");
      name.setFont(new Font("Garamond", Font.BOLD, 50));
      name.setForeground(Color.WHITE);
      name.setHorizontalAlignment(JLabel.CENTER);

      JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

      JButton restart = new JButton("Reiniciar Jogo");
      JButton newgame = new JButton("Novo Jogo");
      restart.setPreferredSize(new Dimension(115,40));
      newgame.setPreferredSize(new Dimension(115,40));
      buttonsPanel.setBackground(Color.RED);

      buttonsPanel.add(restart);
      buttonsPanel.add(newgame);

      loseScreen.add(buttonsPanel, BorderLayout.SOUTH);
      loseScreen.add(name, BorderLayout.CENTER);
      loseFrame.add(loseScreen);
      loseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      loseFrame.pack();
      loseFrame.setVisible(true);
    }
}