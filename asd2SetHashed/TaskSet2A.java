public class TaskSet2A{

    //Liczby calkowite
    static int wholeNumberRange(int min, int max){
        return max - min + 1;
    }

    static int wholeNumberForMin(int min, int e){
        return e - min;
    }

    static void printWholeNumberSet(SetSimple set, int min, int max){
        System.out.print("{ ");
        for(int i = min; i < max; i++){
            if(set.contains(wholeNumberForMin(min, i)))
                System.out.print(i + " ");
        }
        System.out.print("}\n");
    }

    //Liczby calkowite parzyste
    static int evenedNumberRange(int min, int max){
        return (max - min) / 2;
    }

    static int evenedNumberForMin(int min, int e){
        return (e - min) / 2;
    }

    static void printEvenedNumberSet(SetSimple set, int min, int max){
        System.out.print("{ ");
        for(int i = min; i < max; i += 2){
            if(set.contains(evenedNumberForMin(min, i)))
                System.out.print(i + " ");
        }
        System.out.print("}\n");
    }

    //Litery
    static int lettersRange = (int)'z' - (int)'a' + 1;

    static int letter(char l){
        return (int) l - 'a';
    }

    static void printLetterSet(SetSimple set){
        System.out.print("{ ");
        for(int i = (int)'a'; i < (int)'z'+1; i++){
            if(set.contains(letter((char) i)))
                System.out.print((char) i + " ");
        }
        System.out.print("}\n");
    }

    //Pary liter
    static int letterPairRange = (int) Math.pow(lettersRange, 2) + lettersRange;

    static int letterPair(char l1, char l2){
        return (int) (l1 - 'a')*lettersRange + l2-'a';
    }

    static void printLetterPairSet(SetSimple set){
        System.out.print("{ ");
        for(int i = (int)'a'; i < (int)'a'+letterPairRange; i++){
            if(set.contains(i - 'a'))
                printPair(i - 'a');
        }
        System.out.print("}\n");
    }

    static void printPair(int i){
        int j = 0;
        while(i - lettersRange >= 0){
            i -= lettersRange;
            j++;
        }
        System.out.print((char)(j+97));
        System.out.print((char)(i+97));
        System.out.print(" ");
    }

    static void testWhole(){
        SetSimple whole = new SetSimple(wholeNumberRange(10, 100));
        SetSimple whole2 = new SetSimple(wholeNumberRange(10, 100));
        whole.insert(wholeNumberForMin(10, 60));
        whole.insert(wholeNumberForMin(10, 80));
        whole.insert(wholeNumberForMin(10, 99));
        printWholeNumberSet(whole, 10, 100);
        whole.remove(wholeNumberForMin(10, 60));
        printWholeNumberSet(whole, 10, 100);
        System.out.println(whole.contains(wholeNumberForMin(10, 80)));
        System.out.println(whole.contains(wholeNumberForMin(10, 60)));
        whole.insert(wholeNumberForMin(10, 60));
        whole2.insert(wholeNumberForMin(10, 60));
        whole2.insert(wholeNumberForMin(10, 80));
        whole2.insert(wholeNumberForMin(10, 95));
        printWholeNumberSet(whole, 10, 100);
        printWholeNumberSet(whole2, 10, 100);
        printWholeNumberSet(whole.difference(whole2), 10, 100);
        printWholeNumberSet(whole.union(whole2), 10, 110);
        printWholeNumberSet(whole.intersection(whole2), 10, 100);
        System.out.println(whole.isIdenticalTo(whole2));
    }

    static void testEvened(){
        SetSimple evened = new SetSimple(evenedNumberRange(10, 100));
        SetSimple evened2 = new SetSimple(evenedNumberRange(10, 100));
        evened.insert(evenedNumberForMin(10, 60));
        evened.insert(evenedNumberForMin(10, 80));
        evened.insert(evenedNumberForMin(10, 98));
        printEvenedNumberSet(evened, 10, 100);
        evened.remove(evenedNumberForMin(10, 60));
        printEvenedNumberSet(evened, 10, 100);
        System.out.println(evened.contains(evenedNumberForMin(10, 80)));
        System.out.println(evened.contains(evenedNumberForMin(10, 60)));
        evened.insert(evenedNumberForMin(10, 60));
        evened2.insert(evenedNumberForMin(10, 60));
        evened2.insert(evenedNumberForMin(10, 80));
        evened2.insert(evenedNumberForMin(10, 94));
        printEvenedNumberSet(evened, 10, 100);
        printEvenedNumberSet(evened2, 10, 100);
        printEvenedNumberSet(evened.difference(evened2), 10, 100);
        printEvenedNumberSet(evened.union(evened2), 10, 110);
        printEvenedNumberSet(evened.intersection(evened2), 10, 100);
        System.out.println(evened.isIdenticalTo(evened2));
    }

    static void testLetters(){
        SetSimple letters = new SetSimple(lettersRange);
        SetSimple letters2 = new SetSimple(lettersRange);
        letters.insert(letter('a'));
        letters.insert(letter('b'));
        letters.insert(letter('c'));
        printLetterSet(letters);
        letters.remove(letter('c'));
        printLetterSet(letters);
        System.out.println(letters.contains(letter('a')));
        System.out.println(letters.contains(letter('c')));
        letters.insert(letter('c'));
        letters2.insert(letter('a'));
        letters2.insert(letter('b'));
        letters2.insert(letter('d'));
        printLetterSet(letters);
        printLetterSet(letters2);
        printLetterSet(letters.difference(letters2));
        printLetterSet(letters.union(letters2));
        printLetterSet(letters.intersection(letters2));
        System.out.println(letters.isIdenticalTo(letters2));
    }

    static void testLetterPairs(){
        SetSimple pair = new SetSimple(letterPairRange);
        SetSimple pair2 = new SetSimple(letterPairRange);
        pair.insert(letterPair('a', 'a'));
        pair.insert(letterPair('b', 'b'));
        pair.insert(letterPair('c', 'c'));
        printLetterPairSet(pair);
        pair.remove(letterPair('c', 'c'));
        printLetterPairSet(pair);
        System.out.println(pair.contains(letterPair('a', 'a')));
        System.out.println(pair.contains(letterPair('c', 'c')));
        pair.insert(letterPair('c', 'c'));
        pair2.insert(letterPair('a', 'a'));
        pair2.insert(letterPair('b', 'b'));
        pair2.insert(letterPair('c', 'a'));
        printLetterPairSet(pair);
        printLetterPairSet(pair2);
        printLetterPairSet(pair.difference(pair2));
        printLetterPairSet(pair.union(pair2));
        printLetterPairSet(pair.intersection(pair2));
        System.out.println(pair.isIdenticalTo(pair2));
    }

    public static void main(String[] args) {
       testWhole();
    //    testEvened();
    //    testLetters();
    //    testLetterPairs();
    }
}