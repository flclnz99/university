import java.util.*;

public class Graph<V,L> {
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
    if(map.containsKey(a)){
      System.out.println("addNode: node is yet in graph");
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


  public boolean addEdge(V a, V b, L l) throws GraphException{
    if (!map.containsKey(a) || !map.containsKey(b)) 
      throw new GraphException("addEdge: node source and destination do not exist in the graph");
    if(containsEdge(a, b))
     throw new GraphException("addEdge: edge already present in the graph");
    
    if(isLabelled()){
      if(l==null){
        throw new GraphException("addEdge: graph is labelled but edge provided has null label");
      }
    }
    Edge<V,L> newEdge=new Edge<>(b, l);
    map.get(a).add(newEdge);
    if(!isDirected()){
      Edge<V,L> reverseNewEdge=new Edge<>(a, l);
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
				LinkedList<Edge<V, L>> tmp = map.get(adjList.get(i).getDestination());
				boolean foundEdge = false;
				for (int j = 0; j < tmp.size() && !foundEdge; j++) {
					if (tmp.get(j).getDestination() == a) {
						map.get(adjList.get(i).getDestination()).remove(j);
						foundEdge = true;
					}
				}
			}
		} else {  
			for (Map.Entry<V, LinkedList<Edge<V, L>>> mapEntry : map.entrySet()) {
				V key = mapEntry.getKey();
				LinkedList<Edge<V, L>> adjList = mapEntry.getValue();
				for (int i = 0; i < adjList.size(); i++) {
					if (adjList.get(i).getDestination() == a) {
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
			if (map.get(a).get(i).getDestination() == b) {
				map.get(a).remove(i);
			}
		}
    if (!isDirected()) {   //se non è diretto rimuovo anche tutti gli archi da 'b' ad 'a'
			for (int i = 0; i < map.get(b).size(); i++) {
				if (map.get(b).get(i).getDestination() == a) {
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
		if (!isDirected()) {
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
    for (int i = 0; i < map.get(a).size(); i++) {
      if (map.get(a).get(i).getDestination() == b) {
        label=map.get(a).get(i).getLabel();
      }
    }
    return label;
  }

  public AbstractCollection<V> getNodes(){
    AbstractCollection<V> nodes = new ArrayList<>();
    for (Map.Entry<V, LinkedList<Edge<V, L>>> mapEntry : map.entrySet()) {
      nodes.add(mapEntry.getKey());
    }
    return nodes;		
  }
}

