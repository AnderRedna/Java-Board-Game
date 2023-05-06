package BoardGame;

import java.util.LinkedList;

public class Soldier extends Piece {

    public Soldier(String name, int xp, int yp, int level, String type, LinkedList<Piece> ps) {
        super(name, xp, yp, level, type, ps);
    }

    @Override
    public void move(int xp, int yp, String type) {
        if (type == "player") {
            int xDistance = xp - this.xp;
            int yDistance = yp - this.yp;
            if ((xDistance != 0 && yDistance == 0) || (xDistance == 0 && yDistance > 0)) {
                int xStep = Integer.signum(xDistance);
                int yStep = Integer.signum(yDistance);
                int xCurrent = this.xp + xStep;
                int yCurrent = this.yp + yStep;
                while (xCurrent != xp || yCurrent != yp) {
                    Piece obstacle = ChessGame.getPiece(xCurrent * 64, yCurrent * 64);
                    if (obstacle != null) {
                        if (obstacle.type != type && obstacle.type != "river") {
                            obstacle.kill(this.type, this.name, this.level, this);
                            this.xp = xCurrent;
                            this.yp = yCurrent;
                            x = xCurrent * 64;
                            y = yCurrent * 64;
                            return;
                        } else {
                            keepPosition(this.xp, this.yp);
                            return;
                        }
                    }
                    xCurrent += xStep;
                    yCurrent += yStep;
                }
                Piece finalPositionPiece = ChessGame.getPiece(xp * 64, yp * 64);
                if (finalPositionPiece != null) {
                    if (finalPositionPiece.type != type && finalPositionPiece.type != "river") {
                        finalPositionPiece.kill(this.type, this.name, this.level, this);
                        this.xp = xp;
                        this.yp = yp;
                        x = xp * 64;
                        y = yp * 64;
                        return;
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
                return;
            }
        }
    }
    

    
}

