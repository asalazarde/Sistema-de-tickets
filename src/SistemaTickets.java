// Maneja la creación, búsqueda y resolución
public class SistemaTickets {
    private final ColaPrioridad pendientes = new ColaPrioridad();
    private final ListaEnlazada resueltos = new ListaEnlazada();

    public Ticket crearTicket(String descripcion, String nombreCompleto, int prioridad) {
        Ticket ticket = new Ticket(descripcion, nombreCompleto, prioridad);
        pendientes.encolar(ticket);
        return ticket;
    }

    public Ticket buscarResuelto(int id) {
        return resueltos.buscar(id);
    }

    public Ticket verSiguiente() {
        return pendientes.verFrente();
    }

    public Ticket resolverSiguiente() {
        Ticket ticket = pendientes.desencolar();
        if (ticket != null) {
            // Sale de la cola y pasa a la lista con su fecha de resolución
            ticket.resolver();
            resueltos.agregar(ticket);
        }
        return ticket;
    }
}
