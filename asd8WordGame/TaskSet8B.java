import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskSet8B {
    static Graph g = new Graph();

    static void topologicalSortUtility(Vertex vert, Map<String, Boolean> visited, List<String> tasks){
        visited.put(vert.getVal(), true);

        for(String str : g.neighbours(vert.getVal())){
            if(visited.get(str) == false)
                topologicalSortUtility(g.getVertex(str), visited, tasks);
        }

        tasks.add(vert.getVal());
    }

    static List<String> topologicalSort(){
        List<String> tasks = new ArrayList<String>();
        Map<String, Boolean> visited = new HashMap<>();
        for(Vertex vert : g.getVertices()){
            visited.put(vert.getVal(), false);
        }

        for(Vertex vert : g.getVertices()){
            if(visited.get(vert.getVal()) == false)
                topologicalSortUtility(vert, visited, tasks);
        }

        return tasks;
    }
    
    public static void main(String[] args) throws IOException {
        
        g.addVertex("\"Weź 1 jajko\"");
        g.addVertex("\"Weź 1 łyżkę oleju\"");
        g.addVertex("\"Weź 3/4 szklanki mleka\"");
        g.addVertex("\"Weź 1 szklankę proszku do naleśników\"");
        g.addVertex("\"Zmieszaj składniki\"");
        g.addVertex("\"Podgrzej syrop klonowy\"");
        g.addVertex("\"Zjedz rumiany naleśnik polany ciepłym syropem klonowym\"");
        g.addVertex("\"Nagrzej patelnię\"");
        g.addVertex("\"Wylej część ciasta naleśnikowego na patelnię\"");
        g.addVertex("\"Gdy naleśnik jest rumiany od spodu przewróć go i podpiecz z drugiej strony\"");
        g.addEdge("\"Weź 1 jajko\"", "\"Zmieszaj składniki\"");
        g.addEdge("\"Weź 1 łyżkę oleju\"", "\"Zmieszaj składniki\"");
        g.addEdge("\"Weź 3/4 szklanki mleka\"", "\"Zmieszaj składniki\"");
        g.addEdge("\"Weź 1 szklankę proszku do naleśników\"", "\"Zmieszaj składniki\"");
        g.addEdge("\"Zmieszaj składniki\"", "\"Podgrzej syrop klonowy\"");
        g.addEdge("\"Zmieszaj składniki\"", "\"Wylej część ciasta naleśnikowego na patelnię\"");
        g.addEdge("\"Nagrzej patelnię\"", "\"Wylej część ciasta naleśnikowego na patelnię\"");
        g.addEdge("\"Wylej część ciasta naleśnikowego na patelnię\"", "\"Gdy naleśnik jest rumiany od spodu przewróć go i podpiecz z drugiej strony\"");
        g.addEdge("\"Gdy naleśnik jest rumiany od spodu przewróć go i podpiecz z drugiej strony\"", "\"Zjedz rumiany naleśnik polany ciepłym syropem klonowym\"");
        g.addEdge("\"Podgrzej syrop klonowy\"", "\"Zjedz rumiany naleśnik polany ciepłym syropem klonowym\"");
        g.saveToSvgFile("recipe");

        List<String> tasks = topologicalSort();
        for(int i = 1; i <= tasks.size(); i++)
            System.out.println(i + ". " + tasks.get(tasks.size() - i));
    }

    
}
