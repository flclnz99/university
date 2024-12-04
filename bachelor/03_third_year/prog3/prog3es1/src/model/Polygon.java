package model;

public abstract class Polygon {
    private final int numVertices;

    public Polygon(int numVertices) {
        this.numVertices=numVertices;
    }

    public int getNumVertices(){
        return numVertices;
    }

    public abstract int getArea();
    public abstract String[] getAttributes();

    @Override
    public String toString() {
        return " - " + numVertices + " - ";
    }

    @Override
    public boolean equals(Object o){
        if(o!=null && this.getClass() == o.getClass()){
            Polygon a = (Polygon) o;
            return this.numVertices == a.numVertices;
        } else {
            return false;
        }
    }
}
