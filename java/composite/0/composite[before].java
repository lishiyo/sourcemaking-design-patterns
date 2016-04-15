class File
{
    public File(String name)
    {
        m_name = name;
    }
    public void ls()
    {
        System.out.println(Composite.g_indent + m_name);
    }
    private String m_name;
}

class Directory
{
    public Directory(String name)
    {
        m_name = name;
    }
    public void add(Object obj)
    {
        m_files.add(obj);
    }
    public void ls()
    {
        System.out.println(Composite.g_indent + m_name);
        Composite.g_indent.append("   ");
        for (int i = 0; i < m_files.size(); ++i)
        {
            Object obj = m_files.get(i);
            // Recover the type of this object
            if (obj.getClass().getName().equals("Directory"))
              ((Directory)obj).ls();
            else
              ((File)obj).ls();
        }
        Composite.g_indent.setLength(CompositeDemo.g_indent.length() - 3);
    }
    private String m_name;
    private ArrayList m_files = new ArrayList();
}

publicclass CompositeDemo
{
    public static StringBuffer g_indent = new StringBuffer();

    public static void main(String[] args)
    {
        Directory one = new Directory("dir111"), two = new Directory("dir222"),
          thr = new Directory("dir333");
        File a = new File("a"), b = new File("b"), c = new File("c"), d = new
          File("d"), e = new File("e");
        one.add(a);
        one.add(two);
        one.add(b);
        two.add(c);
        two.add(d);
        two.add(thr);
        thr.add(e);
        one.ls();
    }
}
