package BoardGame;

import java.util.LinkedList;

public class Bomb extends Piece {

    public Bomb(String name, int xp, int yp, int level, String type, LinkedList<Piece> ps) {
        super(name, xp, yp, level, type, ps);
    }

    @Override
    public void move(int xp, int yp, String type) {
        if(type == "player"){
            System.out.println("Player bomb");
            keepPosition(xp, yp);
        }else{
            System.out.println("Enemy bomb");
            keepPosition(xp, yp);
        }
    }
}

