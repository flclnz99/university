public class EventManager {

    private Evento currentEvent;
    public EventManager(){
        currentEvent = null;
    }
    public void create(String info, int r) {
        if(r > 1){
            currentEvent = new Recurrence(info, r);
        }
        else {
            currentEvent = new SingleEvent(info);
        }
    }

    public void printCEToScreen() {
        System.out.println("\nEvento corrente:" + currentEvent.getClass().getSimpleName()+
                ";\n info: ");
        currentEvent.getInfo();
    }


    public void openOccurrence(int i) {
        currentEvent = currentEvent.getOccurrence(i);
    }

    public void openTemplate() {
        currentEvent = currentEvent.getOccurrence(0);
    }

    public void modify(String info, boolean p){
        if(currentEvent.getIndex() == 0){
            p = true;
        }
        if(p){
            currentEvent.getRecurrence().modify(info);
        } else {
            modify(info);
        }
    }
    public void modify(String info){
        currentEvent.modify(info);
    }
}
