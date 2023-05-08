package BoardGame;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;

public class MenuScreen extends JPanel {

    public static void main(String[] args) throws IOException{

        JFrame menuFrame = new JFrame();
        menuFrame.setPreferredSize(new Dimension(500,380));

        MenuScreen menuScreen = new MenuScreen();
        menuScreen.setBackground(new Color(102, 51, 0));
        menuScreen.setLayout(new BorderLayout());

        JLabel name = new JLabel("MINI-COMBATE");
        name.setFont(new Font("Candara", Font.BOLD, 30));
        name.setForeground(Color.WHITE);
        name.setHorizontalAlignment(JLabel.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(102, 51, 0));

        JLabel creators = new JLabel("Anderton & Gabriel");
        creators.setFont(new Font("Arial", Font.BOLD, 12));
        creators.setForeground(Color.WHITE);

        topPanel.add(creators);
        menuScreen.add(topPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setBackground(new Color(102, 51, 0));

        JButton random = new JButton("Aleatório");
        JButton select = new JButton("Escolher posições");
        random.setPreferredSize(new Dimension(140,50));
        select.setPreferredSize(new Dimension(140,50));

        buttonsPanel.add(random);
        buttonsPanel.add(select);

        menuScreen.add(buttonsPanel, BorderLayout.SOUTH);
        menuScreen.add(name, BorderLayout.CENTER);
        menuFrame.add(menuScreen);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.pack();
        menuFrame.setVisible(true);
    }
}