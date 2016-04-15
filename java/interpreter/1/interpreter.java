public class InterpreterDemo {
   public static boolean precedence( char a, char b ) {
      String high = "*/", low = "+-";
      if (a == '(') return false;     // if (a == '(' && b == ')') return false;
      if (a == ')'  &&  b == '(') { System.out.println( ")-(" ); return false; }
      if (b == '(') return false;
      if (b == ')') return true;
      if (high.indexOf( a ) > -1  &&  low.indexOf( b )  > -1) return true;
      if (high.indexOf( a ) > -1  &&  high.indexOf( b ) > -1) return true;
      if (low.indexOf( a ) > -1   &&  low.indexOf( b )  > -1) return true;
      return false;
   }
   public static String convertToPostfix( String in ) {
      StkChar      opstk  = new StkChar();
      StringBuffer out    = new StringBuffer();
      String       opers  = "+-*/()";
      char         topsym = '+';
      boolean      empty;

      for (int i = 0; i < in.length(); i++)
         if (opers.indexOf( in.charAt(i) ) == -1)
            out.append( in.charAt(i) );
         else {
            while ( ! (empty = opstk.isEmpty())
                    &&  precedence( topsym = opstk.pop(), in.charAt(i) ))
               out.append( topsym );
            if ( ! empty)                     opstk.push( topsym );
            if (empty || in.charAt(i) != ')') opstk.push( in.charAt(i) );
            else                              topsym = opstk.pop();
         }
      while ( ! opstk.isEmpty()) out.append( opstk.pop() );
      return out.toString();
   }
   public static int evaluate( String in ) {
      StkInt stack = new StkInt();
      String opers = "+-*/";
      for (int a, b, i=0; i < in.length(); i++)
         if (opers.indexOf( in.charAt(i) ) == -1)
            stack.push( in.charAt(i)-48 );
         else {
            b = stack.pop();  a = stack.pop();
            if      (in.charAt(i) == '+') a = a + b;
            else if (in.charAt(i) == '-') a = a - b;
            else if (in.charAt(i) == '*') a = a * b;
            else if (in.charAt(i) == '/') a = a / b;
            stack.push( a );
         }
      return stack.pop();
   }
   public static void main( String[] args ) {
      System.out.print( args[0] );
      String postfix = convertToPostfix( args[0] );
      System.out.print( " -- " + postfix );
      System.out.println( " -- " + evaluate( postfix ) );
} 

class StkChar {
   private char[] arr = new char[9];
   private int    sp  = -1;
   void    push( char ch ) { if ( ! isFull()) arr[++sp] = ch; }
   char    pop()           { if (isEmpty()) return '\0';  return arr[sp--]; }
   boolean isFull()        { return sp == arr.length-1; }
   boolean isEmpty()       { return sp == -1; }
}

class StkInt {
   private int[] arr = new int[9];
   private int   sp  = -1;
   void    push( int ch ) { if ( ! isFull()) arr[++sp] = ch; }
   int     pop()          { if (isEmpty()) return 0;  return arr[sp--]; }
   boolean isFull()       { return sp == arr.length-1; }
   boolean isEmpty()      { return sp == -1; }
}}
