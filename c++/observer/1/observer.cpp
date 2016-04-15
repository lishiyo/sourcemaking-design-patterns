#include <iostream>
#include <vector>
using namespace std;

class AlarmListener
{
  public:
    virtual void alarm() = 0;
};

class SensorSystem
{
    vector < AlarmListener * > listeners;
  public:
    void attach(AlarmListener *al)
    {
        listeners.push_back(al);
    }
    void soundTheAlarm()
    {
        for (int i = 0; i < listeners.size(); i++)
          listeners[i]->alarm();
    }
};

class Lighting: public AlarmListener
{
  public:
     /*virtual*/void alarm()
    {
        cout << "lights up" << '\n';
    }
};

class Gates: public AlarmListener
{
  public:
     /*virtual*/void alarm()
    {
        cout << "gates close" << '\n';
    }
};

class CheckList
{
    virtual void localize()
    {
        cout << "   establish a perimeter" << '\n';
    }
    virtual void isolate()
    {
        cout << "   isolate the grid" << '\n';
    }
    virtual void identify()
    {
        cout << "   identify the source" << '\n';
    }
  public:
    void byTheNumbers()
    {
        // Template Method design pattern
        localize();
        isolate();
        identify();
    }
};
// class inheri.  // type inheritance
class Surveillance: public CheckList, public AlarmListener
{
     /*virtual*/void isolate()
    {
        cout << "   train the cameras" << '\n';
    }
  public:
     /*virtual*/void alarm()
    {
        cout << "Surveillance - by the numbers:" << '\n';
        byTheNumbers();
    }
};

int main()
{
  SensorSystem ss;
  ss.attach(&Gates());
  ss.attach(&Lighting());
  ss.attach(&Surveillance());
  ss.soundTheAlarm();
}
