import java.util.Collection;

public interface Visitor {
    public void visit( Collection collection );
    public void visit( VisitableString string );
    public void visit( VisitableFloat nFloat );
}
