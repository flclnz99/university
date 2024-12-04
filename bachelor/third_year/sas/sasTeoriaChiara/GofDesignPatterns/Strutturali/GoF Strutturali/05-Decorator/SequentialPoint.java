public class SequentialPoint implements DiagonalDraggablePoint {
    private int x, y;
    public SequentialPoint( ) {
        this.x = 0;
        this.y = 0;
    }
    public void moveDiagonal( int distance, String draggerName ) {
        int aux = x + distance ;
        System.out.println( "Moved by " + draggerName +
            " - Origin x=" + x + " y=" + y );
        x = aux;
        y = y + distance;
    }
    public void currentPosition( ) {
        System.out.println( "Current position : x=" + x + " y=" + y );
    }
}
