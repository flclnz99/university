public class SynchronizedPoint implements DiagonalDraggablePoint {
    DiagonalDraggablePoint theSequentialPoint;
    public SynchronizedPoint(DiagonalDraggablePoint np) {
        theSequentialPoint = np;
    }
    public void moveDiagonal( int distance, String draggerName ) {
        synchronized(theSequentialPoint) {
            theSequentialPoint.moveDiagonal( distance, draggerName );
        }
    }
    public void currentPosition( ) {
        theSequentialPoint.currentPosition();
    }
}
