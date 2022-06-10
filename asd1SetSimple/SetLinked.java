public class SetLinked{
    Node head;
    int size;

    public SetLinked(){
        head = null;
        size = 0;
    }

    private class Node{
        Node next;
        int val;

        public Node(int val, Node next){
            this.next = next;
            this.val = val;
        }

        public Node(int val){
            this(val, null);
        }

        public void setNext(Node next){
            this.next = next;
        }

        public Node getNext(){
            return next;
        }

        public int getVal(){
            return val;
        }
    }

    public String toString(){
        String toReturn = "{ ";
        Node current = head;
        while(current != null){
            toReturn += current.getVal() + " ";
            current = current.getNext();
        }
        return toReturn + "}";
    }
    
    public void insert(int e){
        Node newNode = new Node(e);
        Node current = head;
        Node previous = null;
        while(current != null){
            previous = current;
            current = current.getNext();
        }

        if(previous == null){
            head = newNode;
        }
        else
            previous.setNext(newNode);
        newNode.setNext(current);
        size++;
    }

    public void remove(int e){
        if(!contains(e)) 
            return;

        Node current = head;
        Node previous = null;
        while(current != null && e != current.getVal()){
            previous = current;
            current = current.getNext();
        }

        if(previous == null){
            head = head.getNext();
            current.setNext(null);
        }
        else{
            previous.setNext(current.getNext());
            current.setNext(null);
        }
        size--;
    }

    public boolean contains(int e){
        Node current = head;
        while(current != null){
            if(current.getVal() == e)
                return true;
            current = current.getNext();
        }
        return false;
    }

    public SetLinked union(SetLinked bSet){

        SetLinked cSet = new SetLinked();
        Node aCurrent = head;

        if(aCurrent != null){
            cSet.head = new Node(aCurrent.getVal());
            Node cCurrent = cSet.head;
            aCurrent = aCurrent.getNext();

            while(aCurrent != null){
                Node previous = cCurrent;
                cCurrent = new Node(aCurrent.getVal());
                previous.setNext(cCurrent);
                aCurrent = aCurrent.getNext();
            }
        } 
        cSet.size = size;

        Node bCurrent = bSet.head;
        while(bCurrent != null){
            int e = bCurrent.getVal();
            if(!cSet.contains(e)){
                cSet.insert(e);
            }
            bCurrent = bCurrent.getNext();
        }

        return cSet;
    }

    public SetLinked intersection(SetLinked bSet){
        
        SetLinked cSet = new SetLinked();
        Node aCurrent = head;
        Node cCurrent = null;
        cSet.size = size;

        if(aCurrent != null){
            while(cSet.head == null){
                if(bSet.contains(aCurrent.getVal())){
                    cSet.head = new Node(aCurrent.getVal());
                    cCurrent = cSet.head;
                }
                else{
                    cSet.size--;
                }
                
                aCurrent = aCurrent.getNext();
            }

            while(aCurrent != null){
                Node previous = null;
                if(cCurrent != null)
                    previous = cCurrent;

                if(bSet.contains(aCurrent.getVal())){
                    cCurrent = new Node(aCurrent.getVal());
                    previous.setNext(cCurrent);
                }
                aCurrent = aCurrent.getNext();
            }
        } 

        return cSet;
    }

    public SetLinked difference(SetLinked bSet){

        SetLinked cSet = new SetLinked();
        Node aCurrent = head;
        Node cCurrent = null;

        while(cSet.head == null && aCurrent != null){
            if(!bSet.contains(aCurrent.getVal())){
                cSet.head = new Node(aCurrent.getVal());
                cCurrent = cSet.head;
                cSet.size++;
            }
            
             aCurrent = aCurrent.getNext();
        }

        while(aCurrent != null){
            Node previous = null;
            if(cCurrent != null)
                previous = cCurrent;

            if(!bSet.contains(aCurrent.getVal())){
                cCurrent = new Node(aCurrent.getVal());
                previous.setNext(cCurrent);
                cSet.size++;
            }
            aCurrent = aCurrent.getNext();
        }
        

        return cSet;
    }

    public boolean isIdenticalTo(SetLinked bSet){
        if(size != bSet.size) return false;

        Node current = head;
        while(current != null){
           if(!bSet.contains(current.getVal()))
                return false;
            current = current.getNext();
        }
        return true;  
    }

    public static void main(String[] args) {
        SetLinked a = new SetLinked();
        SetLinked b = new SetLinked();

        a.insert(1);
        a.insert(7);
        a.insert(888);
        a.insert(0);
        a.insert(78);

        b.insert(1);
        b.insert(7);
        b.insert(888);
        b.insert(0);

        System.out.println(a +"\n"+ b);
        System.out.println(a.union(b));
        System.out.println(a.intersection(b));
        System.out.println(a.difference(b));
        System.out.println(b.difference(a));
        System.out.println(b.isIdenticalTo(a));
        System.out.println(a.union(b).isIdenticalTo(a.intersection(b)));
    }
}