package main;

import model.Geometries;
import model.*;

import java.util.*;

public class GeometriesExample {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Geometries geometries = new Geometries();
        boolean mainLoop = true;
        int choice;


        while(true) {

            System.out.println("Menu\n");
            System.out.print("1.) Add a Rectangle.\n");
            System.out.print("2.) Add a Triangle.\n");
            System.out.print("3.) Add a Square.\n");
            System.out.print("4.) Add a Parallelogram.\n");
            System.out.print("5.) List geometries.\n");
            System.out.print("6.) Number of geometries.\n");
            System.out.print("\nEnter Your Menu Choice: ");

            choice = input.nextInt();
            Polygon p;

            switch(choice){
                case 1:
                    p = new Rectangle();
                    addPolygon(input, geometries, p);
                    break;

                case 2:
                    p = new Triangle();
                    addPolygon(input, geometries, p);
                    break;
                case 3:
                    p = new Square();
                    addPolygon(input, geometries, p);
                    break;
                case 4:
                    p = new Parallelogram();
                    addPolygon(input, geometries, p);
                    break;
                case 5:
                    geometries.printDescription();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Number of geometries in the collection: "+geometries.getNElements());
                    System.out.println();
                    break;
            } // end of switch

        }
    }

    private static void addPolygon(Scanner input, Geometries geometries, Polygon p) {
        String[] attributesNames = p.describeAttributes();
        double[] attributesValues = new double[attributesNames.length];
        int i = 0;
        for (String attr: attributesNames) {
            System.out.println("Enter "+attr+" (double):");
            double current = input.nextDouble();
            attributesValues[i] = current;
            i++;
        }
        p.parseAttributes(attributesValues);
        geometries.add(p);
        System.out.println("Geometry "+p.getClass().getName()+" added.\n");
    }


}
