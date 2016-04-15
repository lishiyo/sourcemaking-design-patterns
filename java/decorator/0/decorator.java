public class DecoratorBefore {

static class A { public void doIt() { System.out.print( 'A' ); } }

static class AwithX extends A {
   public  void doIt() { super.doIt();  doX(); }
   private void doX()  { System.out.print( 'X' ); }
}

static class AwithY extends A {
   public void doIt() { super.doIt();  doY(); }
   public void doY()  { System.out.print( 'Y' ); }
}

static class AwithZ extends A {
   public void doIt() { super.doIt();  doZ(); }
   public void doZ()  { System.out.print( 'Z' ); }
}

static class AwithXY extends AwithX {
   private AwithY obj = new AwithY();
   public void doIt() {
      super.doIt();
      obj.doY();
}  }

static class AwithXYZ extends AwithX {
   private AwithY obj1 = new AwithY();
   private AwithZ obj2 = new AwithZ();
   public void doIt() {
      super.doIt();
      obj1.doY();
      obj2.doZ();
}  }

public static void main( String[] args ) {
   A[] array = { new AwithX(), new AwithXY(), new AwithXYZ() };
   for (int i=0; i < array.length; i++) {
      array[i].doIt();
      System.out.print( "  " );
}  }  }
