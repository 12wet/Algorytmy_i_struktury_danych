import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TimeComplexity {
    
    static String fileNameA;
    
    static{
        fileNameA = "dataA.dat";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter writerA = new BufferedWriter(new FileWriter(fileNameA));

        //SetHashed
        for(int i = 1; i < 1000; i++){
            long end = 0;

            for(int j = 1; j < 1000; j++){
                Graph g = new Graph();

                Random rand = new Random();
                while(g.getSize() != i){
                    g.addVertex(rand.nextInt(1000));
                    g.addEdge(rand.nextInt(1000), rand.nextInt(1000));
                }

                int random = 0;

                do{
                    random = rand.nextInt(1000);
                }while(g.getVertexValue(random) == -1);

                long start = System.nanoTime();
                g.removeVertex(random);
                end += System.nanoTime() - start;
            }
            System.out.println(i);
            writerA.write(i + " " + end + "\n");
        }
        writerA.close();
    }
}
