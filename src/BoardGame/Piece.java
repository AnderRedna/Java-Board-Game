package BoardGame;

import java.util.LinkedList;


public class Piece {
    int xp;
    int yp;
    int x;
    int y;
    int level;
    String type;
    boolean isPlayer;
    LinkedList<Piece> ps;

    public Piece(String type, int xp, int yp, int level, boolean isPlayer, LinkedList<Piece> ps){
        this.type = type;
        this.xp = xp;
        this.yp = yp;
        x = xp*64;
        y = yp*64;
        this.level = level;
        this.isPlayer = isPlayer;
        this.ps = ps;
        ps.add(this);
    }

    public void move(int xp,int yp){
        if(ChessGame.getPiece(xp*64, yp*64) != null){
            if(ChessGame.getPiece(xp*64, yp*64).isPlayer != isPlayer){
                ChessGame.getPiece(xp*64, yp*64).kill();
            }else{
                x = this.xp*64;
                y = this.yp*64;
                return;
            }
        }
        this.xp=xp;
        this.yp=yp;
        x = xp*64;
        y = yp*64;
    }
    public void kill(){
        ps.remove(this);
    }
    
}
