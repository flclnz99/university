import java.util.Observer;
//import java.util.Observable;

public class ObservedSubject extends java.util.Observable {

    private int value = 0;

    public void receiveValue( int newNumber ) {
        if (Math.random() < .5) {
             System.out.println( "Subject : I like it, I’ve changed my "
                 + "internal value." );
             value = newNumber;
             this.setChanged();
        } else
             System.out.println( "Subject : I have a number "+ value +
                 " now, and I not interested in the number "
                 + newNumber + "." );
        this.notifyObservers();
    }

    public int returnValue() {
        return value;
    }

}
