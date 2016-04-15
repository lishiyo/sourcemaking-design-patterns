class JTable_Table
{
    private JTable m_table;

    public JTable_Table(String[][] matrix)
    {
        m_table = new JTable(matrix[0].length, matrix.length);
        TableModel model = m_table.getModel();
        for (int i = 0; i < matrix.length; ++i)
          for (int j = 0; j < matrix[i].length; ++j)
            model.setValueAt(matrix[i][j], j, i);
    }
    public Component get_table()
    {
        return m_table;
    }
}

class GridLayout_Table
{
    private JPanel m_table = new JPanel();

    public GridLayout_Table(String[][] matrix)
    {
        m_table.setLayout(new GridLayout(matrix[0].length, matrix.length));
        m_table.setBackground(Color.white);
        for (int i = 0; i < matrix[i].length; ++i)
          for (int j = 0; j < matrix.length; ++j)
            m_table.add(new Label(matrix[j][i]));
    }
    public Component get_table()
    {
        return m_table;
    }
}

class GridBagLayout_Table
{
    private JPanel m_table = new JPanel();

    public GridBagLayout_Table(String[][] matrix)
    {
        GridBagConstraints c = new GridBagConstraints();
        m_table.setLayout(new GridBagLayout());
        m_table.setBackground(Color.white);
        for (int i = 0; i < matrix.length; ++i)
        for (int j = 0; j < matrix[i].length; ++j)
        {
            c.gridx = i;
            c.gridy = j;
            m_table.add(new Label(matrix[i][j]), c);
        }
    }
    public Component get_table()
    {
        return m_table;
    }
}

public class BuilderDemo
{
    public static String[][] read_data_file(String file_name)
    {
        String[][] matrix = null;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file_name));
            String line, cell = "";
            String[] tokens;
            boolean first_line = true;
            int row = 0, col = 0;
            while ((line = br.readLine()) != null)
            {
                // Use "whitespace" to tokenize each line
                // java.sun.com/docs/books/tutorial/extra/
                //    regex/pre_char_classes.html
                tokens = line.split("\\s");
                int i = 0;
                if (first_line)
                {
                    matrix = new String[Integer.parseInt(tokens[0])
                      ][Integer.parseInt(tokens[1])];
                    i = 2;
                    first_line = false;
                }
                for (; i < tokens.length; ++i)
                if (tokens[i].equals(""))
                {
                    matrix[col][row++] = cell;
                    cell = "";
                    col = 0;
                }
                else if (tokens[i].equals(""))
                {
                    matrix[col++][row] = cell;
                    cell = "";
                }
                else
                {
                    cell += " " + tokens[i];
                }
            }
            matrix[col][row] = cell;
            br.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return matrix;
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("BuilderDemo - " + args[0]);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[][] matrix = read_data_file("BuilderDemo.dat");
        if (args[0].equals("JTable_Table"))
          frame.getContentPane().add(new JTable_Table(matrix).get_table());
        else if (args[0].equals("GridLayout_Table"))
          frame.getContentPane().add(new GridLayout_Table(matrix).get_table());
        else if (args[0].equals("GridBagLayout_Table"))
          frame.getContentPane().add(new GridBagLayout_Table(matrix).get_table()
            );
        frame.pack();
        frame.setVisible(true);
    }
}

