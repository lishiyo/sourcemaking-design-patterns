class Node
{
  public:
    Node(int v)
    {
        m_val = v;
        m_next = 0;
    }
    void add_node(Node *n)
    {
        if (m_next)
          m_next->add_node(n);
        else
          m_next = n;
    }
    void traverse()
    {
        cout << m_val << "  ";
        if (m_next)
          m_next->traverse();
        else
          cout << '\n';
    }
    void remove_node(int v)
    {
        if (m_next)
          if (m_next->m_val == v)
            m_next = m_next->m_next;
          else
            m_next->remove_node(v);
    }
  private:
    int m_val;
    Node *m_next;
};

int main()
{
  Node lst(11);
  Node two(22), thr(33), fou(44);
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
