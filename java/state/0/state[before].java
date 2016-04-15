// Not good: unwieldy "case" statement

class CeilingFanPullChain
{
    private int m_current_state;

    public CeilingFanPullChain()
    {
        m_current_state = 0;
    }
    public void pull()
    {
        if (m_current_state == 0)
        {
            m_current_state = 1;
            System.out.println("   low speed");
        }
        else if (m_current_state == 1)
        {
            m_current_state = 2;
            System.out.println("   medium speed");
        }
        else if (m_current_state == 2)
        {
            m_current_state = 3;
            System.out.println("   high speed");
        }
        else
        {
            m_current_state = 0;
            System.out.println("   turning off");
        }
    }
}

public class StateDemo
{
    public static void main(String[] args)
    {
        CeilingFanPullChain chain = new CeilingFanPullChain();
        while (true)
        {
            System.out.print("Press ");
            get_line();
            chain.pull();
        }
    }
    static String get_line()
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in))
          ;
        String line = null;
        try
        {
            line = in.readLine();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return line;
    }
}
