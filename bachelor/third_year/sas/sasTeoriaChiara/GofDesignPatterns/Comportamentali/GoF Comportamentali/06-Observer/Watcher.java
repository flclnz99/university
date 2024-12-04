import java.util.Observer;
import java.util.Observable;

public class Watcher implements Observer {

    private int changes = 0;

    public void update(Observable obs, Object arg) {
        System.out.println( "Watcher : I see that the Subject holds now a "
            + ((ObservedSubject) obs ).returnValue() + ".");
        changes++;
    }

    public int observedChanges() {
        return changes;
    }

}
