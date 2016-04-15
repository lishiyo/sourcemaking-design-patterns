interface Component { void traverse(); }      // 1. "lowest common denominator" 

class Primitive implements Component {        // 2. "Isa" relationship
   private int value;
   public Primitive( int val ) { value = val; }
   public void traverse()      { System.out.print( value + "  " ); }
}

abstract class Composite implements Component {      // 2. "Isa" relationship
   private Component[] children = new Component[9];  // 3. Couple to interface
   private int         total    = 0;
   private int         value;
   public Composite( int val )    { value = val; }
   public void add( Component c ) { children[total++] = c; } // 3. Couple to
   public void traverse() {                                  //    interface
      System.out.print( value + "  " );
      for (int i=0; i < total; i++)
          children[i].traverse();            // 4. Delegation and polymorphism 
}  }

class Row extends Composite {                // Two different kinds of "con-
   public Row( int val ) { super( val ); }   // tainer" classes.  Most of the
   public void traverse() {                  // "meat" is in the Composite
      System.out.print( "Row" );             // base class.
      super.traverse();
}  }

class Column extends Composite {
   public Column( int val ) { super( val ); }
   public void traverse() {
      System.out.print( "Col" );
      super.traverse();
}  }

public class CompositeDemo {
   public static void main( String[] args ) {
      Composite first  = new Row( 1 );          // Row1
      Composite second = new Column( 2 );       //   |
      Composite third  = new Column( 3 );       //   +-- Col2
      Composite fourth = new Row( 4 );          //   |     |
      Composite fifth  = new Row( 5 );          //   |     +-- 7
      first.add( second );                      //   +-- Col3
      first.add( third  );                      //   |     |
      third.add( fourth );                      //   |     +-- Row4
      third.add( fifth  );                      //   |     |     |
      first.add(  new Primitive( 6 ) );         //   |     |     +-- 9
      second.add( new Primitive( 7 ) );         //   |     +-- Row5
      third.add(  new Primitive( 8 ) );         //   |     |     |
      fourth.add( new Primitive( 9 ) );         //   |     |     +-- 10
      fifth.add(  new Primitive(10 ) );         //   |     +-- 8
      first.traverse();                         //   +-- 6
}  }
