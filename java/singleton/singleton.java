public class Singleton {
    private static Singleton instance;
 
   // Private constructor suppresses generation of a (public) default constructor
   private Singleton() {
   }
 
   public static Singleton getInstance() {
     if(instance == null) {
        synchronized (Singleton.class) {
           if(instance == null) {
              instance = new Singleton();
           }
        }
     }
     return instance;
   }
 }
