package model;

public class Rectangle extends Polygon{
    private int base;
    private int height;

    public Rectangle(int base, int height) {
        super(4);
        this.base=base;
        this.height=height;
    }

    public int getArea(){return base*height/2;}
    public int getPerimeter(){return this.base*2+this.height*2;}
    public String[] getAttributes(){return new String[]{"base", "altezza"};}

    public boolean equals(Rectangle r){
        if(r==null || this.getClass()!=r.getClass()){
            return false;
        } else if(this.base==r.base && this.height==r.height){
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        return "base: " + base + "height: " + height;
    }
}
