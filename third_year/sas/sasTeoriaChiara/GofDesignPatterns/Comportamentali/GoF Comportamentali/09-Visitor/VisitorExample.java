import java.util.Vector;

public class VisitorExample {
    public static void main (String[] arg) {
        // Prepare a heterogeneous collection
        Vector untidyObjectCase = new Vector();
        untidyObjectCase.add( new VisitableString( "A string" ) );
        untidyObjectCase.add( new VisitableFloat( 1 ) );
        Vector aVector = new Vector();
        aVector.add( new VisitableString( "Another string" ) );
        aVector.add( new VisitableFloat( 2 ) );
        untidyObjectCase.add( aVector );
        untidyObjectCase.add( new VisitableFloat( 3 ) );
        untidyObjectCase.add( new Double( 4 ) );
        // Visit the collection
        WatcherVisitor browser = new WatcherVisitor();
        browser.visit( untidyObjectCase );  
        System.out.println("Stringhe: " + browser.quantestringhe());
        System.out.println("Numeri float: " + browser.quantinumerifloat());
    }
}
