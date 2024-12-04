public class ObserverExample {

    public static void main (String[] args) {

        ObservedSubject s = new ObservedSubject() ;
        Watcher o = new Watcher();
        Psychologist p = new Psychologist();
        s.addObserver( o );
        s.addObserver( p );
        for(int i=1;i<=10;i++){
            System.out.println( "Main : Do you like the number " + i +"?" );
            s.receiveValue( i );
        }
        System.out.println( "The Subject has changed " +
            o.observedChanges()
            + " times the internal value.");
        System.out.println("The Psychologist opinion is:" + p.opinion() );
    }

}
