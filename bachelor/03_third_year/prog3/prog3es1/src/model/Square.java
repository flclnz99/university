package model;

public class Square extends Polygon {
    private int side;

    public Square(int side) {
        super(4);
        this.side=side;
    }

    public int getArea(){return side*side;}
    public String[] getAttributes(){return new String[]{"side"};}

    public boolean equals(Square s){
        if(s==null || this.getClass()!=s.getClass()){
            return false;
        } else if(this.side==s.side){
            return true;
        } else {
            return false;
        }
    }
}
