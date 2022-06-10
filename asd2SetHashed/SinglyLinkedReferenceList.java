import java.util.Arrays;

public class SinglyLinkedReferenceList{
    public Node head = new Node(0);
    private Node last = head;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void sizer(){
        System.out.println("size is: " + size);
    }

    public String toString(){
        Node tempNode = head;
        String toPrint = "{";
        int i = 0;
        while(tempNode.getNext() != null){
            if(i == size - 1){
                tempNode = tempNode.getNext();
                toPrint += String.valueOf(tempNode.getValue());
            }
            else{
                tempNode = tempNode.getNext();
                toPrint += String.valueOf(tempNode.getValue()) + ", ";
            }
            i++;
        }
        toPrint += "}";
        return toPrint;
    }

    public void add(int e){
        Node newNode;
        last.setNext(newNode = new Node(e));
        last = newNode;
        size++;
    }

    public void remove(int e){
        Node contains = contains(e);
        if(contains == null) {
            throw new NullPointerException("The list doesn't contain integer: " + e);
        }

        Node tempNode = contains.getNext();
        contains.setNext(contains.getNext().getNext());
        tempNode.setNext(null);
        if(contains.getNext()==null){
            last = contains;
        }
        size--;
    }

    public Node contains(int e){
        if(size == 0) return null;

        Node toFind = head;
        for(int i = 0; i < size; i++){
            if(toFind.getNext().getValue() == e) return toFind;
            toFind = toFind.getNext();   
        }

        return null;
    }

    public void newSort(){
        if(size == 0) return;

        int[] toBeSorted = new int[size];
        Node tempNode = head;

        for(int i = 0; i < size; i++){
            tempNode = tempNode.getNext();
            toBeSorted[i] = tempNode.getValue();
        }

        Arrays.sort(toBeSorted);

        tempNode = head;
        for(int i = 0; i < size; i++){
            tempNode = tempNode.getNext();
            tempNode.setValue(toBeSorted[i]);
        }
        last = tempNode;
    }
}