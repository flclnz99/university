package graph;

public class Edge <V, L> implements AbstractEdge<V,L>{

  private V src,dest;
  private L label;

  public Edge(V src, V dest, L label) {
    this.src = src;
    this.dest = dest;
    this.label = label;
  }
  
  public V getStart() {
    return this.src;
  }

  public V getEnd() {
    return this.dest;
  }

  public L getLabel() {
    return this.label;
  }

  public void setLabel(L label) {
    this.label=label;
  }

  public void printEdge(){
    System.out.println("src="+this.getStart()+";"+" dest="+this.getEnd()+";"+" label="+this.getLabel());
  }
}
