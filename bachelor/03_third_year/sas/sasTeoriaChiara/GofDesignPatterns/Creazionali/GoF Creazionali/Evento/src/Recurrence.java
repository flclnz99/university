import java.util.ArrayList;

public class Recurrence extends Evento {
    int repetitions;

    Evento template;
    ArrayList<Evento> occurrences = new ArrayList<>();

    public Recurrence(String info, int r){
        this.info = "ricorrenza di "+r+" occorrenze";
        this.repetitions = r;
        template = new SingleEvent(info);
        for (int i = 0; i<= repetitions; i++){
            SingleEvent se = new SingleEvent("occorrenza di "+info);
            se.setRecurrence(this);
            occurrences.add(se);
        }
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public ArrayList<Evento> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(ArrayList<Evento> occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    void modify(String info) {
        for (Evento o : occurrences) {
            o.modify(info);
        }
    }

    @Override
    void elimina() {

    }

    @Override
    void getInfo() {
        System.out.println(this.info + ", capofila di " + occurrences.size() + "occorrenze."+
                "\n\t eccole: ");
       occurrences.forEach((o)->{
           o.getInfo();
       });
    }

    @Override
    Evento getOccurrence(int i) {
        return occurrences.get(i);
    }

}
