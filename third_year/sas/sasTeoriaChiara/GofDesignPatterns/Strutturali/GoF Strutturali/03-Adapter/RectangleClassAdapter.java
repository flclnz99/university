public class RectangleClassAdapter extends Rectangle implements Polygon {
    private String name = "NO NAME";
    public void define( float x0, float y0, float x1, float y1, String color ) {
        float a = x1 - x0;
        float l = y1 - y0;
        setShape( x0, y0, a, l, color );
    }
    public float getSurface() {
        return getArea();
    }
    public float[] getCoordinates() {
        float aux[] = new float[4];
        aux[0] = getOriginX();
        aux[1] = getOriginY();
        aux[2] = getOppositeCornerX();
        aux[3] = getOppositeCornerY();
        return aux;
    }
    public void setId( String id ) {
        name = id;
    }
    public String getId( ) {
        return name;
    }
}
