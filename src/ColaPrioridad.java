// Tickets pendientes, ordenados por su orden de prioridad
public class ColaPrioridad {
    private Nodo frente;

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(Ticket ticket) {
        Nodo nuevo = new Nodo(ticket);
        if (estaVacia() || ticket.getPrioridad() < frente.getTicket().getPrioridad()) {
            nuevo.setSiguiente(frente);
            frente = nuevo;
            return;
        }

        // Si tienen la misma prioridad, queda primero el que llegó antes
        Nodo actual = frente;
        while (actual.getSiguiente() != null
                && actual.getSiguiente().getTicket().getPrioridad() <= ticket.getPrioridad()) {
            actual = actual.getSiguiente();
        }
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
    }

    public Ticket verFrente() {
        return estaVacia() ? null : frente.getTicket();
    }

    public Ticket desencolar() {
        if (estaVacia()) {
            return null;
        }
        Ticket ticket = frente.getTicket();
        frente = frente.getSiguiente();
        return ticket;
    }
}
