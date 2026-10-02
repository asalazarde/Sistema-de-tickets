# Sistema-de-tickets
Práctica #1 - Estudiante: Aylin Salazar Delgado

## Organización

| Clase | Función |
| --- | --- |
| `Ticket` | Guarda el ID, descripción, nombre, prioridad y fechas. El contador estático `cantidad` asigna un ID consecutivo. |
| `Nodo` | Guarda un ticket y una referencia al siguiente nodo. |
| `ColaPrioridad` | Inserta cada ticket en su posición y permite consultar o retirar el frente. |
| `ListaEnlazada` | Agrega los tickets resueltos al final y los busca por ID. |
| `SistemaTickets` | Conecta las dos estructuras y realiza el cambio de pendiente a resuelto. |
| `Main` | Muestra los menús y valida las entradas. |

## Pasos de la solución

1. Definir los datos del ticket y asignar la fecha de creación al construirlo. La fecha de resolución inicia en `null`.
2. Crear el nodo que permite enlazar tickets sin un tamaño fijo.
3. Ordenar los pendientes al insertarlos en la cola. Los tickets de prioridad alta quedan antes que los de media y baja.
4. Implementar la lista simple y recorrerla desde el primer nodo para buscar un ID.
5. Al resolver, retirar el frente, establecer la fecha de resolución y agregar el mismo ticket a la lista.
6. Conectar las operaciones con los menús y validar campos vacíos, entradas no numéricas y opciones fuera de rango.
