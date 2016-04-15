class A {
  public:
    virtual void do_it() {
        cout << 'A';
    }
};

class AwithX: public A {
  public:
    /*virtual*/
    void do_it() {
        A::do_it();
        do_X();
    };
  private:
    void do_X() {
        cout << 'X';
    }
};

class AwithY: public A {
  public:
    /*virtual*/
    void do_it() {
        A::do_it();
        do_Y();
    }
  protected:
    void do_Y() {
        cout << 'Y';
    }
};

class AwithZ: public A {
  public:
    /*virtual*/
    void do_it() {
        A::do_it();
        do_Z();
    }
  protected:
    void do_Z() {
        cout << 'Z';
    }
};

class AwithXY: public AwithX, public AwithY
{
  public:
    /*virtual*/
    void do_it() {
        AwithX::do_it();
        AwithY::do_Y();
    }
};

class AwithXYZ: public AwithX, public AwithY, public AwithZ
{
  public:
    /*virtual*/
    void do_it() {
        AwithX::do_it();
        AwithY::do_Y();
        AwithZ::do_Z();
    }
};

int main() {
  AwithX anX;
  AwithXY anXY;
  AwithXYZ anXYZ;
  anX.do_it();
  cout << '\n';
  anXY.do_it();
  cout << '\n';
  anXYZ.do_it();
  cout << '\n';
}
