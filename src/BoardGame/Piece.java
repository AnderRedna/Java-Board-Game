package BoardGame;

import java.util.LinkedList;

public abstract class Piece {
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

    public abstract void move(int xp, int yp, String type);

    public void kill(String type, String name, int level, Piece p){
        System.out.println("Killing " + this.name);
        System.out.println("Killed by " + name);

        if(p.name == this.name){
            ps.remove(this);
            ps.remove(p);
        }
        if(p.name == "corporal" && this.name == "bomb"){    
            System.out.println("Corporal defused bomb");
            ps.remove(this);
            return;
        }else if(p.name == "spy" && this.name == "marshal"){
            System.out.println("Spy killed marshal");
            ps.remove(this);
            return;
        }else{
            if(p.compareLevels(this)){
                System.out.println("Killed by " + name);
                ps.remove(this);
                return;
            }else{
                System.out.println("Killed by " + name);
                ps.remove(p);
                return;
            }

        }
        }

    public void keepPosition(int xp, int yp){
        x = this.xp * 64;
        y = this.yp * 64;
    }

    public Boolean compareLevels(Piece p){
        return this.level > p.level;
    }
}
