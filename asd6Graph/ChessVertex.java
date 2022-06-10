import java.util.ArrayList;
import java.util.List;

public class ChessVertex{
    private final List<ChessEdge> edges;
    private Pair val;

    public ChessVertex(Pair val) {
        this.edges = new ArrayList<>();
        this.val = val;
    }

    public ChessVertex(){
        this(null);
    }

    public String toString(){
        String toReturn = "[ ";
        for(ChessEdge edg : edges){
            toReturn += edg.getDest() + "<" + edg.getWeight() + "> ";
        }
        return toReturn += "]";
    }

    public Pair getVal() {
        return val;
    }

    public void setVal(Pair val) {
        this.val = val;
    }

    public void addChessEdge(Pair chessEdge){
        if(hasChessEdge(chessEdge) == null)
        edges.add(new ChessEdge(chessEdge));
    }

    public void removeChessEdge(Pair chessEdge){
        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).getDest().equals(chessEdge)){
                edges.remove(i);
                break;
            }     
        }
    }

    public List<ChessEdge> getChessEdges(){
        return edges;
    }
        
    public ChessEdge hasChessEdge(Pair edge){
        for(ChessEdge edg : edges){
            if(edg.getDest().equals(edge))
                { return edg; }
        }
        return null;
    }
}