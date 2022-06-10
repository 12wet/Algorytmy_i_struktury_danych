import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskSet6C {

    public static void main(String[] args) {
        TaskSet6C t = new TaskSet6C();
        ChessGraph g = t.buildGraph();
        List<Pair> route = t.findRoute(g, 7, 3);
        System.out.println(route.size() + ": " + route);
    }

    ChessGraph buildGraph(){
        ChessGraph g = new ChessGraph();

        for(int x = 1; x < 9; x++){
            for(int y = 1; y < 9; y++){
                g.addChessVertex(new Pair(x, y));
            }
        }

        for(ChessVertex vert : g.getVertices().values()){
            for(Pair move : Pair.legalMoves(vert.getVal())){
                g.addChessEdge(vert.getVal(), move);
            }
        }

        return g;
    }

    List<Pair> findRoute(ChessGraph g, int x, int y){
        LinkedList<Pair> route = new LinkedList<>();

        Pair start = new Pair(x, y);
        route.add(start);

        return search(g, route);
    }

    List<Pair> search(ChessGraph g, LinkedList<Pair> route){
        
        if(route.size() == 64){
            for(Pair neigh : g.neighbours(route.peekLast())){
                if(route.peekFirst().equals(neigh))
                    return route;
            }
            return route;
        }

        LinkedList<Pair> nextNeighbours = new LinkedList<>();
        ArrayList<Pair> sortedNeighs = new ArrayList<>();
        Pair current = route.peekLast();

        for(Pair nextNeigh : g.neighbours(current)){
            if( !route.stream().anyMatch(pair -> nextNeigh.equals(pair)) &&
                !current.visited.stream().anyMatch(pair -> nextNeigh.equals(pair)))
                nextNeighbours.add(nextNeigh);
        }

        if(nextNeighbours.size() == 0){
            Pair visited = route.removeLast();
            visited.visited.clear();
            route.peekLast().visited.add(visited);
            return route;
        }

        int maxNeighNum = 0;
        for(Pair neigh : nextNeighbours){
            int neighNum = 0;
            for(Pair neighsNeigh : g.neighbours(neigh)){
                if( !route.stream().anyMatch(pair -> neighsNeigh.equals(pair)) &&
                    !current.visited.stream().anyMatch(pair -> neighsNeigh.equals(pair)))
                    neighNum++;
            }
            neigh.setValue(neighNum);
            if(neighNum > maxNeighNum) maxNeighNum = neighNum;
        }

        while(maxNeighNum >= 0){
            for(Pair neigh : nextNeighbours){
                if(neigh.getValue() == maxNeighNum)
                    sortedNeighs.add(neigh);
            }
            maxNeighNum--;
        }
        
        for(int i = 1; i <= sortedNeighs.size(); i++){
            route.add(sortedNeighs.get(sortedNeighs.size()-i));
            search(g, route);
        }

        return route;
    }
    
}
