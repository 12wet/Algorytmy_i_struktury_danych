import java.util.ArrayList;
import java.util.List;

public class Pair {
    public List<Pair> visited;
    int value = 0;
    int x;
    int y;

    public Pair(int x, int y) {
        visited = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    static List<Pair> legalMoves(Pair p){
        List<Pair> moves = new ArrayList<>();
        // -1 -2
        int newX = p.getX() - 1;
        int newY = p.getY() - 2;
        if(8 >= newX && newX >= 1 &&
           8 >= newY && newY >= 1)
            moves.add(new Pair(newX, newY));

        // +1 -2
        newX = p.getX() + 1;
        newY = p.getY() - 2;
        if(8 >= newX && newX >= 1 &&
           8 >= newY && newY >= 1)
            moves.add(new Pair(newX, newY));

        // +2 -1
        newX = p.getX() + 2;
        newY = p.getY() - 1;
        if(8 >= newX && newX >= 1 &&
           8 >= newY && newY >= 1)
            moves.add(new Pair(newX, newY));

        // +2 +1
        newX = p.getX() + 2;
        newY = p.getY() + 1;
        if(8 >= newX && newX >= 1 &&
           8 >= newY && newY >= 1)
            moves.add(new Pair(newX, newY));

        // +1 +2
        newX = p.getX() + 1;
        newY = p.getY() + 2;
        if(8 >= newX && newX >= 1 &&
           8 >= newY && newY >= 1)
            moves.add(new Pair(newX, newY));

        // -1 +2
        newX = p.getX() - 1;
        newY = p.getY() + 2;
        if(8 >= newX && newX >= 1 &&
           8 >= newY && newY >= 1)
            moves.add(new Pair(newX, newY));

        // -2 +1
        newX = p.getX() - 2;
        newY = p.getY() + 1;
        if(8 >= newX && newX >= 1 &&
           8 >= newY && newY >= 1)
            moves.add(new Pair(newX, newY));

        // -2 -1
        newX = p.getX() - 2;
        newY = p.getY() - 1;
        if(8 >= newX && newX >= 1 &&
           8 >= newY && newY >= 1)
            moves.add(new Pair(newX, newY));


        return moves;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o){
       if (!(o instanceof Pair)) return false;
       if(((Pair) o).getX() == this.getX() &&
          ((Pair) o).getY() == this.getY()) return true;
        return false;
    }
}
