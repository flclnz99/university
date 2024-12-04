import java.util.ArrayList;

abstract class Evento {
    String info;
    abstract void elimina();

    abstract void getInfo();

    abstract Evento getOccurrence(int i);

    abstract void modify(String info) ;
}
