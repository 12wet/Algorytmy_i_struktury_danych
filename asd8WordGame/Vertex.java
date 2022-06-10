import java.util.ArrayList;
import java.util.List;

public class Vertex{
    private final List<Edge> edges;
    private String val;

    public Vertex(String val) {
        this.edges = new ArrayList<>();
        this.val = val;
    }

    public Vertex(){
        this(null);
    }

    public String toString(){
        String toReturn = "[ ";
        for(Edge edg : edges){
            toReturn += edg.getDest() + "<" + edg.getWeight() + "> ";
        }
        return toReturn += "]";
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public void addEdge(String edge){
        if(hasEdge(edge) == null)
            edges.add(new Edge(edge));
    }

    public void removeEdge(String edge){
        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).getDest().equals(edge)){
                edges.remove(i);
                break;
            }     
        }
    }

    public List<Edge> getEdges(){
        return edges;
    }
        
    public Edge hasEdge(String edge){
        for(Edge edg : edges){
            if(edg.getDest().equals(edge))
                { return edg; }
        }
        return null;
    }
}