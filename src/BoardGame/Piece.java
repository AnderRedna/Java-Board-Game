package BoardGame;

import java.util.LinkedList;


public class Piece {
    String name;
    String type;
    int level;
    int xp;
    int yp;
    int x;
    int y;
    LinkedList<Piece> ps;

    public Piece(String name, int xp, int yp, int level, String type, LinkedList<Piece> ps){
        this.name = name;
        this.level = level;
        this.type = type;
        this.xp = xp;
        this.yp = yp;
        x = xp*64;
        y = yp*64;
        this.ps = ps;
        ps.add(this);
    }

    public void move(int xp, int yp) {
        if (((Math.abs(this.xp - xp) == 1 && this.yp == yp) || (Math.abs(this.yp - yp) == 1 && this.xp == xp))
                && !(Math.abs(this.xp - xp) == 1 && Math.abs(this.yp - yp) == 1)
                && yp > this.yp) {
            if (ChessGame.getPiece(xp * 64, yp * 64) != null) {
                if (ChessGame.getPiece(xp * 64, yp * 64).type != type && ChessGame.getPiece(xp * 64, yp * 64).type != "river") {
                    ChessGame.getPiece(xp * 64, yp * 64).kill(this.type);
                } else {
                    keepPosition(this.xp, this.yp);
                    return;
                }
            }
            this.xp = xp;
            this.yp = yp;
            x = xp * 64;
            y = yp * 64;
        } else {
            keepPosition(this.xp, this.yp);
        }
    }
    
    public void kill(String type){
        if(this.type != "river"){
            ps.remove(this);
        }
    }

    public void keepPosition(int xp, int yp){
        x = this.xp * 64;
        y = this.yp * 64;
    }
    
}
