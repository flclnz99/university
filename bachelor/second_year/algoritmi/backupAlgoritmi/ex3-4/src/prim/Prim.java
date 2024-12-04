package prim;

import java.net.http.HttpResponse.PushPromiseHandler;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

import javax.naming.spi.DirObjectFactory;

import org.w3c.dom.views.DocumentView;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import graph.*;
import priorityqueue.*;

public class Prim<V,L> {
    private Graph<String,Double> graph;
    private Comparator<? super PrimNode<String,Double>> comparator;
    //private PriorityQueue<PrimNode<V,L>> forest;

	public Prim(Graph<String,Double> graph, Comparator<? super PrimNode<String,Double>> comparator) throws GraphException{
		this.comparator = comparator;
		this.graph = graph;
		//this.forest = new PriorityQueue<>(this.comparator);
	}
/*
	public PriorityQueue<V> makeForest() {
		ArrayList<V> node = (ArrayList<V>) graph.getNodes(); //cast ad arraylist perchè della abstract collection non si può fare la get
		for (int i = 0; i < node.size(); i++) {
			forest.add(node.get(i));
		}
		return forest;
	}

	public PriorityQueue<V> getForest() {
		return forest;
	}
*/

public Graph<String,Double> primMST(Graph<String,Double> initGraph) throws GraphException,PriorityQueueException{
  Graph<String,Double> mst = new Graph<>(this.graph.isDirected(), this.graph.isLabelled());
  PriorityQueue<PrimNode<String,Double>> pq = new PriorityQueue<>(this.comparator);
  HashMap<String,PrimNode<String,Double>> map = new HashMap<>();

  HashSet<String> visited = new HashSet<>();

  PrimNode<String,Double> firstNode = new PrimNode<>(this.graph.getNodes().iterator().next(), null, 0.0);

  //System.out.println(pq.push(firstNode));
  mst.addNode(firstNode.getNode());
  pq.push(firstNode);

  for(String node : graph.getNodes()){
    if((node).equals(firstNode.getNode())){
      continue;
    }
    //System.out.println(node);
    PrimNode<String,Double> primNode = new PrimNode<String,Double>(node, null, Double.MAX_VALUE);
    map.put(primNode.getNode(), primNode);
    pq.push(primNode);
    mst.addNode(node);
    //pq.print();
  }
  //for(int i = 0; i < pq.size(); i++)
  //  System.out.println(pq.get(i).getNode() + " " + pq.get(i).getParent() + " " + pq.get(i).getCost());

  //pq.pop().printNode();
  
  while(!pq.empty()){
    PrimNode<String,Double> currentNode = pq.pop();
    if(currentNode.getParent()!=null){
      mst.addEdge(currentNode.getNode(), currentNode.getParent(), currentNode.getCost());
    }
    
    visited.add(currentNode.getNode());
    for(String neighbour : this.graph.getNeighbours(currentNode.getNode())){
      //System.out.println(neighbour);
      //if v ∈ Q e W(u,v) < v.d then
      if(!visited.contains(neighbour)&&(this.graph.getLabel(currentNode.getNode(),neighbour)<map.get(neighbour).getCost())){
        //System.out.println(neighbour);
        map.get(neighbour).setCost(this.graph.getLabel(currentNode.getNode(),neighbour));
        map.get(neighbour).setParent(currentNode.getNode());

        PrimNode<String,Double> tmp = pq.remove(map.get(neighbour));
        tmp.setCost(map.get(neighbour).getCost());
        pq.push(tmp);
        //pq.remove()
      }
    }
  }
  
  return mst;
}

/*
public  Graph<V, L> primMST() throws GraphException,PriorityQueueException{
    V source = this.graph.getNodes().iterator().next();
    if(!graph.containsNode(source)){
      return null; 
    } else {
      Graph<V, L> result = new Graph<>(false, true);

      //V visited
      HashSet<V> visited = new HashSet<>();
      PriorityQueue<Edge<V,L>> queue = new PriorityQueue<>(this.comparator);
      
      //Source visiting
      for(V v : graph.getNeighbours(source)){
        L l = graph.getLabel(source, v);
        Edge<V,L> edge = new Edge<>(source, v, l);
        queue.push(edge);
      }
      
      //Source is visited
      result.addNode(source);
      visited.add(source);

      while(result.numNodes() < graph.numNodes() && !queue.empty()){
        Edge<V,L> minEdge = queue.top();
        while(visited.contains(minEdge.getEnd()) && !queue.empty()){
          queue.pop();
          minEdge = queue.top();

          //minEdge == NULL -> Empty
          if(minEdge == null) break;
        }
        if(queue.empty()) break;

        result.addNode(minEdge.getEnd());
        result.addEdge(minEdge.getStart(), minEdge.getEnd(), minEdge.getLabel());

        queue.pop();

        //Visit minEdge
        for(V v : graph.getNeighbours(minEdge.getEnd())){
          if(!result.containsNode(v)) {
            L l = graph.getLabel(minEdge.getEnd(), v);
            queue.push(new Edge<>(minEdge.getEnd(), v, l));
          }
        }
            
        //minEdge.getEnd() is visited
        visited.add(minEdge.getEnd());
      }
      
      return result;
    }
    
  }
  */
}