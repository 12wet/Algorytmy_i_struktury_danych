import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TimeComplexity{
    static String fileNameA;
    static String fileNameB;
    static String fileNameD;
    static String fileNameA2;
    static String fileNameB2;
    static String fileNameD2;
    
    static{
        fileNameA = "dataA.dat";
        fileNameB = "dataB.dat";
        fileNameD = "dataD.dat";
        fileNameA2 = "dataA2.dat";
        fileNameB2 = "dataB2.dat";
        fileNameD2 = "dataD2.dat";
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter writerA = new BufferedWriter(new FileWriter(fileNameA));
        BufferedWriter writerB = new BufferedWriter(new FileWriter(fileNameB));
        BufferedWriter writerD = new BufferedWriter(new FileWriter(fileNameD));
        BufferedWriter writerA2 = new BufferedWriter(new FileWriter(fileNameA2));
        BufferedWriter writerB2 = new BufferedWriter(new FileWriter(fileNameB2));
        BufferedWriter writerD2 = new BufferedWriter(new FileWriter(fileNameD2));

        //SetSimple
        System.out.println("SetSimple");
        for(int i = 1; i < 1000; i++){
            SetSimple s = new SetSimple(i);

            for(int j = 0; j < i-1; j++){
                s.insert(j);
            }

            long start = System.nanoTime();
            s.insert(i-1);
            long end = System.nanoTime() - start;
            System.out.println("Writing: " + i + " " + end);
            writerA.write(i + " " + end + "\n");
        }
        writerA.close();
        for(int i = 1; i < 1000; i++){
            SetSimple s = new SetSimple(i);
            SetSimple s2 = new SetSimple(i);

            for(int j = 0; j < i-1; j++){
                s.insert(j);
                s2.insert(j);
            }

            long start = System.nanoTime();
            s.union(s2);
            long end = System.nanoTime() - start;
            writerA2.write(i + " " + end + "\n");
        }
        writerA2.close();
        

        //SetLinked
        System.out.println("SetLinked");
        for(int i = 10; i < 1000; i++){
            long end = 0;
            for(int k = 10; k < 100; k++){
                SetLinked s = new SetLinked();

                for(int j = 0; j < i-1; j++){
                    s.insert(j);
                }
                Random rand = new Random();
                int randomNum;
                do {
                    randomNum = rand.nextInt(i);
                } while(!s.contains(randomNum));

                long start = System.nanoTime();
                s.remove(randomNum);
                end += System.nanoTime() - start;
            }       
            writerB.write(i + " " + end + "\n");   
        }
        writerB.close();
        for(int i = 1; i < 1000; i++){
            SetLinked s = new SetLinked();
            SetLinked s2 = new SetLinked();

            while(s.size != i && s2.size != i){
                Random rand = new Random();
                Random rand2 = new Random();
                s.insert(rand.nextInt(1000));
                s2.insert(rand2.nextInt(1000));
            }

            long start = System.nanoTime();
            s.difference(s2);
            long end = System.nanoTime() - start;
            writerB2.write(i + " " + end + "\n");
        }
        writerB2.close();

        //DictionarySimple
        System.out.println("DictionarySimple");
        for(int i = 10; i < 1000; i++){
            DictionarySimple d = new DictionarySimple();

            for(int j = 0; j < i-1; j++){
                d.insert(randomString());
            }
            String str = randomString();
            long start = System.nanoTime();
            d.insert(str);
            long end = System.nanoTime() - start;
            writerD.write(i + " " + end + "\n");
        }
        writerD.close();
        for(int i = 1; i < 1000; i++){
            DictionarySimple d = new DictionarySimple();

            for(int j = 0; j < i; j++){
                d.insert(randomString());
            }
            String str = randomString();
            long start = System.nanoTime();
            d.contains(str);
            long end = System.nanoTime() - start;
            writerD2.write(i + " " + end + "\n");
        }
        writerD2.close();
    }

    //https://www.baeldung.com/java-random-string
    static String randomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 50;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
