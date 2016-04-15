class Giant
{
  public:
    enum Type
    {
        Fee, Phi, Pheaux
    };
    Giant()
    {
        m_id = s_next++;
        m_type = (Type)(m_id % 3);
    }
    Type get_type()
    {
        return m_type;
    }
    void fee()
    {
        cout << m_id << "-fee  ";
    }
    void phi()
    {
        cout << m_id << "-phi  ";
    }
    void pheaux()
    {
        cout << m_id << "-pheaux  ";
    }
  private:
    Type m_type;
    int m_id;
    static int s_next;
};
int Giant::s_next = 0;

template <typename T> class Queue
{
  public:
    Queue()
    {
        m_add = m_remove = 0;
    }
    void enque(T *c)
    {
        m_array[m_add] = c;
        m_add = (m_add + 1) % SIZE;
    }
    T *deque()
    {
        int temp = m_remove;
        m_remove = (m_remove + 1) % SIZE;
        return m_array[temp];
    }
  private:
    enum
    {
        SIZE = 8
    };
    T *m_array[SIZE];
    int m_add, m_remove;
};

int main()
{
  Queue que;
  Giant input[6],  *bad_guy;

  for (int i = 0; i < 6; i++)
    que.enque(&input[i]);

  for (int i = 0; i < 6; i++)
  {
    bad_guy = que.deque();
    if (bad_guy->get_type() == Giant::Fee)
      bad_guy->fee();
    else if (bad_guy->get_type() == Giant::Phi)
      bad_guy->phi();
    else if (bad_guy->get_type() == Giant::Pheaux)
      bad_guy->pheaux();
  }
  cout << '\n';
}
