import java.util.Vector;

public class ReflectiveVisitorExample {
    public static void main (String[] arg) {
        // Prepare a heterogeneus collection
        Vector untidyObjectCase = new Vector();
        untidyObjectCase.add( "A string" );
        untidyObjectCase.add( new Float( 1 ) );
        Vector aVector = new Vector();
        aVector.add( "Another string" );
        aVector.add( new Float( 2 ) );
        untidyObjectCase.add( aVector );
        untidyObjectCase.add( new Float( 3 ) );
        untidyObjectCase.add( new Double( 4 ) );
        // Visit the collection
        ReflectiveVisitor browser = new ReflectiveWatcherVisitor();
        browser.visit( untidyObjectCase );
    }
}
