public class SetHashed {
    private final int SIZE;
    private final SinglyLinkedReferenceList[] set;
    private int eNum;
    
    public SetHashed(final int SIZE){
        this.SIZE = SIZE;
        set = new SinglyLinkedReferenceList[SIZE];
        eNum = 0;
    }

    public String toString(){
        String toReturn = "{ ";
        for(SinglyLinkedReferenceList bucket : set){
            if(bucket != null) 
                toReturn += bucket + " ";
        }
        return toReturn + "}";
    }

    int getCurrentSize(){
        return eNum;
    }

    int getSize(){
        return SIZE;
    }

    SinglyLinkedReferenceList[] getSet(){
        return set;
    }

    int hash(int e){
        int hashCode = e % SIZE;
        hashCode = hashCode < 0 ? hashCode * -1 : hashCode;
        return hashCode;
    }

    public void insert(int e){
        int hashCode = hash(e);
        if(set[hashCode] == null)
            set[hashCode] = new SinglyLinkedReferenceList();
        if(set[hashCode].contains(e) == null){
            set[hashCode].add(e);
            eNum++;
        }   
    }

    public boolean remove(int e){
        int hashCode = hash(e);
        try {
            set[hashCode].remove(e);
            if(set[hashCode].getSize() == 0)
                set[hashCode] = null;
            eNum--;
            return true;
        } catch (NullPointerException ex) { return false; }
    }

    public boolean contains(int e){
        int hashCode = hash(e);
        if(set[hashCode] != null)
            return set[hashCode].contains(e) == null ? false : true;
        return false;
    }

    public SetHashed union(SetHashed bSet){
        int cSize = Math.max(this.getSize(), bSet.getSize());
        SetHashed cSet = new SetHashed(cSize);
        for(SinglyLinkedReferenceList bucket : this.set){
            if(bucket != null){
                Node current = bucket.head;
                while(current.getNext() != null){
                    current = current.getNext();
                    cSet.insert(current.getValue());
                }
            }
        }
        for(SinglyLinkedReferenceList bucket : bSet.set){
            if(bucket != null){
                Node current = bucket.head;
                while(current.getNext() != null){
                    current = current.getNext();
                    cSet.insert(current.getValue());
                }
            }
        }
        return cSet;
    }

    public SetHashed intersection(SetHashed bSet){
        int cSize = Math.min(this.getSize(), bSet.getSize());
        SetHashed cSet = new SetHashed(cSize);
        for(SinglyLinkedReferenceList bucket : this.set){
            if(bucket != null){
                Node current = bucket.head;
                while(current.getNext() != null){
                    current = current.getNext();
                    if(bSet.contains(current.getValue()))
                        cSet.insert(current.getValue());
                }
            }
        }

        return cSet;
    }

    public SetHashed difference(SetHashed bSet){
        int cSize = Math.max(this.getSize(), bSet.getSize());
        SetHashed cSet = new SetHashed(cSize);
        for(SinglyLinkedReferenceList bucket : this.set){
            if(bucket != null){
                Node current = bucket.head;
                while(current.getNext() != null){
                    current = current.getNext();
                    if(!bSet.contains(current.getValue()))
                        cSet.insert(current.getValue());
                }
            }
        }

        return cSet;
    }

    public boolean isIdenticalTo(SetHashed bSet){
        if(this.eNum != bSet.eNum) return false;
        for(SinglyLinkedReferenceList bucket : this.set){
            if(bucket != null){
                Node current = bucket.head;
                while(current.getNext() != null){
                    current = current.getNext();
                    if(!bSet.contains(current.getValue()))
                        return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SetHashed s = new SetHashed(100);
        s.insert(10);
        s.insert(60);
        s.insert(160);
        s.insert(19);
        System.out.println(s);
        s.remove(10);
        s.remove(60);
        s.remove(160);
        s.remove(19);
        System.out.println(s);
        s.insert(160);
        s.insert(19);
        System.out.println(s);
        System.out.println(s.contains(10));
        System.out.println(s.contains(160));
        SetHashed s2 = new SetHashed(100);
        s.insert(3);
        s.insert(119);
        s2.insert(10);
        s2.insert(60);
        s2.insert(160);
        s2.insert(19);
        System.out.println(s2);
        System.out.println(s);
        System.out.println(s2.union(s));
        System.out.println(s2.intersection(s));
        System.out.println(s2.difference(s));
        System.out.println(s2.isIdenticalTo(s));
    }
}
