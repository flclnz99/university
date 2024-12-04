public class ObjectAdapterExample {
    public static void main( String[] arg ) {
        Polygon block = new RectangleObjectAdapter();
        block.setId( "Demo" );
        block.define( 3 , 4 , 10, 20, "RED" );
        System.out.println( "The area of "+ block.getId() + " is "+
            block.getSurface() + ", and it’s " +
            block.getColor() );
    }
}
