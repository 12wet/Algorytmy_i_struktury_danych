public class Edge {
    private final int dest;
    int weight;
    
    public Edge(final int dest) {
        this.dest = dest;
    }

    public String toString(){
        return Integer.toString(dest) + "<"+ weight +">";
    }

    public int getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    
}
