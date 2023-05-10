package BoardGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthStyleFactory;

public class ChessGame {
    private static JFrame frame;
    public static int dicasRestantes = 2;

    public static LinkedList<Piece> ps = new LinkedList<Piece>();
    public static Piece selectedPiece = null;

    public static void populateChess(LinkedList<Piece> ps) {
    String[] playerPieces = {"spy", "bomb", "bomb", "corporal", "corporal", "marshal", "soldier", "soldier", "soldier"};
    String[] enemyPieces = {"bomb", "bomb", "corporal", "corporal", "soldier", "soldier", "soldier", "spy", "marshal"};
    Random random = new Random();

    Prisoner prisioneiroPlayer = new Prisoner("prisoner", random.nextInt(5), 0, 0, "player", ps);
    Prisoner prisioneiroEnemy = new Prisoner("prisoner", random.nextInt(5), 4, 0, "enemy", ps);
    River river = new River("river", random.nextInt(5), 2, 1000, "river", ps);

    for (int i = 0; i < playerPieces.length; i++) {
        int xp, yp;
        do {
            xp = random.nextInt(5);
            yp = random.nextInt(2);
        } while (getPiece(xp * 64, yp * 64) != null);
        switch (playerPieces[i]) {
            case "bomb":
                Bomb bombaPlayer = new Bomb(playerPieces[i], xp, yp, 100, "player", ps);
                break;
            case "soldier":
                Soldier soldadoPlayer = new Soldier(playerPieces[i], xp, yp, 2, "player", ps);
                break;
            case "marshal":
                Marshal marechalPlayer = new Marshal(playerPieces[i], xp, yp, 10, "player", ps);
                break;
            case "spy":
                Spy espiaoPlayer = new Spy(playerPieces[i], xp, yp, 1, "player", ps);
                break;
            case "corporal":
                Corporal caboPlayer = new Corporal(playerPieces[i], xp, yp, 3, "player", ps);
                break;
            default:
                System.out.println("ErroAlly");
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
            case "bomb":
                Bomb bombaEnemy = new Bomb(enemyPieces[i], xp, yp, 100, "enemy", ps);
                break;
            case "soldier":
                Soldier soldadoEnemy = new Soldier(enemyPieces[i], xp, yp, 2, "enemy", ps);
                break;
            case "marshal":
                Marshal marechalEnemy = new Marshal(enemyPieces[i], xp, yp, 10, "enemy", ps);
                break;
            case "spy":
                Spy espiaoEnemy = new Spy(enemyPieces[i], xp, yp, 1, "enemy", ps);
                break;
            case "corporal":
                Corporal caboEnemy = new Corporal(enemyPieces[i], xp, yp, 3, "enemy", ps);
                break;
            default:
                System.out.println("ErroEnemy");
                break;
        }
    }
}

public static Boolean isShowed = true;

    public ChessGame() throws IOException {
        this.init();
    }

    public void init() throws IOException {
        frame = new JFrame();
        populateChess(ps);
        BufferedImage all = ImageIO.read(new File("C:\\chess.png"));
        Image imgs[] = new Image[16];
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1600; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }

        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                isShowed = true;
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
                            if(p.type.equalsIgnoreCase("enemy")){
                                g.drawImage(imgs[8], p.x, p.y, null);
                                continue;
                            }else{
                                int ind = 0;
                                if (p.type.equalsIgnoreCase("river")) {
                                    ind = 7;
                                }
                                if (p.name.equalsIgnoreCase("prisoner")) {
                                    ind = 1;
                                }
                                if (p.name.equalsIgnoreCase("bomb")) {
                                    ind = 2;
                                }
                                if (p.name.equalsIgnoreCase("spy")) {
                                    ind = 3;
                                }
                                if (p.name.equalsIgnoreCase("soldier")) {
                                    ind = 4;
                                }
                                if (p.name.equalsIgnoreCase("corporal")) {
                                    ind = 5;
                                }
                                if (p.name.equalsIgnoreCase("marshal")) {
                                    ind = 6;
                                }
                                g.drawImage(imgs[ind], p.x, p.y, null);
                            }
                        }
                    }
                }
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
        toolSection.setSize(180, 200);
        JLabel texto1 = new JLabel("Dicas", JLabel.CENTER);
        texto1.setFont(fontMenu);
        texto1.setPreferredSize(new Dimension(180, 30)); // Definindo tamanho fixo para a label
        JLabel labelDicasRestante = new JLabel("Dicas restantes: " + dicasRestantes, JLabel.CENTER);
        labelDicasRestante.setPreferredSize(new Dimension(180, 30)); // Definindo tamanho fixo para a label
        JLabel labDicasResult = new JLabel("Aguardando..." , JLabel.CENTER);
        labDicasResult.setPreferredSize(new Dimension(180, 30)); // Definindo tamanho fixo para a label
        JButton hideButton = new JButton("Debug");
        JButton btnDica = new JButton("Dica");

        toolSection.setLayout(new GridLayout(5, 1));
        toolSection.add(texto1);
        toolSection.add(labelDicasRestante);
        toolSection.add(labDicasResult);
        toolSection.add(btnDica);
        toolSection.add(hideButton);

        
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
                if (selectedPiece != null && selectedPiece.type.equalsIgnoreCase("player")) {
                    selectedPiece.x = e.getX() - 32;
                    selectedPiece.y = e.getY() - 32;
                    frame.repaint();
                }
            }
        });
        hideButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(isShowed == true){
                        Graphics g = frame.getGraphics();
                        for (Piece p : ps) {
                            if(p.type.equalsIgnoreCase("enemy")){
                                int ind = 0;
                                if (p.name.equalsIgnoreCase("prisoner")) {
                                    ind = 9;
                                }
                                if (p.name.equalsIgnoreCase("bomb")) {
                                    ind = 10;
                                }
                                if (p.name.equalsIgnoreCase("spy")) {
                                    ind = 11;
                                }
                                if (p.name.equalsIgnoreCase("soldier")) {
                                    ind = 12;
                                }
                                if (p.name.equalsIgnoreCase("corporal")) {
                                    ind = 13;
                                }
                                if (p.name.equalsIgnoreCase("marshal")) {
                                    ind = 14;
                                }
                                g.drawImage(imgs[ind], p.x, p.y, null);
                            }
                        }
                        isShowed = false;
                    }else{
                        isShowed = true;
                        frame.repaint();
                    }
                }
        });
        

        btnDica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dicasRestantes > 0) {
                    System.out.println("Selecione uma coluna:");
                    pn.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int coluna = e.getX() / 64;
                            if (colunaContemBomba(coluna)) {
                                dicasRestantes--;
                                labelDicasRestante.setText("Dicas restante: " + dicasRestantes);
                                labelDicasRestante.repaint();
                                labDicasResult.setText("Há bombas na coluna " + coluna);
                                labDicasResult.repaint();
                            } else {
                                dicasRestantes--;
                                labDicasResult.setText("Não há bombas na coluna " + coluna);
                                labDicasResult.repaint();
                            }
                            if (dicasRestantes == 0){
                                labelDicasRestante.setText("Você já usou todas as dicas!");
                                labelDicasRestante.repaint();
                                btnDica.setEnabled(false);
                            }
                            pn.removeMouseListener(this);
                        }
        
                        @Override
                        public void mousePressed(MouseEvent e) {
                        }
        
                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }
        
                        @Override
                        public void mouseEntered(MouseEvent e) {
                        }
        
                        @Override
                        public void mouseExited(MouseEvent e) {
                        }
                    });
                } else {
                    System.out.println("Você já usou todas as dicas!");
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
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if(selectedPiece.type.equalsIgnoreCase("enemy")){
                    return;
                }else{
                    selectedPiece.move(e.getX() / 64, e.getY() / 64, selectedPiece.type);
                    frame.repaint();
                }
                enemyMove(ps);
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
    }
    
    public static void main(String[] args) throws IOException {
        new ChessGame();
    };
    public static void fecharJogo(){
        // Fechamento do JFrame utilizando a variável frame
        frame.dispose();
    }

    
    public static void enemyMove(LinkedList<Piece> p) {
        ArrayList<Piece> enemies = new ArrayList<>();
        ArrayList<Piece> movableEnemies = new ArrayList<>();
    
        for (Piece piece : p) {
            if (piece.type.equalsIgnoreCase("enemy") && !piece.name.equalsIgnoreCase("bomb")) {
                enemies.add(piece);
                if (hasAvailableMoves(piece)) {
                    movableEnemies.add(piece);
                }
            }
        }
    
        if (movableEnemies.size() > 0) {
            int randomEnemy = (int) (Math.random() * movableEnemies.size());
            Piece enemy = movableEnemies.get(randomEnemy);
            System.out.println("Peça inimiga selecionada: " + enemy.name);
            ArrayList<Point> availableMoves = new ArrayList<>();
            int[] xMoves = {-1, 0, 1};
            int[] yMoves = {0, -1, 0};
            for (int i = 0; i < xMoves.length; i++) {
                int newRow = enemy.xp + xMoves[i];
                int newCol = enemy.yp + yMoves[i];
                if ((newRow >= 0 && newRow < 5) && (newCol >= 0 && newCol < 5)) {
                    if (newRow == enemy.xp && newCol == enemy.yp) {
                        continue;
                    }
                    Piece destPiece = getPiece(newRow * 64, newCol * 64);
                    if ((destPiece == null || destPiece.type != enemy.type) && !isRiver(newRow * 64, newCol * 64)) {
                        availableMoves.add(new Point(newRow, newCol));
                    }
                }
            }
    
            if (availableMoves.size() > 0) {
                int randomMove = (int) (Math.random() * availableMoves.size());
                Point destination = availableMoves.get(randomMove);
                switch (enemy.name.toLowerCase()) {
                    case "spy":
                        ((Spy) enemy).move(destination.x, destination.y, enemy.type);
                        break;
                    case "corporal":
                        ((Corporal) enemy).move(destination.x, destination.y, enemy.type);
                        break;
                    case "marshal":
                        ((Marshal) enemy).move(destination.x, destination.y, enemy.type);
                        break;
                    case "soldier":
                        ((Soldier) enemy).move(destination.x, destination.y, enemy.type);
                        break;
                    case "prisoner":
                        ((Prisoner) enemy).move(destination.x, destination.y, enemy.type);
                        break;
                    default:
                        System.out.println("Peça inimiga selecionada não é movível.");
                        break;
                }
            } else {
                System.out.println("A peça inimiga selecionada não tem movimentos disponíveis.");
            }
            
        } else if (enemies.size() > 0) {
            System.out.println("Nenhuma peça inimiga tem movimentos disponíveis.");
        } else {
            System.out.println("Não há peças inimigas para mover.");
        }
    }
    
    
    public static boolean hasAvailableMoves(Piece enemy) {
        int[] xMoves = {-1, 0, 1};
        int[] yMoves = {0, -1, 0};
        for (int i = 0; i < xMoves.length; i++) {
            int newRow = enemy.xp + xMoves[i];
            int newCol = enemy.yp + yMoves[i];
            if ((newRow >= 0 && newRow < 5) && (newCol >= 0 && newCol < 5)) {
                Piece destPiece = getPiece(newRow * 64, newCol * 64);
                if ((destPiece == null || destPiece.type != enemy.type) && !isRiver(newRow * 64, newCol * 64)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
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

    public static boolean isRiver(int x, int y) {
        int xp = x / 64;
        int yp = y / 64;
        for (Piece p : ps) {
            if (p.xp == xp && p.yp == yp && p.type.equalsIgnoreCase("river")) {
                return true;
            }
        }
        return false;
    }

    public static boolean colunaContemBomba(int coluna) {
        for (Piece p : ps) {
            if (p.xp == coluna && p.type.equalsIgnoreCase("enemy") && p.name.equalsIgnoreCase("bomb")) {
                return true;
            }
        }
        return false;
    }
    
    
}
