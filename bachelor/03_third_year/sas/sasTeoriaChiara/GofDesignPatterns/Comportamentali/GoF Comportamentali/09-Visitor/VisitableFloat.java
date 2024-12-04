public class VisitableFloat implements Visitable {
    private Float value;
    public VisitableFloat(float f) {
        value = new Float( f );
    }
    public Float getFloat() {
        return value;
    }
    public void accept( Visitor visitor ) {
        visitor.visit( this );
    }
}
