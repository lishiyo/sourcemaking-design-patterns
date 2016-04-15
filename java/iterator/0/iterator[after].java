class SomeClassWithData
{
    private TreeSet < Integer > m_data = new TreeSet < Integer > ();

    publicclass Iterator
    {
        private SomeClassWithData m_collection;
        private java.util.Iterator m_it;
        private int m_current;
        public Iterator(SomeClassWithData in)
        {
            m_collection = in;
        }
        public void first()
        {
            m_it = m_collection.m_data.iterator();
            next();
        }
        public void next()
        {
            try
            {
                m_current = m_it.next();
            }
            catch (NoSuchElementException ex)
            {
                m_current =  - 999;
            }
        }
        public boolean is_done()
        {
            return m_current ==  - 999;
        }
        public int current_item()
        {
            return m_current;
        }
    }

    public void add(int in)
    {
        m_data.add(in);
    }
    public Iterator create_iterator()
    {
        return new Iterator(this);
    }
}

class IteratorDemo
{
    public static void main(String[] args)
    {
        SomeClassWithData some_object = new SomeClassWithData();
        for (int i = 9; i > 0; --i)
          some_object.add(i);

        // get_data() has been removed.
        // Client has to use Iterator.
        SomeClassWithData.Iterator it1 = some_object.create_iterator();
        SomeClassWithData.Iterator it2 = some_object.create_iterator();

        for (it1.first(); !it1.is_done(); it1.next())
          System.out.print(it1.current_item() + "  ");
        System.out.println();

        // Two simultaneous iterations
        for (it1.first(), it2.first(); !it1.is_done(); it1.next(), it2.next())
          System.out.print(it1.current_item() + " " + it2.current_item() + "  ")
            ;
        System.out.println();
    }
}
