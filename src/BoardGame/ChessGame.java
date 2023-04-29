package BoardGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessGame {
    public static LinkedList<Piece> ps = new LinkedList<Piece>();
    public static Piece selectedPiece = null;
    public static void main(String[] args) throws IOException {
        BufferedImage all = ImageIO.read(new File("C:\\chess.png"));
        Image imgs[] = new Image[14];
        int ind = 0;
        for(int y = 0; y < 400; y+=200){
            for(int x = 0; x < 1400; x+=200){
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        Piece player = new Piece("bomba", 1, 0, 0, true, ps);
        Piece player2 = new Piece("bandeira", 0, 0, 0, true, ps);
        Piece player3 = new Piece("soldado", 2, 0, 0, true, ps);
        Piece player4 = new Piece("marechal", 3, 0, 0, true, ps);
        Piece player5 = new Piece("espiao", 4, 0, 0, true, ps);
        Piece player6 = new Piece("cabo", 0, 1, 0, true, ps);

        Piece enemy = new Piece ("bandeira", 0, 4, 0, false, ps);
        Piece enemy2 = new Piece ("bomba", 1, 4, 0, false, ps);
        Piece enemy3 = new Piece ("soldado", 2, 4, 0, false, ps);
        Piece enemy4 = new Piece ("marechal", 3, 4, 0, false, ps);
        Piece enemy5 = new Piece ("espiao", 4, 4, 0, false, ps);
        Piece enemy6 = new Piece ("cabo", 0, 3, 0, false, ps);
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 1000, 1000);
        JPanel pn = new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean white = true;
            for(int y= 0;y<5;y++){
                for(int x= 0;x<5;x++){
                    if(white){
                        g.setColor(new Color(107,147, 86));
                    }
                    if(!white){
                        g.setColor(new Color(167, 197, 106));
                    }
                    g.fillRect(x*64, y*64, 64, 64);
                    white = !white;
                }
                for(Piece p: ps){
                    int ind = 0;
                    if(p.type.equalsIgnoreCase("bandeira")){
                        ind = 1;
                    }
                    if(p.type.equalsIgnoreCase("bomba")){
                        ind = 2;
                    }
                    if(p.type.equalsIgnoreCase("espiao")){
                        ind = 3;
                    }
                    if(p.type.equalsIgnoreCase("soldado")){
                        ind = 4;
                    }
                    if(p.type.equalsIgnoreCase("cabo")){
                        ind = 5;
                    }
                    if(p.type.equalsIgnoreCase("marechal")){
                        ind = 6;
                    }
                    if(!p.isPlayer){
                        ind+=7;
                    }

                    g.drawImage(imgs[ind], p.x, p.y, null);
                }
            }
        };
    };
    frame.add(pn);
    frame.addMouseMotionListener(new MouseMotionListener() {
            
            @Override
            public void mouseMoved(MouseEvent e) {
            }
    
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece != null){
                    selectedPiece.x = e.getX()-32;
                    selectedPiece.y = e.getY()-32;
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
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            selectedPiece.move(e.getX()/64, e.getY()/64);
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

    public static Piece getPiece(int x, int y){
        int xp = x/64;
        int yp = y/64;
        for(Piece p: ps){
            if(p.xp == xp && p.yp == yp){
                return p;
            }
        }
        return null;
    }
}
