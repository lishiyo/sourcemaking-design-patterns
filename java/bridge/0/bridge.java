class Node {
    public int  value;
    public Node prev, next;
    public Node( int i ) { value = i; }
}

class StackArray {
    private int[] items = new int[12];
    private int   total = -1;
    public void push( int i ) { if ( ! isFull()) items[++total] = i; }
    public boolean isEmpty()  { return total == -1; }
    public boolean isFull()   { return total == 11; }
    public int top() {
        if (isEmpty()) return -1;
        return items[total];
    }
    public int pop() {
        if (isEmpty()) return -1;
        return items[total--];
}   }

class StackList {
    private Node last;
    public void push( int i ) {
        if (last == null)
            last = new Node( i );
        else {
            last.next = new Node( i );
            last.next.prev = last;
            last = last.next;
    }   }
    public boolean isEmpty() { return last == null; }
    public boolean isFull()  { return false; }
    public int top() {
        if (isEmpty()) return -1;
        return last.value;
    }
    public int pop() {
        if (isEmpty()) return -1;
        int ret = last.value;
        last = last.prev;
        return ret;
}   }

class StackFIFO extends StackArray {
    private StackArray temp = new StackArray();
    public int pop() {
        while ( ! isEmpty())
            temp.push( super.pop() );
        int ret =  temp.pop();
        while ( ! temp.isEmpty())
            push( temp.pop() );
        return ret;
}  }

class StackHanoi extends StackArray {
    private int totalRejected = 0;
    public int reportRejected()   { return totalRejected; }
    public void push( int in ) {
        if ( ! isEmpty()  &&  in > top())
            totalRejected++;
        else super.push( in );
}   }

class BridgeDisc {
    public static void main( String[] args ) {
        StackArray[] stacks = { new StackArray(), new StackFIFO(), new StackHanoi() };
        StackList    stack2 = new StackList();
        for (int i=1, num; i < 15; i++) {
            stacks[0].push( i );
            stack2.push( i );
            stacks[1].push( i );
        }
        java.util.Random rn = new java.util.Random();
        for (int i=1, num; i < 15; i++)
            stacks[2].push( rn.nextInt(20) );
        while ( ! stacks[0].isEmpty())
            System.out.print( stacks[0].pop() + "  " );
        System.out.println();
        while ( ! stack2.isEmpty())
            System.out.print( stack2.pop() + "  " );
        System.out.println();
        while ( ! stacks[1].isEmpty())
            System.out.print( stacks[1].pop() + "  " );
        System.out.println();
        while ( ! stacks[2].isEmpty())
            System.out.print( stacks[2].pop() + "  " );
        System.out.println();
        System.out.println( "total rejected is "
            + ((StackHanoi)stacks[2]).reportRejected() );
}   }
