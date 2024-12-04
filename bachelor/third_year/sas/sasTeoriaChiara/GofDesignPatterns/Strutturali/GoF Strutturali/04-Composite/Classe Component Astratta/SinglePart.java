public class SinglePart extends Component {
    public SinglePart(String aName) {
        super(aName);
    }
    public void add(Component c) throws SinglePartException{
        throw new SinglePartException( );
    }
    public void remove(Component c) throws SinglePartException{
        throw new SinglePartException( );
    }
    public Component getChild(int n) {
        return null;
    }
    public void describe(){
        System.out.println( "Component: " + name );
    }
}
