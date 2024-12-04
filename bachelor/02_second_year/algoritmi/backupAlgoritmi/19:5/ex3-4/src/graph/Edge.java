public class Edge <T, U> {

  private T dest;
  private U label;

  public Edge(T dest, U label) {
    //this.src = src;
    this.dest = dest;
    this.label = label;
  }
  /*
  public T getSource() {
    return this.src;
  }
  */

  public T getDestination() {
    return this.dest;
  }

  public U getLabel() {
    return this.label;
  }
}
