public abstract class CPU
{
  ...
} // class CPU

class EmberCPU extends CPU
{
  ...
} // class EmberCPU

class EmberToolkit extends ArchitectureToolkit
{
  public CPU createCPU()
  {
    return new EmberCPU();
  } // createCPU()

  public MMU createMMU()
  {
    return new EmberMMU();
  } // createMMU()
  ...
} // class EmberFactory

public abstract class ArchitectureToolkit
{
  private static final EmberToolkit emberToolkit = new EmberToolkit();
  private static final EnginolaToolkit enginolaToolkit = new EnginolaToolkit();
  ...

  // Returns a concrete factory object that is an instance of the
  // concrete factory class appropriate for the given architecture.
  static final ArchitectureToolkit getFactory(int architecture)
  {
    switch (architecture)
    {
      case ENGINOLA:
        return enginolaToolkit;

      case EMBER:
        return emberToolkit;
        ...
    } // switch
    String errMsg = Integer.toString(architecture);
    throw new IllegalArgumentException(errMsg);
  } // getFactory()

  public abstract CPU createCPU();
  public abstract MMU createMMU();
  ...
} // AbstractFactory
 
public class Client
{
  public void doIt()
  {
    AbstractFactory af;
    af = AbstractFactory.getFactory(AbstractFactory.EMBER);
    CPU cpu = af.createCPU();
    ...
  } // doIt
} // class Client

