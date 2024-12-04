public abstract class Component {
    public String name;
    public Component(String aName){
        name = aName;
    }
    public abstract void describe();
    public abstract void add(Component c) throws SinglePartException;
    public abstract void remove(Component c) throws SinglePartException;
    public abstract Component getChild(int n);
}
