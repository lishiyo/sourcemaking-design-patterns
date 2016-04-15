public class VisitorSingle {

interface Base {
   void process1( Base secondObject );
   void process2(  A   firstObject  );
   void process2(  B   firstObject  );
   void process2(  C   firstObject  );
}

static class A implements Base {
   public void process1( Base second ) { second.process2( this ); }
   public void process2( A first ) {
      System.out.println( "first is A, second is A" ); }
   public void process2( B first ) {
      System.out.println( "first is B, second is A" ); }
   public void process2( C first ) {
      System.out.println( "first is C, second is A" ); }
}

static class B implements Base {
   public void process1( Base second ) { second.process2( this ); }
   public void process2( A first ) {
      System.out.println( "first is A, second is B" ); }
   public void process2( B first ) {
      System.out.println( "first is B, second is B" ); }
   public void process2( C first ) {
      System.out.println( "first is C, second is B" ); }
}

static class C implements Base {
   public void process1( Base second ) { second.process2( this ); }
   public void process2( A first ) {
      System.out.println( "first is A, second is C" ); }
   public void process2( B first ) {
      System.out.println( "first is B, second is C" ); }
   public void process2( C first ) {
      System.out.println( "first is C, second is C" ); }
}

public static void main( String[] args ) {
   Base array[] = { new A(), new B(), new C() };
   for (int i=0; i < array.length; i++)
      for (int j=0; j < 3; j++)
         array[i].process1( array[j] );
}}
