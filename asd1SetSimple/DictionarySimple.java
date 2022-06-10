public class DictionarySimple {
    private final int MAX_SIZE;
    private final String[] dictArray;

    public DictionarySimple(){
        MAX_SIZE = 1000;
        dictArray = new String[MAX_SIZE];
    }

    public String toString(){
        String toReturn = "{ ";
        for(int i = 0; i <= MAX_SIZE-1; i++){
            if(dictArray[i] != null)
                toReturn += dictArray[i] + " ";
        }
        return toReturn + "}";

    }

    public int lookForUnoccupied(){     
        for(int i = 0; i < MAX_SIZE; i++){
            if(dictArray[i] == null) return i;
        }
        return -1;
    }

    public boolean insert(String e){
        if(contains(e) != -1) return false;
        int index;
        if((index = lookForUnoccupied()) == -1)
            throw new IllegalStateException("The Dictionary is full. Cannot call insert(String e) method.");
        dictArray[index] = e;
        return true;
    }

    public boolean remove(String e){
        int index;
        if((index = contains(e)) == -1) return false;   
        dictArray[index] = null;
        return true;
    }

    public int contains(String e){
        for(int i = 0; i <= MAX_SIZE-1; i++){
            if(dictArray[i] != null && dictArray[i].equals(e)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        DictionarySimple d = new DictionarySimple();
        d.insert("asd");
        d.insert("bcv");
        d.insert("cadsd");
        d.insert("dwerwe");
        d.insert("efasf");
        d.insert("fadsads");
        d.insert("gqeqwrqwr");
        System.out.println(d);
        d.remove("asd");
        d.remove("efasf");
        d.remove("fadsads");
        d.remove("bcv");
        d.remove("gqeqwrqwr");
        System.out.println(d);
        System.out.println(d.contains("dwerwe"));
    }
}
