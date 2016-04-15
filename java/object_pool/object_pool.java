// ObjectPool Class

public abstract class ObjectPool<T> {
  private long expirationTime;

  private Hashtable<T, Long> locked, unlocked;

  public ObjectPool() {
    expirationTime = 30000; // 30 seconds
    locked = new Hashtable<T, Long>();
    unlocked = new Hashtable<T, Long>();
  }

  protected abstract T create();

  public abstract boolean validate(T o);

  public abstract void expire(T o);

  public synchronized T checkOut() {
    long now = System.currentTimeMillis();
    T t;
    if (unlocked.size() > 0) {
      Enumeration<T> e = unlocked.keys();
      while (e.hasMoreElements()) {
        t = e.nextElement();
        if ((now - unlocked.get(t)) > expirationTime) {
          // object has expired
          unlocked.remove(t);
          expire(t);
          t = null;
        } else {
          if (validate(t)) {
            unlocked.remove(t);
            locked.put(t, now);
            return (t);
          } else {
            // object failed validation
            unlocked.remove(t);
            expire(t);
            t = null;
          }
        }
      }
    }
    // no objects available, create a new one
    t = create();
    locked.put(t, now);
    return (t);
  }

  public synchronized void checkIn(T t) {
    locked.remove(t);
    unlocked.put(t, System.currentTimeMillis());
  }
}

//The three remaining methods are abstract 
//and therefore must be implemented by the subclass

public class JDBCConnectionPool extends ObjectPool<Connection> {

  private String dsn, usr, pwd;

  public JDBCConnectionPool(String driver, String dsn, String usr, String pwd) {
    super();
    try {
      Class.forName(driver).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.dsn = dsn;
    this.usr = usr;
    this.pwd = pwd;
  }

  @Override
  protected Connection create() {
    try {
      return (DriverManager.getConnection(dsn, usr, pwd));
    } catch (SQLException e) {
      e.printStackTrace();
      return (null);
    }
  }

  @Override
  public void expire(Connection o) {
    try {
      ((Connection) o).close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean validate(Connection o) {
    try {
      return (!((Connection) o).isClosed());
    } catch (SQLException e) {
      e.printStackTrace();
      return (false);
    }
  }
}


// JDBCConnectionPool will allow the application to borrow and return database connections:
/*

public class Main {
  public static void main(String args[]) {
    // Do something...
    ...

    // Create the ConnectionPool:
    JDBCConnectionPool pool = new JDBCConnectionPool(
      "org.hsqldb.jdbcDriver", "jdbc:hsqldb://localhost/mydb",
      "sa", "secret");

    // Get a connection:
    Connection con = pool.checkOut();

    // Use the connection
    ...

    // Return the connection:
    pool.checkIn(con);
 
  }


*/