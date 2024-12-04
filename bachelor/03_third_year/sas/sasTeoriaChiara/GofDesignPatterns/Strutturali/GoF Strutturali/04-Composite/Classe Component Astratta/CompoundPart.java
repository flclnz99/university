import java.util.Vector;
import java.util.Enumeration;

public class CompoundPart extends Component {
    private Vector children ;
    public CompoundPart(String aName) {
        super(aName);
        children = new Vector();
    }
    public void describe(){
        System.out.println("Component: " + name);
        System.out.println("Composed by:");
        System.out.println("{");
        int vLength = children.size();
        for( int i=0; i< vLength ; i ++ ) {
            Component c = (Component) children.get( i );
            c.describe();
        }
        System.out.println("}");
    }
    public void add(Component c) throws SinglePartException {
        children.addElement(c);
    }
    public void remove(Component c) throws SinglePartException{
        children.removeElement(c);
    }
    public Component getChild(int n) {
        return (Component)children.elementAt(n);
    }
}
