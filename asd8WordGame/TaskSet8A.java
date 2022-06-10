import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskSet8A{
    private final String FILE_NAME = "slowa.txt";

    public static void main(String[] args) throws IOException {
        TaskSet8A t = new TaskSet8A();
        Graph g = t.buildGraph();
        t.findRoute(g, "domy", "rata");
    }

    public Graph buildGraph() throws IOException{
        Graph graph = new Graph();

        BufferedReader br
            = new BufferedReader(new FileReader(FILE_NAME));

        while(br.ready()){
            String nextWord = br.readLine();
            if(nextWord.chars().allMatch(c -> isCharacterValid((char) c) == true) &&
                nextWord.length() == 4){
                graph.addVertex(nextWord);
                for(Vertex vertex : graph.getVertices()){
                    String word = vertex.getVal();
                    int matches = 0;
                    for(int i = 0; i < word.length(); i++)
                        if(word.charAt(i) == nextWord.charAt(i)) matches++;
                    if(matches == 3)
                        graph.addDiEdge(nextWord, word);
                }
            }
        }
        br.close();

        return graph;
    }

    private boolean isCharacterValid(char c){
        return (c >= 'a' && c <= 'z');
    }

    public void findRoute(Graph g, String start, String end){
        List<String> visited = new LinkedList<>();
        LinkedList<ArrayList<String>> routes = new LinkedList<>();

        visited.add(start);
        ArrayList<String> startingRoute = new ArrayList<>();
        startingRoute.add(start);
        routes.add(startingRoute);
        
        while(!routes.isEmpty()){
            ArrayList<String> current = routes.pop();
            if(current.get(current.size()-1).equals(end)){
                System.out.println("Shortest route found:");
                System.out.println(current);
                return;
            }
            for(String neigh : g.neighbours(current.get(current.size()-1))){
                if(!visited.contains(neigh))
                    visited.add(neigh);
                else continue;
                ArrayList<String> newRoute = new ArrayList<>();
                int cap = current.size();
                for(int i = 0; i <= cap; i++){
                    if(i == cap){
                        newRoute.add(neigh);
                        break;
                    }
                    newRoute.add(current.get(i));
                }
                routes.add(newRoute);
            }
        }
        System.out.println("Route not found");
    }
}