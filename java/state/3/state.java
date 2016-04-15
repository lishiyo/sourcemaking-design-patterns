import java.io.*;
                                             //  Event - push
// 1. The "wrapper" class                    //  State   ----
class Button {                              //   ON      OFF
   // 2. The "current" state object          //   OFF     ON
   private State current;

   public Button()                   { current = OFF.instance(); }
   public void setCurrent( State s ) { current = s; }

   // 3. The "wrapper" always delegates to the "wrappee"
   public void push()                { current.push( this ); }
}

// 4. The "wrappee" hierarchy
class State {
   // 5. Default behavior can go in the base class
   public void push( Button b )  {              
      b.setCurrent( OFF.instance() );
      System.out.println( "   turning OFF" );
   }
}

class ON extends State {
   private static ON inst = new ON();
   private ON() { }

   public static State instance() { return inst; }
}

class OFF extends State {
   private static OFF inst = new OFF();
   private OFF() { }

   public static State instance() { return inst; }
   // 6. Override only the necessary methods
   public void push( Button b ) {
      // 7. The "wrappee" may callback to the "wrapper"
      b.setCurrent( ON.instance() );
      System.out.println( "   turning ON" ); 
   }
}

public class StateToggle {
   public static void main( String[] args ) throws IOException {
      InputStreamReader is = new InputStreamReader( System.in );
      int ch;
      Button btn = new Button();
      while (true) {
         System.out.print( "Press 'Enter'" );
         ch = is.read();    ch = is.read();
         btn.push();
      }
   }
}
