import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph{
    private Map<String, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    public String toString(){
        String toReturn = "{ ";
        for(Map.Entry<String, Vertex> vert : vertices.entrySet()){
            toReturn += vert.getKey() + "(" + vert.getValue().getVal() + ")->";
            toReturn += vert.getValue() + " ";
        }
        return toReturn += "}";
    }

    public Collection<Vertex> getVertices(){
        return vertices.values();
    }

    public Vertex getVertex(String val){
        return vertices.get(val);
    }

    boolean adjecent(String x, String y){
        return vertices.get(x).hasEdge(y) != null ?
            true : false;
    }

    List<String> neighbours(String x){
        return vertices.get(x)
        .getEdges()
        .stream()
        .map(Edge::getDest)
        .collect(Collectors.toList());
    }

    void addVertex(String x){
        vertices.putIfAbsent(x, new Vertex(x));
    }

    void removeVertex(String x){
        vertices.values()
            .stream()
            .filter(vert -> vert.hasEdge(x) != null)
            .forEach(vert -> vert.removeEdge(x));

        vertices.remove(x);
    }

    void addEdge(String x, String y){
        vertices.get(x).addEdge(y);
    }

    void addDiEdge(String x, String y){
        vertices.get(x).addEdge(y);
        vertices.get(y).addEdge(x);
    }

    void removeEdge(String x, String y){
        vertices.get(x).removeEdge(y);
    }

    void removeDiEdge(String x, String y){
        vertices.get(x).removeEdge(y);
        vertices.get(y).removeEdge(x);
    }

    String getVertexValue(String x){
        return vertices.get(x).getVal();
    }

    void setVertexValue(String x, String val){
        vertices.get(x).setVal(val);
    }

    int getEdgeValue(String x, String y){
        return vertices.get(x).hasEdge(y).getWeight();
    }
    
    void setEdgeValue(String x, String y, int weight){
        vertices.get(x).hasEdge(y).setWeight(weight);
    }

    void setDiEdgeValue(String x, String y, int weight){
        vertices.get(x).hasEdge(y).setWeight(weight);
        vertices.get(y).hasEdge(x).setWeight(weight);
    }

    void saveToSvgFile(String filename) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".dot"));
        writer.write("digraph {\n");
        for(Map.Entry<String, Vertex> vertex : vertices.entrySet()){
            for(Edge edge : vertex.getValue().getEdges()){
                writer.write("\t" + vertex.getKey() + " -> " + edge.getDest() + "\n");
            }
        }
        writer.write("}");
        writer.close();
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("circo -Tpng " + filename + ".dot -o " + filename + ".png");
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}