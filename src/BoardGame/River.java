package BoardGame;

import java.util.LinkedList;

public class River extends Piece{
    public River(String name, int xp, int yp, int level, String type, LinkedList<Piece> ps) {
        super(name, xp, yp, level, type, ps);
    }

    @Override
    public void move(int xp, int yp, String type) {
        keepPosition(this.xp, this.yp);
    }
    
}
