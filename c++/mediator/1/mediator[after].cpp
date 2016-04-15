class Node
{
  public:
    Node(int v)
    {
        m_val = v;
    }
    int get_val()
    {
        return m_val;
    }
  private:
    int m_val;
};

class List
{
  public:
    void add_node(Node *n)
    {
        m_arr.push_back(n);
    }
    void traverse()
    {
        for (int i = 0; i < m_arr.size(); ++i)
          cout << m_arr[i]->get_val() << "  ";
        cout << '\n';
    }
    void remove_node(int v)
    {
        for (vector::iterator it = m_arr.begin(); it != m_arr.end(); ++it)
        if ((*it)->get_val() == v)
        {
            m_arr.erase(it);
            break;
        }
    }
  private:
    vector m_arr;
};

int main()
{
  List lst;
  Node one(11), two(22);
  Node thr(33), fou(44);
  lst.add_node(&one);
  lst.add_node(&two);
  lst.add_node(&thr);
  lst.add_node(&fou);
  lst.traverse();
  lst.remove_node(44);
  lst.traverse();
  lst.remove_node(22);
  lst.traverse();
  lst.remove_node(11);
  lst.traverse();
}
