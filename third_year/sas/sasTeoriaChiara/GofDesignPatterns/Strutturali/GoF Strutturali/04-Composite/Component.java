public abstract class Component {
    public String name;
    public Component(String aName){
        name = aName;
    }
    public abstract void describe();
    public void add(Component c) throws SinglePartException {
        if (this instanceof SinglePart)
            throw new SinglePartException( );
    }
    public void remove(Component c) throws SinglePartException{
        if (this instanceof SinglePart)
            throw new SinglePartException( );
    }
    public Component getChild(int n){
        return null;
    }
}
