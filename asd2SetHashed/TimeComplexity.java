import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Random;

public class TimeComplexity {
    
    static String fileNameB;
    static String fileNameB2;
    
    static{
        fileNameB = "dataB.dat";
        fileNameB2 = "dataB2.dat";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter writerB = new BufferedWriter(new FileWriter(fileNameB));
        BufferedWriter writerB2 = new BufferedWriter(new FileWriter(fileNameB2));

        //SetHashed
        for(int i = 1; i < 1000; i++){
            SetHashed s = new SetHashed(50);

            Random rand = new Random();
            System.out.println("Hi");
            while(s.getCurrentSize() != i)
                s.insert(rand.nextInt(10000));

            int random = 0;
            do{
                System.out.println("Trying hard");
                random = rand.nextInt(10000);
            }while(s.contains(random));

            long start = System.nanoTime();
            s.insert(random);
            long end = System.nanoTime() - start;
            System.out.println("Writing: " + i);
            writerB.write(i + " " + end + "\n");
        }
        writerB.close();

        for(int i = 1; i < 1000; i++){
            SetHashed s = new SetHashed(50);
            SetHashed s2 = new SetHashed(50);

            Random rand = new Random();

            while(s.getCurrentSize() != i)
                s.insert(rand.nextInt(10000));

            while(s2.getCurrentSize() != i)
                s2.insert(rand.nextInt(10000));

            long start = System.nanoTime();
            s.intersection(s2);
            long end = System.nanoTime() - start;
            writerB2.write(i + " " + end + "\n");
        }
        writerB2.close();
    }
}
