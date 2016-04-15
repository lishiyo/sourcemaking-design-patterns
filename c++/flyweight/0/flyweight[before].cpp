class Gazillion
{
  public:
    Gazillion()
    {
        m_value_one = s_num / Y;
        m_value_two = s_num % Y;
        ++s_num;
    }
    void report()
    {
        cout << m_value_one << m_value_two << ' ';
    }
    static int X, Y;
  private:
    int m_value_one;
    int m_value_two;
    static int s_num;
};

int Gazillion::X = 6, Gazillion::Y = 10, Gazillion::s_num = 0;

int main()
{
  Gazillion matrix[Gazillion::X][Gazillion::Y];
  for (int i = 0; i < Gazillion::X; ++i)
  {
    for (int j = 0; j < Gazillion::Y; ++j)
      matrix[i][j].report();
    cout << '\n';
  }
}
