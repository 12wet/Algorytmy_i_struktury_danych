import java.util.ArrayList;
import java.util.List;

public class Vertex{
    private final List<Edge> edges;
    private int val;

    public Vertex(int val) {
        this.edges = new ArrayList<>();
        this.val = val;
    }

    public Vertex(){
        this(0);
    }

    public String toString(){
        String toReturn = "[ ";
        for(Edge edg : edges){
            toReturn += edg.getDest() + "<" + edg.getWeight() + "> ";
        }
        return toReturn += "]";
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void addEdge(int edge){
        if(hasEdge(edge) == null)
            edges.add(new Edge(edge));
    }

    public void removeEdge(int edge){
        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).getDest() == edge){
                edges.remove(i);
                break;
            }     
        }
    }

    public List<Edge> getEdges(){
        return edges;
    }
        
    public Edge hasEdge(int edge){
        for(Edge edg : edges){
            if(edg.getDest() == edge)
                { return edg; }
        }
        return null;
    }
}