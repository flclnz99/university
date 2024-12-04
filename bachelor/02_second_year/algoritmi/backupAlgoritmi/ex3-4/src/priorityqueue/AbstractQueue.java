package priorityqueue;

public interface AbstractQueue<T> {
    public boolean empty(); // controlla se la coda è vuota
    public T top(); // accede all'elemento in cima alla coda
    public boolean push(T e); // aggiunge un elemento alla coda
    public T pop(); // rimuove l'elemento in cima alla coda
    public boolean contains(T e); //restituisce true se la coda contiene l'elemento
    public T remove(T e); // rimuove un elemento se presente in coda -- O(logN)
}