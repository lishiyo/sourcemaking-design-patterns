/* The OLD */
class SquarePeg {
   private double width;
   public SquarePeg( double w )       { width = w; }
   public double getWidth()           { return width; }
   public void   setWidth( double w ) { width = w; }
}

/* The NEW */
class RoundHole {
   private int radius;
   public RoundHole( int r ) {
      radius = r;
      System.out.println( "RoundHole: max SquarePeg is " + r * Math.sqrt(2) );
   }
   public int getRadius() { return radius; }
}

// Design a "wrapper" class that can "impedance match" the old to the new
class SquarePegAdapter {

   // The adapter/wrapper class "has a" instance of the legacy class
   private SquarePeg sp;

   public SquarePegAdapter( double w ) { sp = new SquarePeg( w ); }

   // Identify the desired interface
   public void makeFit( RoundHole rh ) {
      // The adapter/wrapper class delegates to the legacy object
      double amount = sp.getWidth() - rh.getRadius() * Math.sqrt(2);
      System.out.println( "reducing SquarePeg " + sp.getWidth() + " by " + ((amount < 0) ? 0 : amount) + " amount" );
      if (amount > 0) {
         sp.setWidth( sp.getWidth() - amount );
         System.out.println( "   width is now " + sp.getWidth() );
      }
   }
}

class AdapterDemoSquarePeg {
   public static void main( String[] args ) {
      RoundHole        rh = new RoundHole( 5 );
      SquarePegAdapter spa;

      for (int i=6; i < 10; i++) {
         spa = new SquarePegAdapter( (double) i );
         // The client uses (is coupled to) the new interface
         spa.makeFit( rh );
      }
   }
}
