class SomeClassWithData
{
    private TreeSet < Integer > m_data = new TreeSet < Integer > ();

    public void add(int in)
    {
        m_data.add(in);
    }
    public Collection get_data()
    {
        return m_data;
    }
}

class IteratorDemo
{
    public static void main(String[] args)
    {
        SomeClassWithData some_object = new SomeClassWithData();
        for (int i = 9; i > 0; --i)
          some_object.add(i);
        Collection data = some_object.get_data();
        for (java.util.Iterator it = data.iterator(); it.hasNext();)
          System.out.print(it.next() + "  ");
        System.out.println();

        // Do we really want a client to be able to
        // trash encapsulated state?
        data.clear();
        data = some_object.get_data();
        System.out.println("size of data is " + data.size());
    }
}
