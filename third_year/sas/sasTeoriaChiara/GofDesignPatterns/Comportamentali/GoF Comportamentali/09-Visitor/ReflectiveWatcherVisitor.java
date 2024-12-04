import java.util.Vector;
import java.util.Collection;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class ReflectiveWatcherVisitor implements ReflectiveVisitor {
    public void visit(Collection collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            visit( o );
        }
    }
    public void visit(String vString) {
        System.out.println( "’" + vString + "’" );
    }
    public void visit(Float vFloat) {
        System.out.println( vFloat.toString() + "f" );
    }
    public void defaultVisit (Object o) {
        if (o instanceof Collection )
            visit( (Collection) o );
        else
            System.out.println(o.toString());
    }
    public void visit(Object o) {
        try {
            System.out.println( o.getClass().getName() );
            Method m = getClass().getMethod( "visit", new Class[] { o.getClass() });
            m.invoke(this, new Object[] { o });
        } catch (NoSuchMethodException e) {
            // Do the default implementation
            defaultVisit(o);
        } catch (IllegalAccessException e) {
            // Do the default implementation
            defaultVisit(o);
        } catch (InvocationTargetException e) {
            // Do the default implementation
            defaultVisit(o);
        }
    }
}
