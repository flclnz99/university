package prim;

public class PrimNode<V,L> {
  private V node;
  private V parent;
  private L cost;

  public PrimNode(V node, V parent, L cost){
    this.node=node;
    this.parent=parent;
    this.cost=cost;
  }

  public V getNode(){
    return this.node;
  }

  public V getParent(){
    return this.parent;
  }

  public L getCost(){
    return this.cost;
  }

  public void setCost(L cost){
    this.cost=cost;
  }

  public void setParent(V parent){
    this.parent=parent;
  }

  public void printNode(){
    System.out.println("nodo: "+this.getNode()+" parent: "+this.getParent()+" cost: "+this.getCost());
  }
}
