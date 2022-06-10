import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGraph {
    private Map<Integer, ChessVertex> vertices;

    public ChessGraph() {
        this.vertices = new HashMap<>();
    }

    public String toString(){
        String toReturn = "{ ";
        for(Map.Entry<Integer, ChessVertex> vert : vertices.entrySet()){
            toReturn += vert.getKey() + "(" + vert.getValue().getVal() + ")->";
            toReturn += vert.getValue() + " ";
        }
        return toReturn += "}";
    }

    public static int getKey(Pair x){
        return Integer.parseInt(String.valueOf(x.getX()) + String.valueOf(x.getY()));
    }

    public Map<Integer, ChessVertex> getVertices(){
        return vertices;
    }

    boolean adjecent(Pair x, Pair y){
        return vertices.get(getKey(x)).hasChessEdge(y) != null ?
            true : false;
    }

    List<Pair> neighbours(Pair x){
        return vertices.get(getKey(x))
        .getChessEdges()
        .stream()
        .map(edg -> edg.getDest())
        .collect(Collectors.toList());
    }

    void addChessVertex(Pair x){
        vertices.putIfAbsent(getKey(x), new ChessVertex(x));
    }

    void removeChessVertex(Pair x){
        vertices.values()
            .stream()
            .filter(vert -> vert.hasChessEdge(x) != null)
            .forEach(vert -> vert.removeChessEdge(x));

        vertices.remove(getKey(x));
    }

    void addChessEdge(Pair x, Pair y){
        vertices.get(getKey(x)).addChessEdge(y);
        vertices.get(getKey(y)).addChessEdge(x);
    }

    void removeChessEdge(Pair x, Pair y){
        vertices.get(getKey(x)).removeChessEdge(y);
        vertices.get(getKey(y)).removeChessEdge(x);
    }

    Pair getChessVertexValue(Pair x){
        return vertices.get(getKey(x)).getVal();
    }

    void setChessVertexValue(Pair x, Pair val){
        vertices.get(getKey(x)).setVal(val);
    }

    int getChessEdgeValue(Pair x, Pair y){
        return vertices.get(getKey(x)).hasChessEdge(y).getWeight();
    }
    
    void setChessEdgeValue(Pair x, Pair y, int weight){
        vertices.get(getKey(x)).hasChessEdge(y).setWeight(weight);
        vertices.get(getKey(y)).hasChessEdge(x).setWeight(weight);
    }

    void saveToSvgFile(String filename) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".dot"));
        writer.write("digraph {\n");
        for(Map.Entry<Integer, ChessVertex> ChessVertex : vertices.entrySet()){
            for(ChessEdge ChessEdge : ChessVertex.getValue().getChessEdges()){
                writer.write("\t" + ChessVertex.getKey() + " -- " + ChessEdge.getDest() + "\n");
            }
        }
        writer.write("}");
        writer.close();
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("circo -Tsvg " + filename + ".dot -o " + filename + ".svg");
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
