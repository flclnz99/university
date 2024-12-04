public class VisitableString implements Visitable {
    private String value;
    public VisitableString(String string) {
        value = string;
    }
    public String getString() {
        return value;
    }
    public void accept( Visitor visitor ) {
        visitor.visit( this );
    }
}
