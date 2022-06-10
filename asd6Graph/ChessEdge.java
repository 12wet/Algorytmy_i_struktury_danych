public class ChessEdge {
    private final Pair dest;
    int weight;
    
    public ChessEdge(final Pair dest) {
        this.dest = dest;
    }

    public String toString(){
        return dest + "<"+ weight +">";
    }

    public Pair getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    
}
