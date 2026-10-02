import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Datos de cada ticket
public class Ticket {
    private static int cantidad = 0;
    private final int id;
    private final String descripcion;
    private final String nombreCompleto;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;
    private final int prioridad;

    public Ticket(String descripcion, String nombreCompleto, int prioridad) {
        if (descripcion == null || descripcion.trim().isEmpty()
                || nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre y la descripción son obligatorios.");
        }
        if (prioridad < 1 || prioridad > 3) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 3.");
        }
        this.id = ++cantidad;
        this.descripcion = descripcion.trim();
        this.nombreCompleto = nombreCompleto.trim();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaResolucion = null;
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void resolver() {
        // La fecha se guarda una sola vez
        if (fechaResolucion == null) {
            fechaResolucion = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String nombrePrioridad;
        switch (prioridad) {
            case 1:
                nombrePrioridad = "Alta";
                break;
            case 2:
                nombrePrioridad = "Media";
                break;
            default:
                nombrePrioridad = "Baja";
        }
        String resolucion = fechaResolucion == null
                ? "Pendiente" : fechaResolucion.format(formato);
        return "ID: " + id
                + "\nUsuario: " + nombreCompleto
                + "\nDescripción: " + descripcion
                + "\nPrioridad: " + nombrePrioridad
                + "\nFecha de creación: " + fechaCreacion.format(formato)
                + "\nFecha de resolución: " + resolucion;
    }
}
