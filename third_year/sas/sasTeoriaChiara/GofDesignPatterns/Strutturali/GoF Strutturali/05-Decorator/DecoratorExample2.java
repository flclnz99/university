public class DecoratorExample2 {
    public static void main( String[] arg ) {
        System.out.println( "Non synchronized point:" );
        DiagonalDraggablePoint p = new SequentialPoint();
        PointDragger mp1 = new PointDragger( p, "Thread 1" );
        PointDragger mp2 = new PointDragger( p, "Thread 2" );
        Thread t1 = new Thread( mp1 );
        Thread t2 = new Thread( mp2 );
        t1.start();
        t2.start();
        while( t1.isAlive() || t2.isAlive() );
        p.currentPosition();
        System.out.println( "Synchronized point:" );
        p = new SynchronizedPoint( new SequentialPoint() );
        mp1 = new PointDragger( p, "Thread 1" );
        mp2 = new PointDragger( p, "Thread 2" );
        t1 = new Thread( mp1 );
        t2 = new Thread( mp2 );
        t1.start();
        t2.start();
        while( t1.isAlive() || t2.isAlive() );
        p.currentPosition();
    }
}

class PointDragger implements Runnable {
    DiagonalDraggablePoint point;
    String name ;
    public PointDragger( DiagonalDraggablePoint p, String nom ) {
        point = p;
        name = nom;
    }
    public void run() {
        for( int i=1; i < 5; i++ ) {
            point.moveDiagonal( 1, name );
        }
    }
}
