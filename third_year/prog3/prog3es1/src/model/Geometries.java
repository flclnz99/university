package model;

import java.util.ArrayList;

public class Geometries{
    private ArrayList<Polygon> arrayList;

    public Geometries(){
       arrayList = new ArrayList<>();
    }

    public int getNumPolygon(){return arrayList.size();}

    public boolean addPolygon(Polygon p){
        for(int i=0; i<arrayList.size(); i++){
            if(arrayList.get(i).equals(p)){
               return false;
            }
        }
        arrayList.add(p);
        return true;
    }

    public void printDescription() {
        System.out.println("Geometries collection:");
        for (Polygon p:this.arrayList) {
            System.out.println("Polygon class: " + p.getClass() + " Area: " + p.getArea());
        }
    }
}
