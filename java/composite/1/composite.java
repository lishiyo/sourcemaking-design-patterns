import java.util.List;
import java.util.ArrayList;

abstract class Entity {
    protected static StringBuffer indent = new StringBuffer();
    protected static int level = 1;
    public abstract void traverse( int[] levels );
    protected boolean printThisLevel( int[] levels ) {
        for (int i=0; i < levels.length; i++)
            if (level == levels[i])
                return true;
        return false;
}   }

class Product extends Entity {
    private int value;
    public Product( int val ) { value = val; }
    public void traverse( int[] levels ) {
        if (printThisLevel( levels ))
            System.out.println( indent.toString() + value );
}   }

class Box extends Entity {
    private List children = new ArrayList();
    private int  value;
    public Box( int val )       { value = val; }
    public void add( Entity c ) { children.add( c ); }
    public void traverse( int[] levels ) {
        if (printThisLevel( levels )) {
            System.out.println( indent.toString() + value );
            indent.append( "   " );
        }
        level++;
        for (int i=0; i < children.size(); i++)
            ((Entity)children.get(i)).traverse( levels );
        level--;
        if (printThisLevel( levels ))
            indent.setLength( indent.length() - 3 );
}   }

public class CompositeLevelsAns {
    public static void main( String[] args ) {
        Box root = initialize();
        int[] levels = new int[args.length];
        for (int i=0; i < args.length; i++)
            levels[i] = Integer.parseInt( args[i] );
        root.traverse( levels );
    }

    private static Box initialize() {
        Box[] nodes = new Box[7];
        nodes[1] = new Box( 1 );
        int[] s = { 1, 4, 7 };
        for (int i=0; i < 3; i++) {
            nodes[2] = new Box( 21+i );
            nodes[1].add( nodes[2] );
            int lev = 3;
            for (int j=0; j < 4; j++) {
                nodes[lev-1].add( new Product( lev*10 + s[i] ) );
                nodes[lev] = new Box( lev*10 + s[i]+1 );
                nodes[lev-1].add( nodes[lev] );
                nodes[lev-1].add( new Product( lev*10 + s[i]+2 ) );
                lev++;
        }   }
        return nodes[1];
}   }
