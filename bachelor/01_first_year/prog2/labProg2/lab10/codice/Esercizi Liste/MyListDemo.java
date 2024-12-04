public class MyListDemo {
  public static void main(String[] args) {
    MyList lista = new MyList();
    //2, -2, 4, 7, -6, -4
    lista.insert(-4);
    lista.insert(-6);
    lista.insert(7);
    lista.insert(4);
    lista.insert(-2);
    lista.insert(2);
    //System.out.println(lista.toString());
    //lista.pushSomma();
    System.out.println(lista.toString());
    lista.l(1);
    System.out.println(lista.toString());
  }
}
