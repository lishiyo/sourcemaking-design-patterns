// 1. "lowest common denominator"
interface Widget {
  void draw();
}

// 3. "Core" class with "is a" relationship
class TextField implements Widget {
  private int width, height;
  public TextField( int w, int h ) {
    width  = w;
    height = h;
  }
  public void draw() {
    System.out.println( "TextField: " + width + ", " + height );
  }
}

// 2. Second level base class with "isa" relationship
abstract class Decorator implements Widget {
  private Widget wid; // 4. "has a" relationship

  public Decorator( Widget w ) {
    wid = w;
  }

  // 5. Delegation
  public void draw() {
    wid.draw();
  }
}

// 6. Optional embellishment
class BorderDecorator extends Decorator {
  public BorderDecorator( Widget w ) {
    super( w );
  }
  public void draw() {
    super.draw(); // 7. Delegate to base class and add extra stuff
    System.out.println("  BorderDecorator");
  }
}

// 6. Optional embellishment
class ScrollDecorator extends Decorator {
  public ScrollDecorator( Widget w ) {
    super( w );
  }
  public void draw() {
    super.draw(); // 7. Delegate to base class and add extra stuff
    System.out.println( "  ScrollDecorator" );
  }
}

public class DecoratorDemo {
  public static void main( String[] args ) {
    // 8. Client has the responsibility to compose desired configurations
    Widget aWidget = new BorderDecorator(
                       new BorderDecorator(
                         new ScrollDecorator(
                           new TextField( 80, 24 ))));
    aWidget.draw();
  }
}
