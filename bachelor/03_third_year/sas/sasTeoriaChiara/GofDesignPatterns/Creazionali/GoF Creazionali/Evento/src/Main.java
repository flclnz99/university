// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
       EventManager em = new EventManager();
       em.create("matrimonio Alice", 1);
       em.printCEToScreen();
       em.modify("addioAlnubilato", false);
      em.printCEToScreen();
       em.create("Festa di Laurea", 3);
        em.printCEToScreen();
       // em.modify("Sbronza atomica");
      //  em.modify("recurrentInformation", true); //la UI non permetterà di dire false visto che CE è un capofila
       // em.printCEToScreen();
        em.openOccurrence(2);
        em.printCEToScreen();
        em.modify("Sbronza atomica", true);
        em.openTemplate();
        em.printCEToScreen();
        }
}