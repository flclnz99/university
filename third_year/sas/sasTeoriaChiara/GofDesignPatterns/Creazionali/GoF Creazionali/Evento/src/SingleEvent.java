public class SingleEvent extends Evento {

    int index;

    Evento recurrence;

    public void setIndex(int index) {
        this.index = index;
    }

    public SingleEvent(String info) {
        this.info = info;
    }

    public void setRecurrence(Evento r){
        recurrence = r;
    }

    @Override
    void modify(String info) {
        this.info = info;
    }

    @Override
    void elimina() {

    }

    @Override
    void getInfo() {
        System.out.println(this.info);
    }


    @Override
    Evento getOccurrence(int i) {
        if(r != null){
            return r.getOccurrence(i);
        } else {
            return this;
        }
    }
}
