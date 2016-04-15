#define MOTIF

class Widget {
public:
   virtual void draw() = 0;
};

class MotifButton : public Widget {
public:
   void draw() { cout << "MotifButton\n"; }
};
class MotifMenu : public Widget {
public:
   void draw() { cout << "MotifMenu\n"; }
};

class WindowsButton : public Widget {
public:
   void draw() { cout << "WindowsButton\n"; }
};
class WindowsMenu : public Widget {
public:
   void draw() { cout << "WindowsMenu\n"; }
};

void display_window_one() {
#ifdef MOTIF
   Widget* w[] = { new MotifButton,
                   new MotifMenu };
#else // WINDOWS
   Widget* w[] = { new WindowsButton,
                   new WindowsMenu };
#endif
   w[0]->draw();  w[1]->draw();
}

void display_window_two() {
#ifdef MOTIF
   Widget* w[] = { new MotifMenu,
                   new MotifButton };
#else // WINDOWS
   Widget* w[] = { new WindowsMenu,
                   new WindowsButton };
#endif
   w[0]->draw();  w[1]->draw();
}

int main() {
#ifdef MOTIF
   Widget* w = new MotifButton;
#else // WINDOWS
   Widget* w = new WindowsButton;
#endif
   w->draw();
   display_window_one();
   display_window_two();
}