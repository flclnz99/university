package graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class Graph<V,L> implements AbstractGraph<V,L>{
  private boolean directed,labelled;
  private HashMap<V, LinkedList<Edge<V,L>>> map;
  
  public Graph(Boolean directed, Boolean labelled) throws GraphException{ //boolean può essere solo true o false, Boolean anche null
    if(directed==null) throw new GraphException("directed parameter is null");
    if(labelled==null) throw new GraphException("labelled parameter is null");
    this.directed=directed;
    this.labelled=labelled;
    this.map=new HashMap<>();
  }

  public boolean isLabelled(){
    return labelled;
  } 

  public boolean isDirected(){
    return directed;
  }

  public boolean addNode(V a){
    if(a == null) {
      System.out.println("addNode: node cannot be null.");
      return false;
    }

    if(map.containsKey(a)){
      //System.out.println("addNode: node already exists.");
      return false;
    } else {
      map.put(a, new LinkedList<Edge<V,L>>());
      return true;
    }
  }

  // verifica se il grafo contiene un dato arco
	public boolean containsEdge(V a, V b) {
		LinkedList<Edge<V,L>> adjList = map.get(a);     //forse si può fare map.get(a).contains(b)
		for (int i = 0; i < adjList.size(); i++) {
			if (adjList.get(i) == b) {
				return true;
			}
		}
		return false;
	}

  public boolean addEdge(V a, V b, L l){
    if (!map.containsKey(a) || !map.containsKey(b)) 
      System.out.println("addEdge: node source and destination do not exist in the graph");
    if(containsEdge(a, b))
      System.out.println("addEdge: edge already present in the graph");
    
    if(isLabelled()){
      if(l==null){
        //throw new GraphException("addEdge: graph is labelled but edge provided has null label");
      }
    }
    Edge<V,L> newEdge=new Edge<>(a,b,l);
    map.get(a).add(newEdge);
    if(!isDirected()){
      Edge<V,L> reverseNewEdge=new Edge<>(b,a,l);
      map.get(b).add(reverseNewEdge);
    }
    return true;
  }

  public boolean containsNode(V a){
    return map.containsKey(a);
  }

  public boolean removeNode(V a) throws GraphException {
		if (!containsNode(a)) {
			throw new GraphException("removeNode: node does not exist");
		}
		if (!isDirected()) {  //grafo non diretto, rimuovo prima 'a' da tutti gli adiacenti
			LinkedList<Edge<V, L>> adjList = map.get(a); 
			for (int i = 0; i < adjList.size(); i++) {
				LinkedList<Edge<V, L>> tmp = map.get(adjList.get(i).getEnd());
				boolean foundEdge = false;
				for (int j = 0; j < tmp.size() && !foundEdge; j++) {
					if (tmp.get(j).getEnd() == a) {
            //rimozione dalla lista adiacenza di B
						map.get(adjList.get(i).getEnd()).remove(j);
						foundEdge = true;
					}
				}
			}
		} else {  
			for (Map.Entry<V, LinkedList<Edge<V, L>>> mapEntry : map.entrySet()) {
				V key = mapEntry.getKey();
				LinkedList<Edge<V, L>> adjList = mapEntry.getValue();
				for (int i = 0; i < adjList.size(); i++) {
					if (adjList.get(i).getEnd() == a) {
						map.get(key).remove(i);
					}
				}
			}
		}
		map.remove(a);
    return true;
	}

  public boolean removeEdge(V a, V b) throws GraphException{
    if(!map.containsKey(a) || !map.containsKey(b)){
      throw new GraphException("removeEdge: node source and destination do not exist in the graph");
    }
    if(!containsEdge(a, b)){
      throw new GraphException("removeEdge: edge does not exist");
    }
    for (int i = 0; i < map.get(a).size(); i++) { //rimuovo tutti gli archi da 'a' a 'b' 
			if (map.get(a).get(i).getEnd() == b) {
				map.get(a).remove(i);
			}
		}
    if (!isDirected()) {   //se non è diretto rimuovo anche tutti gli archi da 'b' ad 'a'
			for (int i = 0; i < map.get(b).size(); i++) {
				if (map.get(b).get(i).getEnd() == a) {
					map.get(b).remove(i);
				}
			}
		}
    return true;
  }

  public int numNodes(){
    return map.size();
  }

  public int numEdges(){
		int count = 0;
		for (Map.Entry<V, LinkedList<Edge<V, L>>> mapEntry : map.entrySet()) {
			LinkedList<Edge<V, L>> adjList = mapEntry.getValue();
			count = count + adjList.size();
		}
		if(!isDirected()) {
			count = count / 2;
		}
		return count;
  }

  public L getLabel(V a, V b) throws GraphException{
    if(!isLabelled()){
      throw new GraphException("getLabel: graph is not labelled");
    }
    if(!map.containsKey(a) || !map.containsKey(b)){
      throw new GraphException("getLabel: node source and destination do not exist in the graph");
    }
    L label=null;
    for(int i = 0; i < map.get(a).size(); i++) {
      if(map.get(a).get(i).getEnd() == b) {
        label=map.get(a).get(i).getLabel();
      }
    }
    return label;
  }

  public Collection<V> getNodes(){
    Collection<V> nodes = new ArrayList<>();
    for (Map.Entry<V, LinkedList<Edge<V, L>>> mapEntry : map.entrySet()) {
      nodes.add(mapEntry.getKey());
      //System.out.println(mapEntry.getKey());
    }
    return nodes;		
  }

  public Collection<AbstractEdge<V,L>> getEdges() throws GraphException{
    Collection<AbstractEdge<V, L>> edges = new ArrayList<>();
		V key;
		for (Map.Entry<V, LinkedList<Edge<V, L>>> me : map.entrySet()) {
			key = me.getKey();
			LinkedList<Edge<V, L>> adjList = map.get(key);
			for (int i = 0; i < adjList.size(); i++) {
        edges.add(adjList.get(i));
			}
		}
    /*for(Map.Entry<V, LinkedList<Edge<V, L>>> mapEntry : map.entrySet()) {
      for(int i = 0; i < mapEntry.getValue().size(); i++) {
        edges.add(mapEntry.getValue().get(i));
      }
    }*/
    return edges;
  }
  
  //recupero nodi adiacenti di un adato nodo
  public Collection<V> getNeighbours(V a) throws GraphException {
    if(!containsNode(a))
      throw new GraphException("getAdjList: node does not exist.");
    Collection<V> neighboursList = new ArrayList<>();
    LinkedList<Edge<V, L>> adjList = map.get(a);
    for (int i = 0; i < adjList.size(); i++) {
      neighboursList.add(adjList.get(i).getEnd());
    }
    return neighboursList;
    /* 
    AbstractCollection<Edge<V,L>> adjList = new ArrayList<>();
    for(int i = 0; i < map.get(a).size(); i++)
      adjList.add(map.get(a).get(i));

    return adjList;
    */
  }

  //determinazione peso grafo
  public double graphWeight() throws GraphException {
    if(!isLabelled())
      new GraphException("graphWeight: graph is note labelled.");
    double weight = 0.0;

    for(Map.Entry<V, LinkedList<Edge<V, L>>> mapEntry : map.entrySet()) {
      if((mapEntry.getValue().getFirst().getLabel()) instanceof Double) {
        for(int i = 0; i < mapEntry.getValue().size(); i++)
          weight = weight + (double)(mapEntry.getValue().get(i).getLabel());
      }
      else 
        new GraphException("graphWeight: label is not a number.");
    }

    if(!isDirected())
      weight = weight/2;
      
    return weight;
  }

  public void printGraph(){
    //System.out.println("graph labelled: "+isLabelled());
    //System.out.println("graph directed: "+isDirected());
    for (V vertex : map.keySet()) {
      System.out.print(vertex + " -> ");
      LinkedList<Edge<V, L>> edges = map.get(vertex);
      for (Edge<V, L> edge : edges) {
        System.out.print("(" + edge.getEnd() + ", " + edge.getLabel() + ") ");
      }
      System.out.println();
    }
  }

}

