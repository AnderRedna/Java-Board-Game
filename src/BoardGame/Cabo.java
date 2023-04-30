package BoardGame;

import java.util.LinkedList;

public class Cabo extends Piece {

    public Cabo(int xp, int yp, int level, boolean isPlayer, LinkedList<Piece> ps) {
        super("Cabo", xp, yp, level, isPlayer, ps);
    }

    @Override
    public void move(int xp, int yp) {
        if (type.equals("Cabo")) {
            // lógica específica para o movimento do Cabo
        }
        // chama o método move da classe pai para atualizar a posição da peça
        super.move(xp, yp);
    }

}
