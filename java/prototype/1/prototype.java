interface Prototype {
  Object clone();
  String getName();
}
// 1. The clone() contract
interface Command {
  void execute();
}

class PrototypesModule {
  // 2. "registry" of prototypical objs
  private static Prototype[] prototypes = new Prototype[9];
  private static int total = 0;

  // Adds a feature to the Prototype attribute of the PrototypesModule class
  // obj  The feature to be added to the Prototype attribute
  public static void addPrototype( Prototype obj ) {
    prototypes[total++] = obj;
  }

  public static Object findAndClone( String name ) {
    // 4. The "virtual ctor"
    for ( int i = 0; i < total; i++ ) {
      if ( prototypes[i].getName().equals( name ) ) {
        return prototypes[i].clone();
      }
    }
    System.out.println( name + " not found" );
    return null;
  }
}

// 5. Sign-up for the clone() contract.
// Each class calls "new" on itself FOR the client.
class This implements Prototype, Command {
  public Object clone() {
    return new This();
  }
  public String getName() {
    return "This";
  }
  public void execute() {
    System.out.println( "This: execute" );
  }
}

class That implements Prototype, Command {
  public Object clone() {
    return new That();
  }
  public String getName() {
    return "That";
  }
  public void execute() {
    System.out.println( "That: execute" );
  }
}

class TheOther implements Prototype, Command {
  public Object clone() {
    return new TheOther();
  }
  public String getName() {
    return "TheOther";
  }
  public void execute() {
    System.out.println( "TheOther: execute" );
  }
}


public class PrototypeDemo {
  // 3. Populate the "registry"
  public static void initializePrototypes() {
    PrototypesModule.addPrototype( new This() );
    PrototypesModule.addPrototype( new That() );
    PrototypesModule.addPrototype( new TheOther() );
  }
  public static void main( String[] args ) {
    initializePrototypes();
    Object[] objects = new Object[9];
    int total = 0;

    // 6. Client does not use "new"
    for (int i=0; i < args.length; i++) {
      objects[total] = PrototypesModule.findAndClone( args[i] );
      if (objects[total] != null) total++;
    }
    for (int i=0; i < total; i++) {
      ((Command)objects[i]).execute();
    }
  }
}