#include <iostream>
#include <vector>
using namespace std;

class Number
{
  public:
    void dubble(int &value)
    {
        value *= 2;
    }
};

class Command
{
  public:
    virtual void execute(int &) = 0;
};

class SimpleCommand: public Command
{
    typedef void(Number:: *Action)(int &);
    Number *receiver;
    Action action;
  public:
    SimpleCommand(Number *rec, Action act)
    {
        receiver = rec;
        action = act;
    }
     /*virtual*/void execute(int &num)
    {
        (receiver-> *action)(num);
    }
};

class MacroCommand: public Command
{
    vector < Command * > list;
  public:
    void add(Command *cmd)
    {
        list.push_back(cmd);
    }
     /*virtual*/void execute(int &num)
    {
        for (int i = 0; i < list.size(); i++)
          list[i]->execute(num);
    }
};

int main()
{
  Number object;
  Command *commands[3];
  commands[0] = &SimpleCommand(&object, &Number::dubble);

  MacroCommand two;
  two.add(commands[0]);
  two.add(commands[0]);
  commands[1] = &two;

  MacroCommand four;
  four.add(&two);
  four.add(&two);
  commands[2] = &four;

  int num, index;
  while (true)
  {
    cout << "Enter number selection (0=2x 1=4x 2=16x): ";
    cin >> num >> index;
    commands[index]->execute(num);
    cout << "   " << num << '\n';
  }
}
