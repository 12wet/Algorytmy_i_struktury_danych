public class Node{
    private int value;
    private Node next;

    public Node(int value, Node next){
        this.value = value;
        this.next = next;
    }

    public Node(int value){
        this(value, null);
    }

    public int getValue(){
        return value;
    }

    public void setValue(int val){
        value = val;
    }
    
    public Node getNext(){
        return next;
    }
    
    public void setNext(Node nextNode){
        next = nextNode;
    }
}