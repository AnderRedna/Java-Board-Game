package BoardGame;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;

public class winScreen extends JPanel {

    public static void main(String[] args) throws IOException{

      JFrame winFrame = new JFrame();
      winFrame.setPreferredSize(new Dimension(500,380));

      winScreen winScreen = new winScreen();
      winScreen.setBackground(new Color(34, 139, 34));
      winScreen.setLayout(new BorderLayout());

      JLabel name = new JLabel("VITÓRIA");
      name.setFont(new Font("Garamond", Font.BOLD, 50));
      name.setForeground(Color.WHITE);
      name.setHorizontalAlignment(JLabel.CENTER);

      JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

      JButton restart = new JButton("Reiniciar Jogo");
      JButton newgame = new JButton("Novo Jogo");
      restart.setPreferredSize(new Dimension(115,40));
      newgame.setPreferredSize(new Dimension(115,40));
      buttonsPanel.setBackground(new Color(34, 139, 34));

      buttonsPanel.add(restart);
      buttonsPanel.add(newgame);

      winScreen.add(buttonsPanel, BorderLayout.SOUTH);
      winScreen.add(name, BorderLayout.CENTER);
      winFrame.add(winScreen);
      winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      winFrame.pack();
      winFrame.setVisible(true);
    }
}