import java.util.*;

class Memento {
   private String state;

   public Memento(String stateToSave) { state = stateToSave; }
   public String getSavedState() { return state; }
}

class Originator {
   private String state;
   /* lots of memory consumptive private data that is not necessary to define the 
    * state and should thus not be saved. Hence the small memento object. */

   public void set(String state) { 
       System.out.println("Originator: Setting state to "+state);
       this.state = state; 
   }

   public Memento saveToMemento() { 
       System.out.println("Originator: Saving to Memento.");
       return new Memento(state); 
   }
   public void restoreFromMemento(Memento m) {
       state = m.getSavedState(); 
       System.out.println("Originator: State after restoring from Memento: "+state);
   }
}   

class Caretaker {
   private ArrayList<Memento> savedStates = new ArrayList<Memento>();

   public void addMemento(Memento m) { savedStates.add(m); }
   public Memento getMemento(int index) { return savedStates.get(index); }
}   

class MementoExample {
   public static void main(String[] args) {
       Caretaker caretaker = new Caretaker();

       Originator originator = new Originator();
       originator.set("State1");
       originator.set("State2");
       caretaker.addMemento( originator.saveToMemento() );
       originator.set("State3");
       caretaker.addMemento( originator.saveToMemento() );
       originator.set("State4");

       originator.restoreFromMemento( caretaker.getMemento(1) );
   }
}
