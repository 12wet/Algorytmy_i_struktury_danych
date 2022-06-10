public class SetSimple{
    final int OMEGA_SIZE;
    final int[] omega;

    public SetSimple(final int OMEGA_SIZE){
        this.OMEGA_SIZE = OMEGA_SIZE;
        omega = new int[OMEGA_SIZE];
    }

    public String toString(){
        String toReturn = "{ ";
        for(int i = 0; i < OMEGA_SIZE; i++)
            if(omega[i] == 1) toReturn += i + " ";
        
        return toReturn + "}";
    }

    public int getSize(){
        return OMEGA_SIZE;
    }

    public void insert(int e){
        omega[e] = 1;
    }

    public void remove(int e){
        omega[e] = 0;
    }

    public boolean contains(int e){
        if(e >= this.getSize()) return false;
        if(omega[e] == 0) return false;
        return true;
    }

    public SetSimple union(SetSimple bSet){
        int newSize = Math.max(this.getSize(), bSet.getSize());
        SetSimple cSet = new SetSimple(newSize);
        for(int i = 0; i < newSize; i++){
            if(this.contains(i) || bSet.contains(i))
                cSet.insert(i);
        }

        return cSet;
    }

    public SetSimple intersection(SetSimple bSet){
        int newSize = Math.min(this.getSize(), bSet.getSize());
        SetSimple cSet = new SetSimple(newSize);
        for(int i = 0; i < newSize; i++){
            if(this.contains(i) && bSet.contains(i))
                cSet.insert(i);
        }

        return cSet;
    }

    public SetSimple difference(SetSimple bSet){
        int newSize = this.getSize();
        SetSimple cSet = new SetSimple(newSize);
        for(int i = 0; i < newSize; i++){
            if(this.contains(i) && !bSet.contains(i))
                cSet.insert(i);
        }

        return cSet;
    }

    public boolean isIdenticalTo(SetSimple bSet){
        int newSize = Math.max(this.getSize(), bSet.getSize());
        for(int i = 0; i < newSize; i++){
            if(this.contains(i) != bSet.contains(i))
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SetSimple a = new SetSimple(1000);
        SetSimple b = new SetSimple(1000);

        a.insert(1);
        a.insert(7);
        a.insert(888);
        a.insert(0);
        a.insert(78);

        b.insert(87);
        b.insert(3);
        b.insert(0);
        b.insert(888);
        b.insert(13);

        System.out.println(a +"\n"+ b);
        System.out.println(a.union(b));
        System.out.println(a.intersection(b));
        System.out.println(a.difference(b));
        System.out.println(a.isIdenticalTo(b));
    }
}