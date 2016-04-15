interface AlarmListener { public void alarm(); }

class SensorSystem {
   private java.util.Vector listeners = new java.util.Vector();

   public void register( AlarmListener al ) { listeners.addElement( al ); }
   public void soundTheAlarm() {
      for (java.util.Enumeration e=listeners.elements(); e.hasMoreElements(); )
         ((AlarmListener)e.nextElement()).alarm();
}  }

class Lighting implements AlarmListener {
   public void alarm() { System.out.println( "lights up" ); }
}

class Gates implements AlarmListener {
   public void alarm() { System.out.println( "gates close" ); }
}

class CheckList {
   public void byTheNumbers() {  // Template Method design pattern
      localize();
      isolate();
      identify(); }
   protected void localize() {
      System.out.println( "   establish a perimeter" ); }
   protected void isolate()  {
      System.out.println( "   isolate the grid" ); }
   protected void identify() {
      System.out.println( "   identify the source" ); }
}
                   // class inheri.  // type inheritance
class Surveillance extends CheckList implements AlarmListener {
   public void alarm() {
      System.out.println( "Surveillance - by the numbers:" );
      byTheNumbers(); }
   protected void isolate() { System.out.println( "   train the cameras" ); }
}

public class ClassVersusInterface {
   public static void main( String[] args ) {
      SensorSystem ss = new SensorSystem();
      ss.register( new Gates()        );
      ss.register( new Lighting()     );
      ss.register( new Surveillance() );
      ss.soundTheAlarm();
}  }
