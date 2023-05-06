package BoardGame;

import java.util.LinkedList;

public class Marshal extends Piece {

    public Marshal(String name, int xp, int yp, int level, String type, LinkedList<Piece> ps) {
        super(name, xp, yp, level, type, ps);
    }

    @Override
    public void move(int xp, int yp, String type) {
        if(type == "player"){
            if ((Math.abs(xp - this.xp) == 1 && yp == this.yp) || (Math.abs(yp - this.yp) == 1 && xp == this.xp && yp - this.yp == 1))  {
                if (ChessGame.getPiece(xp * 64, yp * 64) != null) {
                    if (ChessGame.getPiece(xp * 64, yp * 64).type != type && ChessGame.getPiece(xp * 64, yp * 64).type != "river") {
                        ChessGame.getPiece(xp * 64, yp * 64).kill(this.type, this.name, this.level, this);
                    } else {
                        keepPosition(this.xp, this.yp);
                        return;
                    }
                } else {
                    keepPosition(this.xp, this.yp);
                }
            this.xp = xp;
            this.yp = yp;
            x = xp * 64;
            y = yp * 64;
            } else {
            keepPosition(this.xp, this.yp);
            return;
            }
        }else{
        }
    }
}

