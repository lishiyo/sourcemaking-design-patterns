interface Element {
   // 1. accept(Visitor) interface
   public void accept( Visitor v ); // first dispatch
}

class This implements Element {
   // 1. accept(Visitor) implementation
   public void   accept( Visitor v ) {
     v.visit( this );
   } 
   public String thiss() {
     return "This";
   }
}

class That implements Element {
   public void   accept( Visitor v ) {
     v.visit( this );
   }
   public String that() {
     return "That";
   }
}

class TheOther implements Element {
   public void   accept( Visitor v ) {
     v.visit( this );
   }
   public String theOther() {
     return "TheOther"; 
   }
}

// 2. Create a "visitor" base class with a visit() method for every "element" type
interface Visitor {
   public void visit( This e ); // second dispatch
   public void visit( That e );
   public void visit( TheOther e );
}

// 3. Create a "visitor" derived class for each "operation" to perform on "elements"
class UpVisitor implements Visitor {                   
   public void visit( This e ) {
      System.out.println( "do Up on " + e.thiss() );
   }
   public void visit( That e ) {
      System.out.println( "do Up on " + e.that() );
   }
   public void visit( TheOther e ) {
      System.out.println( "do Up on " + e.theOther() );
   }
}

class DownVisitor implements Visitor {
   public void visit( This e ) {
      System.out.println( "do Down on " + e.thiss() );
   }
   public void visit( That e ) {
      System.out.println( "do Down on " + e.that() );
   }
   public void visit( TheOther e ) {
      System.out.println( "do Down on " + e.theOther() );
   }
}

class VisitorDemo {
   public static Element[] list = { new This(), new That(), new TheOther() };

   // 4. Client creates "visitor" objects and passes each to accept() calls
   public static void main( String[] args ) {
      UpVisitor    up   = new UpVisitor();
      DownVisitor  down = new DownVisitor();
      for (int i=0; i < list.length; i++) {
         list[i].accept( up );
      }
      for (int i=0; i < list.length; i++) {
         list[i].accept( down );
      }
   }
}
