package model;

public class Triangle extends Polygon{
    private int base;
    private int heigth;

    public Triangle(int base, int heigth){
        super(3);
        this.base=base;
        this.heigth=heigth;
    }

    public int getArea(){return base*heigth/2;}
    public String[] getAttributes(){return new String[]{"base","height"};}

    public boolean equals(Triangle t){
        if(t==null || this.getClass()!=t.getClass()){
            return false;
        } else if(this.base==t.base && this.heigth==t.heigth){
            return true;
        } else {
            return false;
        }
    }
}
