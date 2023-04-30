package BoardGame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessGame {
    public static LinkedList<Piece> ps = new LinkedList<Piece>();
    public static Piece selectedPiece = null;

public static void populateChess(LinkedList<Piece> ps) {
    String[] playerPieces = {"prisioneiro", "bomba", "soldado", "marechal", "espiao", "cabo"};
    String[] enemyPieces = {"prisioneiro", "bomba", "soldado", "marechal", "espiao", "cabo"};
    Random random = new Random();
    Piece prisioneiroPlayer = new Piece("prisioneiro", random.nextInt(5), 0, 0, true, ps);
    Piece prisioneiroEnemy = new Piece("prisioneiro", random.nextInt(5), 4, 0, false, ps);
    for (int i = 0; i < playerPieces.length; i++) {
        int xp, yp;
        do {
            xp = random.nextInt(5);
            yp = random.nextInt(2);
        } while (getPiece(xp * 64, yp * 64) != null);
        switch (playerPieces[i]) {
            case "bomba":
                Piece bombaPlayer = new Piece(playerPieces[i], xp, yp, 100, true, ps);
                break;
            case "soldado":
                Piece soldadoPlayer = new Piece(playerPieces[i], xp, yp, 2, true, ps);
                break;
            case "marechal":
                Piece marechalPlayer = new Piece(playerPieces[i], xp, yp, 10, true, ps);
                break;
            case "espiao":
                Piece espiaoPlayer = new Piece(playerPieces[i], xp, yp, 1, true, ps);
                break;
            case "cabo":
                Piece caboPlayer = new Piece(playerPieces[i], xp, yp, 3, true, ps);
                break;
            default:
                System.out.println("Erro");
                break;
        }
    }
    for (int i = 0; i < enemyPieces.length; i++) {
        int xp, yp;
        do {
            xp = random.nextInt(5);
            yp = random.nextInt(2)+3;
        } while (getPiece(xp * 64, yp * 64) != null);
        switch (enemyPieces[i]) {
            case "bomba":
                Piece bombaEnemy = new Piece(enemyPieces[i], xp, yp, 100, false, ps);
                break;
            case "soldado":
                Piece soldadoEnemy = new Piece(enemyPieces[i], xp, yp, 2, false, ps);
                break;
            case "marechal":
                Piece marechalEnemy = new Piece(enemyPieces[i], xp, yp, 10, false, ps);
                break;
            case "espiao":
                Piece espiaoEnemy = new Piece(enemyPieces[i], xp, yp, 1, false, ps);
                break;
            case "cabo":
                Piece caboEnemy = new Piece(enemyPieces[i], xp, yp, 3, false, ps);
                break;
            default:
                System.out.println("Erro");
                break;
        }
    }
}


    public static void main(String[] args) throws IOException {
        populateChess(ps);
        BufferedImage all = ImageIO.read(new File("C:\\chess.png"));
        Image imgs[] = new Image[14];
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1400; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        

        JFrame frame = new JFrame();
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 5; y++) {
                    for (int x = 0; x < 5; x++) {
                        if (white) {
                            g.setColor(new Color(107, 147, 86));
                        }
                        if (!white) {
                            g.setColor(new Color(167, 197, 106));
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    for (Piece p : ps) {
                        int ind = 0;
                        if (p.type.equalsIgnoreCase("bandeira")) {
                            ind = 1;
                        }
                        if (p.type.equalsIgnoreCase("bomba")) {
                            ind = 2;
                        }
                        if (p.type.equalsIgnoreCase("espiao")) {
                            ind = 3;
                        }
                        if (p.type.equalsIgnoreCase("soldado")) {
                            ind = 4;
                        }
                        if (p.type.equalsIgnoreCase("cabo")) {
                            ind = 5;
                        }
                        if (p.type.equalsIgnoreCase("marechal")) {
                            ind = 6;
                        }
                        if (!p.isPlayer) {
                            ind += 7;
                        }

                        g.drawImage(imgs[ind], p.x, p.y, null);
                    }
                }
            };
        };
        frame.setBounds(200 , 200, 500, 380);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pnTitle = new JPanel(new BorderLayout());
        JLabel lbTitle = new JLabel("Vença a guerra!");

        Font fontTitle = new Font("Arial", Font.BOLD, 54);
        lbTitle.setLocation(200, 200);
        lbTitle.setFont(fontTitle);
        lbTitle.setForeground(new Color(255, 255, 255));
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        pnTitle.add(lbTitle, BorderLayout.CENTER);
        
        pnTitle.setBackground(new Color(0, 0, 0));

        Font fontMenu = new Font("Arial", Font.BOLD, 24);
        JPanel toolSection = new JPanel();
        JLabel texto1 = new JLabel("Dicas", JLabel.CENTER);
        texto1.setFont(fontMenu);
        JLabel texto2 = new JLabel("Quantidade restantes: blablabla");
        JLabel texto3 = new JLabel("Resultado: blablabla");
        JButton botao = new JButton("Usar");
        
        toolSection.setLayout(new GridLayout(4, 1));
        toolSection.add(texto1);
        toolSection.add(texto2);
        toolSection.add(texto3);
        toolSection.add(botao);
        
        JPanel menuSection = new JPanel();
        JLabel menuTexto1 = new JLabel("Menu", JLabel.CENTER);
        menuTexto1.setFont(fontMenu);
        JButton menuBotao = new JButton("Sair");
        
        menuSection.setLayout(new GridLayout(2, 1));
        menuSection.add(menuTexto1);
        menuSection.add(menuBotao);
        
        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(toolSection, BorderLayout.NORTH);
        eastPanel.add(menuSection, BorderLayout.SOUTH);
        
        frame.add(pnTitle, BorderLayout.SOUTH);
        frame.add(pn, BorderLayout.CENTER);
        frame.add(eastPanel, BorderLayout.EAST);
        
        
        frame.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null) {
                    selectedPiece.x = e.getX() - 32;
                    selectedPiece.y = e.getY() - 32;
                    frame.repaint();
                }
            }
        });
        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                selectedPiece = getPiece(e.getX(), e.getY());
                System.out.println(selectedPiece.type + " " + selectedPiece.xp + " " + selectedPiece.yp);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX() / 64, e.getY() / 64);
                frame.repaint();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    };

    public static Piece getPiece(int x, int y) {
        int xp = x / 64;
        int yp = y / 64;
        for (Piece p : ps) {
            if (p.xp == xp && p.yp == yp) {
                return p;
            }
        }
        return null;
    }
}
