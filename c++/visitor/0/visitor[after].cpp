class Color
{
  public:
    virtual void accept(class Visitor*) = 0;
};

class Red: public Color
{
  public:
     /*virtual*/void accept(Visitor*);
    void eye()
    {
        cout << "Red::eye\n";
    }
};
class Blu: public Color
{
  public:
     /*virtual*/void accept(Visitor*);
    void sky()
    {
        cout << "Blu::sky\n";
    }
};

class Visitor
{
  public:
    virtual void visit(Red*) = 0;
    virtual void visit(Blu*) = 0;
};

class CountVisitor: public Visitor
{
  public:
    CountVisitor()
    {
        m_num_red = m_num_blu = 0;
    }
     /*virtual*/void visit(Red*)
    {
        ++m_num_red;
    }
     /*virtual*/void visit(Blu*)
    {
        ++m_num_blu;
    }
    void report_num()
    {
        cout << "Reds " << m_num_red << ", Blus " << m_num_blu << '\n';
    }
  private:
    int m_num_red, m_num_blu;
};

class CallVisitor: public Visitor
{
  public:
     /*virtual*/void visit(Red *r)
    {
        r->eye();
    }
     /*virtual*/void visit(Blu *b)
    {
        b->sky();
    }
};

void Red::accept(Visitor *v)
{
  v->visit(this);
}

void Blu::accept(Visitor *v)
{
  v->visit(this);
}

int main()
{
  Color *set[] = 
  {
    new Red, new Blu, new Blu, new Red, new Red, 0
  };
  CountVisitor count_operation;
  CallVisitor call_operation;
  for (int i = 0; set[i]; i++)
  {
    set[i]->accept(&count_operation);
    set[i]->accept(&call_operation);
  }
  count_operation.report_num();
}
