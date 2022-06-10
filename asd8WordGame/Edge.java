public class Edge {
    private final String dest;
    int weight;
    
    public Edge(final String dest) {
        this.dest = dest;
    }

    public String toString(){
        return dest + "<"+ weight +">";
    }

    public String getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
