package prim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Comparator;
import graph.*;
import priorityqueue.*;
import prim.*;

public class PrimApp {
  public static void loadGraph(Graph<String, Double> graph, String fileName) throws GraphException {
		File file = new File(fileName);
		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNextLine()) {
				String data = inputStream.nextLine();
				String[] array = data.split(",");
				String source = array[0];
				graph.addNode(source);
				String destination = array[1];
				graph.addNode(destination);
				// System.out.println("destination: " + destination + "\n");
				double weight = Double.parseDouble(array[2]);
				graph.addEdge(source, destination, weight);
				// System.out.println("weight: " + weight + "\n");
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

  public static void testPrim(String file_name, Comparator<PrimNode<String, Double>> comparator) throws GraphException, PriorityQueueException{
    Graph<String, Double> italianDistGraph = new Graph<>(false, true);
		loadGraph(italianDistGraph, file_name);
    //italianDistGraph.printGraph();
    //System.out.println("num nodi:"+italianDistGraph.numNodes());
    //System.out.println("num archi:"+italianDistGraph.numEdges());
    //italianDistGraph.getNodes();
		
		Prim<String, Double> prim = new Prim<>(italianDistGraph, comparator);
		Graph<String, Double> outputGraph = prim.primMST(italianDistGraph);
		//italianDistGraph.printGraph();
		//outputGraph.printGraph();
		//System.out.println("\n\n");
		//System.out.println("num nodi:"+italianDistGraph.numNodes());
    //System.out.println("num archi:"+italianDistGraph.numEdges());
		//System.out.println("peso totale:"+italianDistGraph.graphWeight());
		//System.out.println("\n");
		double graphWeight = outputGraph.graphWeight()/1000;
		System.out.println("num nodi:"+outputGraph.numNodes());
    System.out.println("num archi:"+outputGraph.numEdges());
		System.out.println("peso totale:"+graphWeight);
  }

  public static void main(String[] args) throws IOException, GraphException, Exception {
    if (args.length < 1)
			throw new Exception("Usage: PrimUsageJava <file_name>"); 
      testPrim(args[0], new PrimNodeComparatorDouble());
	}
}
