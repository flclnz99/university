public class RectangleObjectAdapter implements Polygon {
    Rectangle adaptee;
    private String name = "NO NAME";
    public RectangleObjectAdapter() {
        adaptee = new Rectangle();
    }
    public void define( float x0, float y0, float x1, float y1, String col ) {
        float a = x1 - x0;
        float l = y1 - y0;
        adaptee.setShape( x0, y0, a, l, col);
    }
    public float getSurface() {
        return adaptee.getArea();
    }
    public float[] getCoordinates() {
        float aux[] = new float[4];
        aux[0] = adaptee.getOriginX();
        aux[1] = adaptee.getOriginY();
        aux[2] = adaptee.getOppositeCornerX();
        aux[3] = adaptee.getOppositeCornerY();
        return aux;
    }
    public void setId( String id ) {
        name = id;
    }
    public String getId( ) {
        return name;
    }
    public String getColor( ) {
        return adaptee.getColor();
    }
}
