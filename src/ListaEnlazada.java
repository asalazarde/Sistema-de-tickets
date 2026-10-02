// Guarda los tickets que ya se resolvieron
public class ListaEnlazada {
    private Nodo primero;
    private Nodo ultimo;

    public void agregar(Ticket ticket) {
        // Se guarda al final, en el orden en que se resolvió
        Nodo nuevo = new Nodo(ticket);
        if (primero == null) {
            primero = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
    }

    public Ticket buscar(int id) {
        // Se revisan los nodos hasta encontrar el ID
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getTicket().getId() == id) {
                return actual.getTicket();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}
