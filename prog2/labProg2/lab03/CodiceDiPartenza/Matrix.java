public class Matrix {
  int[][] data;

  public Matrix(int rows, int col){
    assert rows>0 && col>0:"dati input errati";
    data = new int[rows][col];
  }

  public int rows(){
    return data.length;
  }

  public int col(){
    return data[0].length;
  }

  public int get(int x, int y){
    return data[x][y];
  }

  public void set(int x, int y, int val){

  }
}
