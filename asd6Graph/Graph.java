import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph{
    private Map<Integer, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    public String toString(){
        String toReturn = "{ ";
        for(Map.Entry<Integer, Vertex> vert : vertices.entrySet()){
            toReturn += vert.getKey() + "(" + vert.getValue().getVal() + ")->";
            toReturn += vert.getValue() + " ";
        }
        return toReturn += "}";
    }

    int getSize(){
        return vertices.size();
    }

    boolean adjecent(int x, int y){
        return vertices.get(x).hasEdge(y) != null ?
            true : false;
    }

    List<Integer> neighbours(int x){
        return vertices.get(x)
        .getEdges()
        .stream()
        .map(Edge::getDest)
        .collect(Collectors.toList());
    }

    void addVertex(int x){
        vertices.putIfAbsent(x, new Vertex());
    }

    void removeVertex(int x){
        if(!vertices.containsKey(x)) return;
        vertices.values()
            .stream()
            .filter(vert -> vert.hasEdge(x) != null)
            .forEach(vert -> vert.removeEdge(x));

        vertices.remove(x);
    }

    void addEdge(int x, int y){
        if(!vertices.containsKey(x) || !vertices.containsKey(y))
            return;
        vertices.get(x).addEdge(y);
        vertices.get(y).addEdge(x);
    }

    void removeEdge(int x, int y){
        if(!vertices.containsKey(x) || !vertices.containsKey(y))
            return;
        vertices.get(x).removeEdge(y);
        vertices.get(y).removeEdge(x);
    }

    int getVertexValue(int x){
        if(!vertices.containsKey(x)) return -1;
        return vertices.get(x).getVal();
    }

    void setVertexValue(int x, int val){
        vertices.get(x).setVal(val);
    }

    int getEdgeValue(int x, int y){
        return vertices.get(x).hasEdge(y).getWeight();
    }
    
    void setEdgeValue(int x, int y, int weight){
        vertices.get(x).hasEdge(y).setWeight(weight);
        vertices.get(y).hasEdge(x).setWeight(weight);
    }

    void saveToSvgFile(String filename) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".dot"));
        writer.write("digraph {\n");
        for(Map.Entry<Integer, Vertex> vertex : vertices.entrySet()){
            for(Edge edge : vertex.getValue().getEdges()){
                writer.write("\t" + vertex.getKey() + " -> " + edge.getDest() + "\n");
            }
        }
        writer.write("}");
        writer.close();
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("circo -Tsvg " + filename + ".dot -o " + filename + ".svg");
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Graph g = new Graph();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        System.out.println(g);
        System.out.println(g.adjecent(1, 3));
        System.out.println(g.adjecent(1, 2));
        System.out.println(g.neighbours(3));
        g.removeVertex(3);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addEdge(1, 4);
        g.addEdge(4, 5);
        g.addEdge(2, 6);
        g.addEdge(3, 6);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.setEdgeValue(1, 4, 6);
        g.setEdgeValue(4, 5, 12);
        g.setEdgeValue(5, 1, 1);
        g.setEdgeValue(3, 6, -5);
        g.setVertexValue(1, 2);
        g.setVertexValue(3, 10);
        g.setVertexValue(4, 30);
        g.setVertexValue(5, 5);
        g.setVertexValue(6, -1);
        System.out.println(g);
        System.out.println(g.getEdgeValue(1, 4));
        System.out.println(g.getEdgeValue(5, 1));
        System.out.println(g.getEdgeValue(6, 3));
        System.out.println(g.getVertexValue(1));
        System.out.println(g.getVertexValue(2));
        System.out.println(g.getVertexValue(6));
        g.removeEdge(1, 4);
        g.removeEdge(5, 1);
        System.out.println(g);
        g.removeVertex(6);
        System.out.println(g);
        g.saveToSvgFile("graph");
    }
}