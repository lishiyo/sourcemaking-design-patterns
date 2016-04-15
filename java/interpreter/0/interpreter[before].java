public class InterpreterDemo
{
    public static boolean precedence(char a, char b)
    {
        String high = "*/", low = "+-";
        if (a == '(')
          return false;
        if (a == ')' && b == '(')
        {
            System.out.println(")-(");
            return false;
        }
        if (b == '(')
          return false;
        if (b == ')')
          return true;
        if (high.indexOf(a) >  - 1 && low.indexOf(b) >  - 1)
          return true;
        if (high.indexOf(a) >  - 1 && high.indexOf(b) >  - 1)
          return true;
        if (low.indexOf(a) >  - 1 && low.indexOf(b) >  - 1)
          return true;
        return false;
    }
    public static String convert_to_postfix(String expr)
    {
        Stack < Character > op_stack = new Stack < Character > ();
        StringBuffer out = new StringBuffer();
        String opers = "+-*/()";
        char top_sym = '+';
        boolean empty;
        String[] tokens = expr.split(" ");

        for (int i = 0; i < tokens.length; i++)
        if (opers.indexOf(tokens[i].charAt(0)) ==  - 1)
        {
            out.append(tokens[i]);
            out.append(' ');
        }
        else
        {
            while (!(empty = op_stack.isEmpty()) && precedence(top_sym =
              op_stack.pop(), tokens[i].charAt(0)))
            {
                out.append(top_sym);
                out.append(' ');
            }
            if (!empty)
              op_stack.push(top_sym);
            if (empty || tokens[i].charAt(0) != ')')
              op_stack.push(tokens[i].charAt(0));
            else
              top_sym = op_stack.pop();
        }
        while (!op_stack.isEmpty())
        {
            out.append(op_stack.pop());
            out.append(' ');
        }
        return out.toString();
    }
    public static double process_postfix(String postfix, HashMap < String,
      Integer > map)
    {
        Stack < Double > stack = new Stack < Double > ();
        String opers = "+-*/";
        String[] tokens = postfix.split(" ");
        for (int i = 0; i < tokens.length; i++)
        // If token is a number or variable
        if (opers.indexOf(tokens[i].charAt(0)) ==  - 1)
        {
            double term = 0.;
            try
            {
                term = Double.parseDouble(tokens[i]);
            }
            catch (NumberFormatException ex)
            {
                term = map.get(tokens[i]);
            }
            stack.push(term);

            // If token is an operator
        }
        else
        {
            double b = stack.pop(), a = stack.pop();
            if (tokens[i].charAt(0) == '+')
              a = a + b;
            else if (tokens[i].charAt(0) == '-')
              a = a - b;
            else if (tokens[i].charAt(0) == '*')
              a = a * b;
            else if (tokens[i].charAt(0) == '/')
              a = a / b;
            stack.push(a);
        }
        return stack.pop();
    }

    public static void main(String[] args)
    {
        String infix = "C * 9 / 5 + 32";
        String postfix = convert_to_postfix(infix);
        System.out.println("Infix:   " + infix);
        System.out.println("Postfix: " + postfix);
        HashMap < String, Integer > map = new HashMap < String, Integer > ();
        for (int i = 0; i <= 100; i += 10)
        {
            map.put("C", i);
            System.out.println("C is " + i + ",  F is " + process_postfix
              (postfix, map));
        }
    }
}
