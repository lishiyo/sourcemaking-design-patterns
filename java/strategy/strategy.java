// 1. Define the interface of the algorithm
interface Strategy { public void solve(); }          

// 2. Bury implementation
abstract class TemplateMethod1 implements Strategy { // 3. Template Method 
   public void solve() {
      start();
      while (nextTry() && ! isSolution())
         ;
      stop();
   }
   protected abstract void    start();
   protected abstract boolean nextTry();
   protected abstract boolean isSolution();
   protected abstract void    stop();
}

class Impl1 extends TemplateMethod1 {
   private int state = 1;
   protected void start() {
     System.out.print( "start  " );
   }
   protected void stop() {
     System.out.println( "stop" );
   }
   protected boolean nextTry() {
      System.out.print( "nextTry-" + state++ + "  " );
      return true;
   }
   protected boolean isSolution() {
      System.out.print( "isSolution-" + (state == 3) + "  " );
      return (state == 3);
   }
}

// 2. Bury implementation
abstract class TemplateMethod2 implements Strategy { // 3. Template Method
   public void solve() {                             
      while (true) {
         preProcess();
         if (search()) break;
         postProcess();
      }
   }
   protected abstract void preProcess();
   protected abstract boolean search();
   protected abstract void postProcess();
}

class Impl2 extends TemplateMethod2 {
   private int state = 1;
   protected void    preProcess()  { System.out.print( "preProcess  " ); }
   protected void    postProcess() { System.out.print( "postProcess  " ); }
   protected boolean search() {
      System.out.print( "search-" + state++ + "  " );
      return state == 3 ? true : false;
   }
}

// 4. Clients couple strictly to the interface
public class StrategyDemo {
   public static void clientCode( Strategy strat ) {
     strat.solve();
   }
   public static void main( String[] args ) {
      Strategy[] algorithms = { new Impl1(), new Impl2() };
      for (int i=0; i < algorithms.length; i++) {
         clientCode( algorithms[i] );
      }
   }
}
