package model;

public class Parallelogram extends Polygon {
    private int base;
    private int height;

    public Parallelogram(int base, int height) {
        super(4);
        this.height=height;
        this.base=base;
    }

    public int getArea(){return base*height;}
    public String[] getAttributes(){return new String[]{"base", "altezza"};}

    public boolean equals(Parallelogram p){
        if(p==null || this.getClass()!=p.getClass()){
            return false;
        } else if(this.base==p.base && this.height==p.height){
            return true;
        } else {
            return false;
        }
    }
}
