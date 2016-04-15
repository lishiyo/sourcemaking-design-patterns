import java.awt.*;
import java.awt.event.*;

class FlyweightFactory {
   private static java.util.Map  ht = new java.util.TreeMap();
   private static int            sharedButtons = 0;
   private static ButtonListener bl = new ButtonListener();
   public static Button makeButton( String num ) {
      Button btn;
      if (ht.containsKey( num )) {
         // 1. Identify intrinsic state (Button label)
         // 2. Return an existing object   [The same Button cannot be added
         //    multiple times to a container, and, Buttons cannot be cloned.
         //    So - this is only simulating the sharing that the Flyweight
         //    pattern provides.]
         btn = new Button( ((Button)ht.get(num)).getLabel() );
         sharedButtons++;
      } else {
         // 2. Return a new object
         btn = new Button( num );
         ht.put( num, btn );
      }
      btn.addActionListener( bl );
      return btn;
   }
   public static void report() {
      System.out.print( "new Buttons - " + ht.size()
         + ", \"shared\" Buttons - " + sharedButtons + ", " );
      for (java.util.Iterator it = ht.keySet().iterator(); it.hasNext(); )
         System.out.print( it.next() + " " );
      System.out.println();
}  }

class ButtonListener implements ActionListener {
   public void actionPerformed( ActionEvent e) {
      Button      btn  = (Button) e.getSource();
      java.awt.Component[] btns = btn.getParent().getComponents();
      int i = 0;
      for ( ; i < btns.length; i++)
         if (btn == btns[i]) break;
      // 4. A third party must compute the extrinsic state (x and y)
      //    (the Button label is intrinsic state)
      System.out.println( "label-" + e.getActionCommand()
         + "  x-" + i/10   + "  y-" + i%10 );  // 1. Identify extrinsic state
}  }                                           //    (Button location)

public class FlyweightDemo {
   public static void main( String[] args ) {
      java.util.Random rn = new java.util.Random();
      Frame frame = new Frame( "Flyweight Demo" );
      frame.addWindowListener( new WindowAdapter() {
        public void windowClosing( WindowEvent e ) {
          System.exit( 0 );
      } } );
      frame.setLayout( new GridLayout( 10, 10 ) );
      // 1. Identify shareable and non-shareable state
      //    shareable - Button label, non-shareable - Button location
      for (int i=0; i < 10; i++)
         for (int j=0; j < 10; j++)
            // 3. The client must use the Factory to request objects
            frame.add( FlyweightFactory.makeButton( 
               Integer.toString( rn.nextInt(15) ) ) );
      frame.pack();
      frame.setVisible( true );
      FlyweightFactory.report();
}  }
